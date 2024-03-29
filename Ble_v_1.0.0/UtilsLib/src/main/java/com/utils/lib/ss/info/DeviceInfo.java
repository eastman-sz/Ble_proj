package com.utils.lib.ss.info;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

/**
 * 设备信息。 
 * @author E
 */
public class DeviceInfo {

	/**
	 * 系统版本号。
	 * @return 系统版本号
	 */
	public static String getSystemVersion(){
		return android.os.Build.VERSION.RELEASE;
	}
	
	/**
	 * 设备品牌。
	 * @return 设备品牌
	 */
	public static String getBrand(){
		return android.os.Build.BRAND;
	}
	
	/**
	 * 设备型号。
	 * @return 设备型号
	 */
	public static String getModel(){
		return android.os.Build.MODEL;
	}
	
	/**
	 * 设备屏幕宽度。
	 * @param context 上下文环境
	 * @return 设备屏幕宽度
	 */
	public static int getScreenWith(Context context){
		return context.getResources().getDisplayMetrics().widthPixels;
	}
	
	/**
	 * 设备屏幕高度。
	 * @param context 上下文环境
	 * @return 设备屏幕高度
	 */
	public static int getScreenHeight(Context context){
		return context.getResources().getDisplayMetrics().heightPixels;
	}
	
    /**
     * 获取时区。
     * @return 时区
     */
    public static String getTimeZone() {
        return String.valueOf(TimeZone.getDefault().getRawOffset() / 1000);
    }
    
    /**
     * 获取设备语言
     * @return 设备语言
     */
    public static String getDeviceLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取设备区域
     * @return 设备区域
     */
    public static String getDeviceArea() {
        return Locale.getDefault().getDisplayCountry();
    }
    
    /**
     * 是否有SD卡。
     * @return boolean
     */
    public static boolean sdkExist(){
    	return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }
	
	/**
	 * 设备MAC。
	 * @param context 上下文环境
	 * @return 设备MAC
	 */
	public static String getLocalMacAddress(Context context){
//		WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//		WifiInfo info = wifi.getConnectionInfo();
        String[] data = new String[]{"A" , "B" , "C" , "D" , "E" , "F" ,"G" , "K" , "S", "Y" , "Z" ,
                "v" , "X" , "P" ,"D"  , "T" , "L" , "K", "M" , "N" , "X" , "R" , "T" ,"J" ,
                "0" ,"1" , "2", "3", "4", "5", "6", "7", "8", "9"};
        StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(System.currentTimeMillis()).substring(10));
        for (int i = 0 ; i < 6 ; i++){
            builder.append(data[new Random().nextInt(1000)%data.length]);
        }
//		String ss = info.getMacAddress();
//		if (null != ss) {
//			ss = ss.replace(":", "");
//			if (CheckHelper.isNullOrEmpty(ss) || ss.contains("000000")) {
//				return defMac;
//			}
//			return ss;
//		}
		return builder.toString();
	}
	
	/**
	 * 设备总内存。
	 * @param context 上下文环境
	 * @return 设备总内存
	 */
	public static long getTotalMemory(Context context) {
        String str1 = "/proc/meminfo";
        String str2;
        String[] arrayOfString;
        long initial_memory = 0;
        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(
            localFileReader, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;
            localBufferedReader.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return initial_memory/(1024*1024);
    }
	
	/**
	 * 设备剩余内存。
	 * @param context 上下文环境
	 * @return 设备剩余内存
	 */
	public static long getAvailMemory(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        MemoryInfo mi = new MemoryInfo();
        am.getMemoryInfo(mi);
        return mi.availMem/(1024*1024);
    }
	
	/**
	 * 根据手机的分辨率从 DP 的单位 转成为 PX(像素)。
	 * @param context 上下文环境
	 * @param dpValue DP值
	 * @return PX值
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	
	/**
	 * 根据手机的分辨率从 PX(像素) 的单位 转成为 DP。
	 * @param context 上下文环境
	 * @param pxValue PX值
	 * @return DP值
	 */
	public static int px2dip(Context context, float pxValue) {
	  final float scale = context.getResources().getDisplayMetrics().density;
	  return (int) (pxValue / scale + 0.5f);
	}
	
	/**
	 * SP转值为PX。
	 * @param context 上下文环境
	 * @param spValue SP值
	 * @return PX值
	 */
	public static int sp2px(Context context, float spValue) { 
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity; 
        return (int) (spValue * fontScale + 0.5f); 
	}
	
	/**
	 *  PX转值为SP。
	 * @param context 上下文环境
	 * @param pxValue PX值
	 * @return SP值
	 */
	public static int px2sp(Context context, float pxValue) { 
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity; 
        return (int) (pxValue / fontScale + 0.5f); 
    } 
	
	/**
	 * 状态栏高度。
	 * @param context 上下文环境
	 * @return 状态栏高度
	 */
	public static int getStatusBarHeight(Context context){
		Class<?> c =  null; 
		Object obj =  null; 
		Field field =  null; 
		int  x = 0, sbar =  0; 
		try  { 
		    c = Class.forName("com.android.internal.R$dimen");
		    obj = c.newInstance();
		    field = c.getField("status_bar_height");
		    x = Integer.parseInt(field.get(obj).toString());
		    sbar = context.getResources().getDimensionPixelSize(x);
		    return sbar;
		} catch(Exception e1) { 
		    e1.printStackTrace();
		}
		return 0;
	}	
	
}
