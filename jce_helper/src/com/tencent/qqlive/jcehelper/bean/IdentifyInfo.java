package com.tencent.qqlive.jcehelper.bean;

/**
 * 账号信息
 * @author willzhao
 *
 */
public class IdentifyInfo {
	private String guid;
	private String userid;
	private String wxnickname;
	private String wxheadimgurl;
	private String qq;
	private String qqnickname;
	private String qqheadimgurl;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getWxnickname() {
		return wxnickname;
	}

	public void setWxnickname(String wxnickname) {
		this.wxnickname = wxnickname;
	}

	public String getWxheadimgurl() {
		return wxheadimgurl;
	}

	public void setWxheadimgurl(String wxheadimgurl) {
		this.wxheadimgurl = wxheadimgurl;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getQqnickname() {
		return qqnickname;
	}

	public void setQqnickname(String qqnickname) {
		this.qqnickname = qqnickname;
	}

	public String getQqheadimgurl() {
		return qqheadimgurl;
	}

	public void setQqheadimgurl(String qqheadimgurl) {
		this.qqheadimgurl = qqheadimgurl;
	}
}
