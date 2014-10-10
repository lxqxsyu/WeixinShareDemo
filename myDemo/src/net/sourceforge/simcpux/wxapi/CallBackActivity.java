package net.sourceforge.simcpux.wxapi;

import com.example.weixinsharedemo.share.WXCallbackActivity;

public class CallBackActivity extends WXCallbackActivity{

	@Override
	public void weixinResp(int respCode) {
		System.out.println("respCode = " + respCode);
	}
}
