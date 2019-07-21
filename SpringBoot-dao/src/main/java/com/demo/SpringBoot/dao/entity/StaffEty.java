package com.demo.SpringBoot.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StaffEty {

	/**
	 * ID
	 */
	private Long id;
	private String name;
	private Integer age;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd" , timezone="GMT+8")
	private java.util.Date birthday;
	private String email;

	/**
	 * 得到 ID
	 * @return Long
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * 设置 ID
	 * @param id,  : Long
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return this.age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

	public java.util.Date getBirthday() {
		return this.birthday;
	}
	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}