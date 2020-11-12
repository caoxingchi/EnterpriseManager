package com.Enterprise.web;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class loginOutServlet extends HttpServlet {


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String weight=req.getParameter("weight");
		HttpSession session=req.getSession();
		
		if(Integer.parseInt(weight)==5){
			
			if(session!=null)
				session.invalidate();
				resp.sendRedirect("http://localhost:8080/EnterpriseManager/staffLogin.jsp");
				System.out.println("退出登录");
			
						
		}else{
			if(session!=null)
			session.invalidate();
			resp.sendRedirect("http://localhost:8080/EnterpriseManager/index.jsp");	
		
		}
		

	}

}
