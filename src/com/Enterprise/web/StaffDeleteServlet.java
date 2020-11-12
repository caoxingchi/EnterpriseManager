package com.Enterprise.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Enterprise.dao.CustomerDao;
import com.Enterprise.dao.StaffDao;
import com.Enterprise.utils.DbUtils;
import com.Enterprise.utils.ResponseUtil;

import net.sf.json.JSONObject;


public class StaffDeleteServlet extends HttpServlet {
	DbUtils dbutils = new DbUtils();
	StaffDao staffDao=new StaffDao();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String delIds=req.getParameter("delIds");
		Connection con=null;
		try {
			con = dbutils.getConn();
			JSONObject result=new JSONObject();
			
			int delNum=staffDao.staffDelete(con, delIds);
			
			if(delNum>0){
				result.put("success", "true");
				result.put("delNum", delNum);
			}else{
				result.put("errorMsg", "删除失败");
				
			}

			ResponseUtil.write(resp, result);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeCon(con);
		}
	}

}
