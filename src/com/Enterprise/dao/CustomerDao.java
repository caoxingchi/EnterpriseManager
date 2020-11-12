package com.Enterprise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.Enterprise.model.Customer;
import com.Enterprise.model.PageBean;
import com.Enterprise.model.Product;
import com.Enterprise.utils.DbUtils;
import com.Enterprise.utils.StringUtil;

/**
 * 
 * @author xingchi
 * @Date 2019年10月10日
 *
 */
public class CustomerDao {
	Customer customer;

	/**
	 * ֤
	 * 
	 * @param con
	 * @param user
	 * @return
	 */
	public Customer login(Connection con, Customer customer) {

		try {
			String sql = "select * from t_user where cName=? and password=?";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, customer.getcName());
			ps.setString(2, customer.getPassword());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				customer = new Customer();
				customer.setcName(rs.getString("cName"));
				customer.setPassword(rs.getString("password"));
				customer.setAddress(rs.getString("cAddress"));
				customer.setcTel(rs.getString("cTel"));
				customer.setEmail(rs.getString("cEmail"));
				customer.setSex(rs.getString("sex"));
				customer.setWeight(rs.getInt("weight"));
				System.out.println("登录成功！");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtils.closeCon(con);
		}
		return customer;

	}

	public boolean isExistCustomer(Customer customer, Connection con) {

		ResultSet rs = null;
		PreparedStatement ps = null;
		String sqlUser = "select * from t_user where cName=?";
		try {
			ps = con.prepareStatement(sqlUser);
			ps.setString(1, customer.getcName());
			rs = ps.executeQuery();
			
			while (rs.next()) {
				customer = new Customer();
				customer.setcName(rs.getString("cName"));
				customer.setPassword(rs.getString("password"));
				customer.setAddress(rs.getString("cAddress"));
				customer.setcTel(rs.getString("cTel"));
				customer.setEmail(rs.getString("cEmail"));
				customer.setSex(rs.getString("sex"));
				customer.setWeight(rs.getInt("weight"));
				System.out.println("登录成功！");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public int Register(Connection con, Customer customer) {
		String name = customer.getcName();
		String password = customer.getPassword();
		String gender = customer.getSex();
		String tel_num = customer.getcTel();
		String email = customer.getEmail();
		String Address = customer.getAddress();
		int weight=customer.getWeight();
		int rs = 0;
		try {

			String sql = "insert into t_user values(null,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, tel_num);
			ps.setString(3, Address);
			ps.setString(4, gender);
			ps.setString(5, email);
			ps.setString(6, password);
			ps.setInt(7,weight);
			rs = ps.executeUpdate();
			if (rs > 0) {
				System.out.println("恭喜 " + name + " 注册成功");
			} else if (rs == 0) {
				System.out.println("注册失败！！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;

	}
	
	public ResultSet customerList(Connection con,PageBean pageBean,Customer customer)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_user");
		if(StringUtil.isNotEmpty(customer.getcName())){
			sb.append(" and cName like '%"+customer.getcName()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+
		pageBean.getRows());		
		}
		PreparedStatement ps=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return ps.executeQuery();
	}
	
	public int customerCount(Connection con,Customer customer )throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_user");
		if(StringUtil.isNotEmpty(customer.getcName())){
			sb.append(" and cName like '%"+customer.getcName()+"%'");
		}
		PreparedStatement ps=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	public int CustomerDelete (Connection con,String delIds) throws Exception{
		String sql="delete from t_user where id in("+delIds+")";
		PreparedStatement ps=con.prepareStatement(sql);
		return ps.executeUpdate();
	}
	
	public int customerModify(Connection con,Customer customer)throws Exception{
		String sql="update t_user set cTel=?,cAddress=?,sex=?,cEmail=?,password=? where cName=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, customer.getcTel());
		ps.setString(2, customer.getAddress());
		ps.setString(3, customer.getSex());
		ps.setString(4, customer.getEmail());
		ps.setString(5, customer.getPassword());
		ps.setString(6, customer.getcName());
		return ps.executeUpdate();
		
	}
}
