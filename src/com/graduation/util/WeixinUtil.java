package com.graduation.util;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.graduation.po.menu.Button;
import com.graduation.po.menu.ClickButton;
import com.graduation.po.menu.Menu;
import com.graduation.po.menu.ViewButton;
import com.graduation.po.req.AccessToken;

public class WeixinUtil {
	private static final String APPID = "wx08e69fcdac93c9f4";
	private static final String APPSECRET = "9910ee4a6f2c6bf78c6a0570ba0e058e";
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	/**
	 * 
	 * @Title: doGetStr
	 * @Description: get请求
	 * @return: JSONObject
	 * @author: qinghaihuang
	 * @date: 2017年4月9日 下午9:39:01
	 */
	public static JSONObject doGetStr(String url) {

		@SuppressWarnings("deprecation")
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet();
		JSONObject jsObject = null;
		try {
			HttpResponse response = httpClient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// 转换成string
				String result = EntityUtils.toString(entity, "utf-8");
				jsObject = JSONObject.fromObject(result);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsObject;

	}

	/**
	 * 
	 * @Title: doPostStr
	 * @Description: post请求
	 * @return: JSONObject
	 * @author: qinghaihuang
	 * @date: 2017年4月9日 下午9:39:45
	 */
	public static JSONObject doPostStr(String url, String outStr) {
		@SuppressWarnings("deprecation")
		HttpClient httpClient = new DefaultHttpClient();
		// 传递参数
		HttpPost httpPost = new HttpPost();
		JSONObject jsonObject = null;
		try {
			httpPost.setEntity(new StringEntity(outStr, "utf-8"));
			HttpResponse response = httpClient.execute(httpPost);
			//
			String result = EntityUtils.toString(response.getEntity(), "utf-8");
			jsonObject = JSONObject.fromObject(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObject;

	}

	/**
	 * 
	 * @Title: getToken
	 * @Description: 获取access token
	 * @return: AccessToken
	 * @author: qinghaihuang
	 * @date: 2017年4月20日 下午9:56:50
	 */
	public static AccessToken getAccessToken() {
		AccessToken token = new AccessToken();
		// 参数替换
		// token可保存在本地文件中
		String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace(
				"APPSECRET", APPSECRET);
		JSONObject jsonObject = doGetStr(url);
		// System.out.print(url);
		if (jsonObject != null) {
			token.setToken(jsonObject.getString("access_token"));
			token.setExpiresin(jsonObject.getInt("expires_in"));
		}
		return token;
	}

	/**
	 * 
	 * @Title: initMenu
	 * @Description: TODO菜单设置
	 * @return: Menu
	 * @author: qinghaihuang
	 * @date: 2017年5月8日 上午11:48:10
	 */
	public static Menu initMenu() {
		Menu menu = new Menu();
		ClickButton cButton11 = new ClickButton();
		cButton11.setName("click官网");
		cButton11.setType("click");
		cButton11.setKey("11");

		ViewButton vButton21 = new ViewButton();
		vButton21.setName("viwe官网");
		vButton21.setType("view");
		vButton21.setUrl("http://112.74.111.167/library");

		ClickButton cButton31 = new ClickButton();
		cButton31.setName("扫一扫测试");
		cButton31.setType("scancode_push");
		cButton31.setKey("31");

		ClickButton cButton32 = new ClickButton();
		cButton31.setName("位置测试");
		cButton31.setType("location_select");
		cButton31.setKey("31");

		Button button3 = new Button();
		button3.setName("功能菜单");
		button3.setSub_button(new Button[] { cButton31, cButton32 });
		// 主菜单
		menu.setButton(new Button[] { cButton11, vButton21, button3 });
		return menu;

	}

	public static int createMenu(String token, String menu) {
		int result = 0;
		String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = new JSONObject();
		if (jsonObject != null) {
			result = jsonObject.getInt("errcode");
		}
		return result;
	}
}
