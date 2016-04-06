package com.ezz.bean;

import android.R.integer;
import cn.bmob.v3.BmobObject;

public class Collect extends BmobObject{
    private int id;
    private int cid;
	private String userid;
	private int type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
