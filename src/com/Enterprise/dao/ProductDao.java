package com.Enterprise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.Enterprise.model.PageBean;
import com.Enterprise.model.Product;
import com.Enterprise.utils.StringUtil;

import javafx.css.PseudoClass;



public class ProductDao {
	
	public ResultSet productList(Connection con,PageBean pageBean,Product product)throws Exception{
		StringBuffer sb=new StringBuffer("select * from product");
		if(StringUtil.isNotEmpty(product.getPname())){
			sb.append(" and Pname like '%"+product.getPname()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+
		pageBean.getRows());		
		}
		PreparedStatement ps=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return ps.executeQuery();
	}
	
	public int productCount(Connection con,Product product )throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from product");
		if(StringUtil.isNotEmpty(product.getPname())){
			sb.append(" and Pname like '%"+product.getPname()+"%'");
		}
		PreparedStatement ps=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	public int ProductDelete (Connection con,String delIds) throws Exception{
		String sql="delete from product where id in("+delIds+")";
		PreparedStatement ps=con.prepareStatement(sql);
		return ps.executeUpdate();
	}
	
	public int productModify(Connection con,Product product) throws Exception{
		String sql="update product set Pnum=?,Ptype=?,Pname=?,Pprice=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, product.getPnum());
		pstmt.setString(2, product.getPtype());
		pstmt.setString(3, product.getPname());
		pstmt.setDouble(4, product.getPrice());		
		pstmt.setInt(5, product.getPid());
		return pstmt.executeUpdate();
	}
	
	public int productAdd(Connection con,Product product)throws Exception{
		String sql="insert into product values(null,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, product.getPname());
		ps.setString(2, product.getPtype());
		ps.setInt(3, product.getPnum());
		ps.setDouble(4, product.getPrice());
		return ps.executeUpdate();
	}
	
	public int UpdatePnum(Connection con,Product product)throws Exception{
		String sql="update product set Pnum=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, product.getPnum());	
		pstmt.setInt(2, product.getPid());
		return pstmt.executeUpdate();
	}
	
	public int getPnum(Connection con,Product product)throws Exception{
		String sql="select Pnum from product where id=?";
		int Pnum=0;
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, product.getPid());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Pnum=rs.getInt("Pnum");
		}
		return Pnum;
	}

}
