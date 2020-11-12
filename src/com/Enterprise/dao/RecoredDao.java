package com.Enterprise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.Enterprise.model.PageBean;
import com.Enterprise.model.Recored;
import com.Enterprise.utils.DateUtil;
import com.Enterprise.utils.StringUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class RecoredDao {
	public ResultSet recoredList(Connection con,PageBean pageBean,Recored recored,String cName)throws Exception{
		StringBuffer sb=new StringBuffer("select r.id,r.cName,BPnum,BuyRecored,count,Amount,time,suggestion from recored r,t_user u where r.cName=u.cName");
		if(StringUtil.isNotEmpty(cName)){
			sb.append(" and u.cName=?");
		}
		if(StringUtil.isNotEmpty(recored.getBuyRecored())){
			sb.append(" and BuyRecored like '%"+recored.getBuyRecored()+"%'");
		}
		
		if(StringUtil.isNotEmpty(recored.getBPnum())){
			sb.append(" and BPnum like '%"+recored.getBPnum()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+
		pageBean.getRows());		
		}
		PreparedStatement ps=con.prepareStatement(sb.toString());
		if(StringUtil.isNotEmpty(cName)){
			ps.setString(1, cName);
		}
		
		return ps.executeQuery();
	}
	
	public int recoredCount(Connection con,Recored recored,String cName)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from recored r,t_user u where r.cName=u.cName");
		
		if(StringUtil.isNotEmpty(cName)){
			sb.append(" and u.cName=?");
		}
		if(StringUtil.isNotEmpty(recored.getBuyRecored())){
			sb.append(" and BuyRecored like '%"+recored.getBuyRecored()+"%'");
		}
		
		if(StringUtil.isNotEmpty(recored.getBPnum())){
			sb.append(" and BPnum like '%"+recored.getBPnum()+"%'");
		}
		PreparedStatement ps=con.prepareStatement(sb.toString());
		if(StringUtil.isNotEmpty(cName)){
			ps.setString(1, cName);
		}
		
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	public int recoredDelete (Connection con,String delIds) throws Exception{
		String sql="delete from recored where id in("+delIds+")";
		PreparedStatement ps=con.prepareStatement(sql);
		return ps.executeUpdate();
	}
	
	public int recoredAddSuggestion(Connection con,Recored recored) throws Exception{
		String sql="update recored set suggestion=? where id=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, recored.getSuggestion());
		ps.setInt(2, recored.getId());
		return ps.executeUpdate();
	}
	
	public double getPrice(Connection con,int id)throws Exception{
		double price=0;
		String sql="select Pprice from product where id=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs =ps.executeQuery();
		while(rs.next()){
			price=rs.getDouble("Pprice");
		}
		return price;
	}
	
	public int BuyProducts(Connection con,Recored recored)throws Exception{
		String sql="insert into recored values(null,?,?,?,?,?,?,null)";
		//INSERT INTO recored VALUES(NULL,"ITcolors","2019122809011","鼠标",5,225,"2019-12-28","")
		Random s=new Random();
		int r=s.nextInt(10);
		String BPnum=StringUtil.GetUserNun()+r;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, recored.getcName());
		ps.setString(2, BPnum);
		ps.setString(3, recored.getBuyRecored());
		ps.setInt(4, recored.getCount());
		ps.setDouble(5, recored.getAmount());
		ps.setString(6, df.format(new Date()));
		return ps.executeUpdate();
	}
}
