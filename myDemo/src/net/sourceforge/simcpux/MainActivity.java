package net.sourceforge.simcpux;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.weixinsharedemo.share.WeixinShareManager;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button = (Button) findViewById(R.id.share_weibo);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				WeixinShareManager wsm = WeixinShareManager.getInstance(MainActivity.this);
				wsm.shareByWeixin(wsm.new ShareContentPic(R.drawable.ic_launcher),
						WeixinShareManager.WEIXIN_SHARE_TYPE_TALK);
			}
		});
	}
}
