package com.smd.demo.model;

import java.util.Date;

/**
 * 用户实体类
 */
public class User extends BaseModel {

	private int id;// 用户id
	private String name;// 用户名
	private String password;// 密码
	private String email;// 邮箱
	private Date createDate;// 创建日期
	private Date modifyDate;// 最后修改日期

	private String ids; // 批量删除的id串

	public User() {
		super();
	}

	public User(int id, String name, String password, String email, Date createDate, Date modifyDate, String ids) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.ids = ids;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", ids=" + ids + "]";
	}

}
