package com.example.weixinsharedemo.util;

import java.io.ByteArrayOutputStream;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;

public class WeixiShareUtil {
	
	/**
	 * 图片转换成Byte数组
	 * @param bmp
	 * @param needRecycle
	 * @return
	 */
	public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		bmp.compress(CompressFormat.PNG, 100, output);
		if (needRecycle) {
			bmp.recycle();
		}
		
		byte[] result = output.toByteArray();
		try {
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 获取微信AppId
	 * @param context
	 * @return
	 */
	public static String getWeixinAppId(Context context){
		try {
			ApplicationInfo appInfo = context.getPackageManager()
                     .getApplicationInfo(context.getPackageName(),
                     PackageManager.GET_META_DATA);
			return appInfo.metaData.getString("WEIXIN_APP_ID");
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
