package com.example.weixinsharedemo.share;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.weixinsharedemo.util.WeixiShareUtil;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * �������ص��࣬������İ�����ӦĿ¼���½�һ��wxapiĿ¼�����ڸ�wxapiĿ¼������һ��WXEntryActivity�༯���Ը���
 * @author Administrator
 *
 */
public abstract class WXCallbackActivity extends Activity implements IWXAPIEventHandler{
	
	/**
	 * ����ɹ�
	 */
	public static final int CALLBACK_CODE_SUCCESS = 0;
	/**
	 * ȡ������
	 */
	public static final int CALLBACK_CODE_CANCEL = 1;
	/**
	 * �ܾ�����
	 */
	public static final int CALLBACK_CODE_DENY = 2;
	/**
	 * δ֪
	 */
	public static final int CALLBACK_CODE_UNKNOWN = 3;
	
	private IWXAPI wxApi;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ͨ��WXAPIFactory��������ȡIWXAPI��ʵ��
    	String appId = WeixiShareUtil.getWeixinAppId(this);
    	if(appId != null){
    		wxApi = WXAPIFactory.createWXAPI(this, appId, false);
    		wxApi.handleIntent(getIntent(), this);
    	}
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		
		setIntent(intent);
        wxApi.handleIntent(intent, this);
	}


	@Override
	public void onReq(BaseReq arg0) {

	}

	@Override
	public void onResp(BaseResp resp) {
		int result = CALLBACK_CODE_UNKNOWN;
		switch (resp.errCode) {
		case BaseResp.ErrCode.ERR_OK:
			result = CALLBACK_CODE_SUCCESS;
			break;
		case BaseResp.ErrCode.ERR_USER_CANCEL:
			result = CALLBACK_CODE_CANCEL;
			break;
		case BaseResp.ErrCode.ERR_AUTH_DENIED:
			result = CALLBACK_CODE_DENY;
			break;
		default:
			result = CALLBACK_CODE_UNKNOWN;
			break;
		}
		weixinResp(result);
	}
	
	public abstract void weixinResp(int respCode);
}
