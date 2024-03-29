package com.utils.lib.ss.net;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.utils.lib.ss.common.ThreadPool;
/**
 * 网络访问的实现类，返回网络状态码和内容。
 * @author E
 */
public abstract class BaseHttpImp2 implements BaseHttp{
	
	private static final String CHARSET = "utf-8";
	private static final int TIME_OUT = 10 * 1000;
	
	public static final int RESULT_OK = 9999999;
	public static final int NET_ERROR_CODE = 9999998;
	public static final int ERROR_REQUEST = 9999997;
	
	public static final String NET_ERROR_STRING = "NetError";
	
	//JSON里的ＫＥＹ
	public static final String RESPONSECODE = "responseCode"; 
	
	/**
	 * Send request to server (Basic method).
	 * @param urls 地址数组
	 * @param params params to send
	 * @return result returned
	 * @throws Exception exception caused
	 */
	@Override
	public String sendRequest(HashMap<String, Object> params , String... urls) throws Exception{
		JSONObject jsonObject = new JSONObject();
		try {
			HttpURLConnection resultUrlConnection = sendPostRequest(urls, params);
			int responseCode = resultUrlConnection.getResponseCode(); 
			String result = null;
			if (200 == responseCode) {
				result = read2String(resultUrlConnection.getInputStream()).toString();
				jsonObject = new JSONObject(result);
			}
			jsonObject.put(RESPONSECODE, responseCode);
			
		} catch (Exception e) {
			try {
				jsonObject.put(RESPONSECODE, ERROR_REQUEST);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		return jsonObject.toString();
	}
	
	@Override
	public String sendRequest(Context context,HashMap<String, Object> params , String... urls) throws Exception {
		if (!NetStatus.checkNetWorkStatus(context)) {
			return NET_ERROR_STRING;
		}
		String result = sendRequest(params ,urls);
		return result;
	}

	@Override
	public void sendRequest(Context context,HashMap<String, Object> params, Handler handler , String... urls) {
		sendRequest(context ,params, handler, -1 ,urls);
	}

	@Override
	public void sendRequest(Context context, final HashMap<String, Object> params, final Handler handler, final int resultWhats , final String... urls) {
		if (!NetStatus.checkNetWorkStatus(context)) {
			if (null != handler) {
				handler.sendEmptyMessage(NET_ERROR_CODE);
			}
			return;
		}
		ThreadPool.add(new Runnable() {
			@Override
			public void run() {
				try {
					String result = sendRequest(params ,urls);
					if (null != handler) {
						handler.obtainMessage(-1 == resultWhats ? RESULT_OK : resultWhats, result).sendToTarget();
					}
				} catch (Exception e) {
					e.printStackTrace();
					if (null != handler) {
						handler.sendEmptyMessage(NET_ERROR_CODE);
					}
				}
			}
		});
	}

	@Override
	public void sendRequest(Context context,  final HashMap<String, Object> params, final Handler handler, final Message msg , final String... urls) {
		if (!NetStatus.checkNetWorkStatus(context)) {
			if (null != handler) {
				handler.sendEmptyMessage(NET_ERROR_CODE);
			}
			return;
		}
		ThreadPool.add(new Runnable() {
			@Override
			public void run() {
				try {
					String result = sendRequest(params ,urls);
					if (null != handler) {
						if (null == msg) {
							handler.obtainMessage(RESULT_OK, result).sendToTarget();
						}else {
							Bundle bundle = new Bundle();
							bundle.putString("result", result);
							msg.setData(bundle);
							handler.sendMessage(msg);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					if (null != handler) {
						handler.sendEmptyMessage(NET_ERROR_CODE);
					}
				}
			}
		});
	}

	private HttpURLConnection sendPostRequest(String[] urls , HashMap<String, Object> params) throws Exception {
		int length = urls.length;
		if (length == 0) {
			return null;
		}
		params = specialHandleParams(params);
		StringBuilder builder = new StringBuilder();
		Set<Entry<String, Object>> entrys = null;
		if (null != params && !params.isEmpty()) {
			entrys = params.entrySet();
			for (Entry<String, Object> entry : entrys) {
				String key = entry.getKey();
				Object value = entry.getValue();
				if (null != key && null != value) {
					builder.append(key).append("=")
					.append(value instanceof String ? URLEncoder.encode(String.valueOf(value), CHARSET) : value)
					.append("&");
				}
			}
			builder.deleteCharAt(builder.length() - 1);
		}
		HttpURLConnection conn  = sendPost(urls, 0, builder);
		return conn;
	}
	
	public abstract HashMap<String, Object> specialHandleParams(HashMap<String, Object> params);
	
	private  HttpURLConnection sendPost(String[] urls , int urlIndex, StringBuilder builder) throws Exception{
		String requestUrl = urls[urlIndex];
		int urlSize = urls.length;
		URL url = new URL(requestUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setConnectTimeout(TIME_OUT);
		OutputStream out = conn.getOutputStream();
		out.write(builder.toString().getBytes(CHARSET));
		int responseCode = conn.getResponseCode(); 
		if (responseCode != 200 && urlIndex < (urlSize-1)) {
			urlIndex ++ ;
			sendPost(urls, urlIndex, builder);
		}
		return conn;
	}
	
	/**
	 * 将输入流转为字符流。
	 * @param inStream 输入流
	 * @return 内容。
	 * @throws Exception 异常
	 */
	protected String read2String(InputStream inStream) throws Exception {
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.flush();
		outSteam.close();
		inStream.close();
		return new String(outSteam.toByteArray(), CHARSET);
	}
	

}
