package com.Enterprise.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Enterprise.dao.CustomerDao;
import com.Enterprise.model.Customer;
import com.Enterprise.utils.DbUtils;
import com.Enterprise.utils.ResponseUtil;


import net.sf.json.JSONObject;


public class CustomerModifyServlet extends HttpServlet {

	DbUtils dbUtil=new DbUtils();//驱动数据库的对象
	CustomerDao customerDao=new CustomerDao();//定义一个数据库中的用户实体对象
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String cName=req.getParameter("cName");
		String password=req.getParameter("password");
		String sex=req.getParameter("sex");
		String email=req.getParameter("email");
		String address=req.getParameter("address");
		String cTel=req.getParameter("cTel");
		
		Customer customer=null;
		customer = new Customer(cName,cTel,address,email,sex,password);		
		//如果传过来的id不是空的
		
		Connection con=null;
		try{
			con=dbUtil.getConn();
			int saveNums=0;
			JSONObject result=new JSONObject();
			//如果id不是空的就进行编辑的操作
			saveNums=customerDao.customerModify(con, customer);
			if(saveNums>0){
				result.put("success", "true");
			}else{
				result.put("success", "true");
				result.put("errorMsg","删除失败");
			}
			ResponseUtil.write(resp, result);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbUtils.closeCon(con);
		}
	}

}
