package com.demo.SpringBoot.dao.vo.userreport;
public class UserReportForm{
	private Long id;	//ID
	private String name;
	private java.util.Date birthday;

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

	public java.util.Date getBirthday() {
		return this.birthday;
	}
	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}

}