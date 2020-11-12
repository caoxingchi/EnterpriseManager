package com.Enterprise.model;

public class Product {
	//商品
	private int Pid;
	private String Pname;
	private String Ptype;
	private int Pnum;
	private double price;

	public int getPid() {
		return Pid;
	}

	public void setPid(int pid) {
		this.Pid = pid;
	}

	public String getPname() {
		return Pname;
	}

	public void setPname(String pname) {
		this.Pname = pname;
	}

	public String getPtype() {
		return Ptype;
	}

	public void setPtype(String ptype) {
		this.Ptype = ptype;
	}

	public int getPnum() {
		return Pnum;
	}

	public void setPnum(int pnum) {
		Pnum = pnum;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Product( String pname, String ptype, int pnum, double price) {
		super();
		Pname = pname;
		Ptype = ptype;
		Pnum = pnum;
		this.price = price;
	}

	/**
	 * 更新数据
	 * @param pid
	 * @param pnum
	 */
	public Product(int pid, int Count) {
		super();
		Pid = pid;
		this.Pnum= Count;
	}

	@Override
	public String toString() {
		return "Product [Pid=" + Pid + ", Pname=" + Pname + ", Ptype=" + Ptype + ", Pnum=" + Pnum + ", price=" + price
				+ "]";
	}



	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
