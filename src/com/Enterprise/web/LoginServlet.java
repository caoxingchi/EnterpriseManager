package com.Enterprise.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Enterprise.dao.CustomerDao;
import com.Enterprise.model.Customer;
import com.Enterprise.utils.DbUtils;
import com.Enterprise.utils.StringUtil;



public class LoginServlet extends HttpServlet{
	
	DbUtils dbutils=new DbUtils();
	CustomerDao customerDao=new CustomerDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	
	}

	@Override
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
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		}
		
		Customer customer=new Customer(userName,password);
		Connection con=null;
		try {
			con=dbutils.getConn();
			
			Customer currentUser=customerDao.login(con, customer);
			if(currentUser==null){
				req.setAttribute("error", "用户名或密码错误！");
				req.getRequestDispatcher("index.jsp").forward(req, resp);
				return;
			}else{
				//获取Session
				//ServletContext application=getServletContext();
				HttpSession session=req.getSession();
				session.setAttribute("currentUser", currentUser);
				//客户端跳转
				resp.sendRedirect("main.jsp");	
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeCon(con);
		}
	}
	
}
