package com.demo.SpringBoot.dao.vo.userreport;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserReportBean{
	private Long id;	//ID
	private String name;
	private Integer age;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private java.util.Date birthday;
	private String email;
	private String departmentName;
	private String address;

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

	public String getDepartmentName() {
		return this.departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getAddress() {
		return this.address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}