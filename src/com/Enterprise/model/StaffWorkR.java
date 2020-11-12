package com.Enterprise.model;

import java.util.Date;

public class StaffWorkR {
	private int id;
	private String Sname;
	private String WType;// 迟到，早退，正常，
	private String department;
	private String Spostion;
	private Date Atime;// 打卡时间
	private Date ETime;// 下班时间
	public StaffWorkR() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StaffWorkR(String sname, String wType, String department, String spostion, Date atime, String sTel) {
		super();
		Sname = sname;
		WType = wType;
		this.department = department;
		Spostion = spostion;
		Atime = atime;
		STel = sTel;
	}

	private String STel;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSname() {
		return Sname;
	}

	public void setSname(String sname) {
		Sname = sname;
	}

	public String getWType() {
		return WType;
	}

	public void setWType(String wType) {
		WType = wType;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSpostion() {
		return Spostion;
	}

	public void setSpostion(String spostion) {
		Spostion = spostion;
	}

	public Date getAtime() {
		return Atime;
	}

	public void setAtime(Date atime) {
		Atime = atime;
	}

	public Date getETime() {
		return ETime;
	}

	public void setETime(Date eTime) {
		ETime = eTime;
	}

	public String getSTel() {
		return STel;
	}

	public void setSTel(String sTel) {
		STel = sTel;
	}

	@Override
	public String toString() {
		return "StaffWorkR [id=" + id + ", Sname=" + Sname + ", WType=" + WType + ", department=" + department
				+ ", Spostion=" + Spostion + ", Atime=" + Atime + ", ETime=" + ETime + ", STel=" + STel + "]";
	}
	

}
