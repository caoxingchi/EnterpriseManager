package com.Enterprise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.Enterprise.model.Customer;
import com.Enterprise.model.PageBean;
import com.Enterprise.model.Product;
import com.Enterprise.model.Staff;
import com.Enterprise.utils.DateUtil;
import com.Enterprise.utils.DbUtils;
import com.Enterprise.utils.StringUtil;

public class StaffDao {
	Staff staff;
	public Staff login(Connection con, Staff staff) {

		try {
			String sql = "select * from staff where Sname=? and password=?";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, staff.getSname());
			ps.setString(2, staff.getPassword());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				staff = new Staff();
				staff.setSname(rs.getString("Sname"));
				staff.setPassword(rs.getString("password"));
				staff.setAge(rs.getInt("age"));
				staff.setEdu(rs.getString("edu"));
				staff.setSex(rs.getString("sex"));
				staff.setSalary(Double.parseDouble(rs.getString("salary")));
				staff.setTelnum(rs.getString("STel"));
				staff.setDepartment(rs.getString("department"));
				staff.setSpostion(rs.getString("Spostion"));
				staff.setSworkingDate(DateUtil.formatString(rs.getString("SworkingDate"), "yyyy-MM-dd"));
				staff.setAddress(rs.getString("address"));
				staff.setWeight(rs.getInt("weight"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeCon(con);
		}
		return staff;

	}
	
	public boolean isExistStaff(Staff staff, Connection con) {

		ResultSet rs = null;
		PreparedStatement ps = null;
		String sqlUser = "select * from staff where Sname=?";
		try {
			ps = con.prepareStatement(sqlUser);
			ps.setString(1, staff.getSname());
			rs = ps.executeQuery();
			while(rs.next())
			{
				if(StringUtil.isEmpty(staff.getTelnum())){
					staff.setTelnum(rs.getString("STel"));
					System.out.println("列表操作");
				}else{
					System.out.println("注册操作");
				}
				
				staff.setDepartment(rs.getString("department"));
				staff.setSpostion(rs.getString("Spostion"));
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int Register(Staff staff,Connection con){
		int update=0;
		PreparedStatement ps = null;
		String sql="update staff set sex=?,age=?,edu=?,STel=?,password=?,address=?,weight=? where Sname=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, staff.getSex());
			ps.setInt(2, staff.getAge());
			ps.setString(3, staff.getEdu());
			ps.setString(4, staff.getTelnum());
			ps.setString(5, staff.getPassword());
			ps.setString(6, staff.getAddress());
			ps.setInt(7,staff.getWeight());
			ps.setString(8, staff.getSname());
			update=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return update;
	}
	
	public ResultSet staffList(Connection con,PageBean pageBean,Staff staff)throws Exception{
		StringBuffer sb=new StringBuffer("select * from staff");
		if(StringUtil.isNotEmpty(staff.getSname())){
			sb.append(" and Sname like '%"+staff.getSname()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+
		pageBean.getRows());		
		}
		PreparedStatement ps=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return ps.executeQuery();
	}
	
	public int staffCount(Connection con,Staff staff)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from staff");
		if(StringUtil.isNotEmpty(staff.getSname())){
			sb.append(" and Sname like '%"+staff.getSname()+"%'");
		}
		PreparedStatement ps=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	public int staffDelete (Connection con,String delIds) throws Exception{
		String sql="delete from staff where id in("+delIds+")";
		PreparedStatement ps=con.prepareStatement(sql);
		return ps.executeUpdate();
	}
	
	public int staffAdd(Connection con,Staff staff)throws Exception{
		String sql="insert into staff (id,Sname,department,SworkingDate,Spostion,salary,weight)values(null,?,?,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, staff.getSname());
		ps.setString(2, staff.getDepartment());
		ps.setString(3, DateUtil.formatDate(staff.getSworkingDate(), "yyyy-MM-dd"));
		ps.setString(4, staff.getSpostion());
		ps.setDouble(5, staff.getSalary());
		ps.setInt(6, staff.getWeight());
		return ps.executeUpdate();
	}
	
	public int staffModify(Connection con,Staff staff) throws Exception{
		String sql="update staff set Sname=?,department=?,SworkingDate=?,Spostion=?,salary=? where id=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, staff.getSname());
		ps.setString(2, staff.getDepartment());
		ps.setString(3, DateUtil.formatDate(staff.getSworkingDate(), "yyyy-MM-dd"));
		ps.setString(4, staff.getSpostion());
		ps.setDouble(5, staff.getSalary());
		ps.setInt(6, staff.getSid());
		return ps.executeUpdate();
	}
	
}
