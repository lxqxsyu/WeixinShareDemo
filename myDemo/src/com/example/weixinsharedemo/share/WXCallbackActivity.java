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
 * 分享结果回调类，请在你的包名相应目录下新建一个wxapi目录，并在该wxapi目录下新增一个WXEntryActivity类集成自该类
 * @author Administrator
 *
 */
public abstract class WXCallbackActivity extends Activity implements IWXAPIEventHandler{
	
	/**
	 * 分享成功
	 */
	public static final int CALLBACK_CODE_SUCCESS = 0;
	/**
	 * 取消分享
	 */
	public static final int CALLBACK_CODE_CANCEL = 1;
	/**
	 * 拒绝访问
	 */
	public static final int CALLBACK_CODE_DENY = 2;
	/**
	 * 未知
	 */
	public static final int CALLBACK_CODE_UNKNOWN = 3;
	
	private IWXAPI wxApi;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 通过WXAPIFactory工厂，获取IWXAPI的实例
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
