package com.demo.SpringBoot.dao.entity;
public class DepartmentStaffsRefEty {
	private Long iD;
	private Integer departmentId;
	private Integer staffId;

	public Long getID() {
		return this.iD;
	}
	public void setID(Long iD) {
		this.iD = iD;
	}

	public Integer getDepartmentId() {
		return this.departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getStaffId() {
		return this.staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

}