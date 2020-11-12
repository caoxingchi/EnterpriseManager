package com.Enterprise.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Enterprise.dao.StaffDao;
import com.Enterprise.model.Customer;
import com.Enterprise.model.Staff;
import com.Enterprise.utils.DbUtils;
import com.Enterprise.utils.StringUtil;


public class StaffLoginServlet extends HttpServlet {
	
	DbUtils dbutils=new DbUtils();
	StaffDao  staffDao=new StaffDao();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String userName=req.getParameter("userName");
		String password=req.getParameter("password");
		req.setAttribute("userName", userName);
		req.setAttribute("password", password);
		if(StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)){
			req.setAttribute("error", "用户名或密码不能为空！");
			req.getRequestDispatcher("staffLogin.jsp").forward(req, resp);
			return;
		}
		
		Staff staff=new Staff(userName,password);
		Connection con=null;
		try {
			con=dbutils.getConn();
			Staff currentUser=staffDao.login(con,staff);
			if(currentUser==null){
				req.setAttribute("error", "用户名或密码错误！");
				req.getRequestDispatcher("staffLogin.jsp").forward(req, resp);
				return;
			}else{
				System.out.println(currentUser.toString());
				//获取Session
				//ServletContext application=getServletContext();
				HttpSession session=req.getSession();
				session.setAttribute("currentUser", currentUser);
				//客户端跳转
				resp.sendRedirect("staffmain.jsp");	
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeCon(con);
		}
	}

}
