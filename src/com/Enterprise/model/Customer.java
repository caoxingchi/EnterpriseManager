package com.Enterprise.model;

public class Customer {
	private int id;
	private String cName;
	private String cTel;
	private String address;
	private String email;
	private String sex;
	private String password;
	private int weight;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String cName, String password) {
		super();
		this.cName = cName;
		this.password = password;
	}

	
	public Customer(String cName, String cTel, String address, String email, String sex, String password) {
		super();
		this.cName = cName;
		this.cTel = cTel;
		this.address = address;
		this.email = email;
		this.sex = sex;
		this.password = password;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcTel() {
		return cTel;
	}

	public void setcTel(String cTel) {
		this.cTel = cTel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Customer [cName=" + cName + ", cTel=" + cTel + ", address=" + address + ", email=" + email + ", sex="
				+ sex + ", password=" + password + "]";
	}

}
