package com.Enterprise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.Enterprise.model.PageBean;
import com.Enterprise.model.StaffWorkR;
import com.Enterprise.utils.DateUtil;
import com.Enterprise.utils.StringUtil;

public class StaffWorkRDao {
	public ResultSet recoredList(Connection con,PageBean pageBean,StaffWorkR staffWorkR)throws Exception{
		StringBuffer sb=new StringBuffer("select * from staffworkr");
		if(StringUtil.isNotEmpty(staffWorkR.getSname())){
			sb.append(" and Sname like '%"+staffWorkR.getSname()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+
		pageBean.getRows());		
		}
		PreparedStatement ps=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return ps.executeQuery();
	}
	
	public int recoredCount(Connection con,StaffWorkR staffWorkR)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from staffworkr");
		
		if(StringUtil.isNotEmpty(staffWorkR.getSname())){
			sb.append(" and Sname like '%"+staffWorkR.getSname()+"%'");
		}
		PreparedStatement ps=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	public int PunchCard(Connection con,StaffWorkR staffWorkR) throws Exception{
		String sql="insert into staffworkr (id,Sname,WType,department,Spostion,Atime,STel)values(null,?,?,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, staffWorkR.getSname());
		ps.setString(2, staffWorkR.getWType());
		ps.setString(3, staffWorkR.getDepartment());
		ps.setString(4, staffWorkR.getSpostion());
		ps.setString(5, DateUtil.formatDate(staffWorkR.getAtime(), "yyyy-MM-dd HH:mm:ss"));
		ps.setString(6, staffWorkR.getSTel());
		
		/*byte ptext[] = myString.getBytes();
		String value = new String(ptext, "UTF-8");*/
		System.out.println("上班0090      打卡"+staffWorkR.getSname()+staffWorkR.getSTel());
		return ps.executeUpdate();
	}
	
	public int PunchLeaveWork(Connection con,StaffWorkR staffWorkR) throws Exception{
		String sql="update staffworkr set ETime=?,WType=? where Sname=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, DateUtil.formatDate(staffWorkR.getETime(),"yyyy-MM-dd HH:mm:ss"));
		ps.setString(2, staffWorkR.getWType());
		ps.setString(3, staffWorkR.getSname());
		System.out.println("下班0090  打卡"+staffWorkR.getSname()+staffWorkR.getWType());
		return ps.executeUpdate();
	}
}
