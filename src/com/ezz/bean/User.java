package com.ezz.bean;

import android.R.integer;
import cn.bmob.v3.BmobObject;

/**
 * Created by 37492 on 2016/4/4.
 */
public class User extends BmobObject{
    private String username;
    private String password;
    private String realname;
    private int age;
    private String id;
    private String sex;
    private String headimage;
    private  String label;
    private String telphone;
    private String school;
    private String email;
    private String intro;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeadimage() {
        return headimage;
    }

    public void setHeadimage(String headimage) {
        this.headimage = headimage;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    
}
