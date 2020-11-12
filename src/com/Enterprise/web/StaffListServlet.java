package com.Enterprise.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Enterprise.dao.CustomerDao;
import com.Enterprise.dao.StaffDao;
import com.Enterprise.model.Customer;
import com.Enterprise.model.PageBean;
import com.Enterprise.model.Staff;
import com.Enterprise.utils.DbUtils;
import com.Enterprise.utils.JsonUtils;
import com.Enterprise.utils.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StaffListServlet extends HttpServlet {
	
	DbUtils dbutils=new DbUtils();
	StaffDao staffDao=new StaffDao();
	
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String  page=req.getParameter("page");
		String  rows=req.getParameter("rows");
		String Sname=req.getParameter("Sname");
		if(Sname==null){
			Sname="";
		}
		Staff staff=new Staff();
		staff.setSname(Sname);
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Connection con=null;
		try {
			con = dbutils.getConn();
			JSONObject result=new JSONObject();
			JSONArray jsonArray=JsonUtils.formatRsToJsonArray(staffDao.staffList(con,pageBean,staff));
			int total=staffDao.staffCount(con,staff);
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(resp, result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeCon(con);
		}
	}

}
