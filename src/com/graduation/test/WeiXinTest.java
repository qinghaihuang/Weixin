package com.graduation.test;

import com.graduation.po.req.AccessToken;
import com.graduation.util.WeixinUtil;

public class WeiXinTest {
	public static void main(String[] args) {
		try {
			AccessToken token = WeixinUtil.getAccessToken();
			System.out.print("票据" + token.getToken());
			System.out.print("有效时间" + token.getExpiresin());
			// String menu = JSONObject.fromObject(WeixinUtil.initMenu()
			// .toString());
			// int result = WeixinUtil.createMenu(token.getToken(), menu);
			// if (result == 0) {
			// System.out.println("成功");
			// } else {
			// System.out.println("创建失败，错误码" + result);
			// }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
