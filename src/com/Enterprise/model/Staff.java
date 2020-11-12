package com.Enterprise.model;

import java.util.Date;

public class Staff {
	
	private int Sid;
	private String Sname;
	private String sex;
	private int age;
	private String edu;
	private String Department;
	private Date SworkingDate;
	private String Spostion;
	private double salary;
	private String password;
	private String telnum;
	private String address;
	private int weight;

	public int getSid() {
		return Sid;
	}

	public void setSid(int sid) {
		Sid = sid;
	}

	public Date getSworkingDate() {
		return SworkingDate;
	}

	public void setSworkingDate(Date sworkingDate) {
		SworkingDate = sworkingDate;
	}
	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getTelnum() {
		return telnum;
	}

	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Staff(String sname, String password) {
		super();
		Sname = sname;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSname() {
		return Sname;
	}

	public void setSname(String sname) {
		Sname = sname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEdu() {
		return edu;
	}

	public void setEdu(String edu) {
		this.edu = edu;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}


	public String getSpostion() {
		return Spostion;
	}

	public void setSpostion(String spostion) {
		Spostion = spostion;
	}

	public double getSalary() {
		return salary;
	}

	public Staff(String sname, String department, Date sWorkingDate, String spostion, double salary, int weight) {
		super();
		Sname = sname;
		Department = department;
		SworkingDate = sWorkingDate;
		Spostion = spostion;
		this.salary = salary;
		this.weight = weight;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Staff [Sid=" + Sid + ", Sname=" + Sname + ", sex=" + sex + ", age=" + age + ", edu=" + edu
				+ ", Department=" + Department + ", SworkingDate=" + SworkingDate + ", Spostion=" + Spostion
				+ ", salary=" + salary + ", password=" + password + ", telnum=" + telnum + ", address=" + address
				+ ", weight=" + weight + "]";
	}
	

}
