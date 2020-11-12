package com.Enterprise.model;

import java.util.Date;

public class Recored {
	private int id;
	private String cName;
	private String BuyRecored;
	private String suggestion;
	private String BPnum;// 订单号
	private Date buyTime;
	private int count;//数量
	private double Amount;
	
	public Recored() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * add suggestion
	 * @param id
	 * @param suggestion
	 */
	public Recored(int id,String suggestion) {
		super();
		this.id=id;
		this.suggestion = suggestion;
	}


	/**
	 * buy products
	 * @param cName
	 * @param buyRecored
	 * @param suggestion
	 * @param bPnum
	 * @param buyTime
	 * @param count
	 * @param amount
	 */
	public Recored(String cName, String buyRecored, String suggestion, int count,Double amount) {
		super();
		this.cName = cName;
		BuyRecored = buyRecored;
		this.suggestion = suggestion;
		this.count = count;
		this.Amount=amount;
	}


	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getAmount() {
		return Amount;
	}

	public void setAmount(double amount) {
		Amount = amount;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getBuyRecored() {
		return BuyRecored;
	}

	public void setBuyRecored(String buyRecored) {
		BuyRecored = buyRecored;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public String getBPnum() {
		return BPnum;
	}

	public void setBPnum(String bPnum) {
		BPnum = bPnum;
	}

	public Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}
}
