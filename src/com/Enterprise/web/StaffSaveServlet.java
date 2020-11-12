package com.Enterprise.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Enterprise.dao.StaffDao;
import com.Enterprise.model.Staff;
import com.Enterprise.utils.DateUtil;
import com.Enterprise.utils.DbUtils;
import com.Enterprise.utils.ResponseUtil;
import com.Enterprise.utils.StringUtil;

import net.sf.json.JSONObject;

public class StaffSaveServlet extends HttpServlet {
	
	DbUtils dbUtil=new DbUtils();//驱动数据库的对象
	StaffDao staffDao=new StaffDao();//定义一个数据库中的用户实体对象
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String Sid=req.getParameter("id");
		String Sname=req.getParameter("Sname");
		String department=req.getParameter("department");
		String SWorkingDate=req.getParameter("SworkingDate");
		String Spostion=req.getParameter("Spostion");
		String salary=req.getParameter("salary");
		int weight=5;
		
		Staff staff=null;
		try {
			staff = new Staff(Sname, department, DateUtil.formatString(SWorkingDate, "yyyy-MM-dd"), Spostion,
					Double.parseDouble(salary), weight);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//如果传过来的id不是空的
		if(StringUtil.isNotEmpty(Sid)){
			staff.setSid(Integer.parseInt(Sid));
		}
		Connection con=null;
		try{
			con=dbUtil.getConn();
			int saveNums=0;
			JSONObject result=new JSONObject();
			//如果id不是空的就进行编辑的操作
			if(StringUtil.isNotEmpty(Sid)){
				saveNums=staffDao.staffModify(con, staff);
			}else{
				saveNums=staffDao.staffAdd(con, staff);
			}
			
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
