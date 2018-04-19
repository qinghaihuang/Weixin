package com.graduation.po.req;

public class AccessToken {
	// 获取到的凭证
	private String token;
	// 凭证有效时间，单位：秒
	private int expiresin;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpiresin() {
		return expiresin;
	}

	public void setExpiresin(int expiresin) {
		this.expiresin = expiresin;
	}

}
