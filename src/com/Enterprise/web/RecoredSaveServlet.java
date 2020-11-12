package com.Enterprise.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Enterprise.dao.RecoredDao;
import com.Enterprise.model.Product;
import com.Enterprise.model.Recored;
import com.Enterprise.utils.DbUtils;
import com.Enterprise.utils.ResponseUtil;
import com.Enterprise.utils.StringUtil;

import net.sf.json.JSONObject;


public class RecoredSaveServlet extends HttpServlet {

	DbUtils dbUtil=new DbUtils();//驱动数据库的对象
	RecoredDao recoredDao=new RecoredDao();//定义一个数据库中的用户实体对象

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String id=req.getParameter("Pid");
		String suggestion=req.getParameter("suggestion");
		Recored recored=null;
		
		recored = new Recored(Integer.parseInt(id),suggestion);	
		Connection con=null;
		try{
			con=dbUtil.getConn();
			int saveNums=0;
			JSONObject result=new JSONObject();
			//如果id不是空的就进行编辑的操作
			saveNums=recoredDao.recoredAddSuggestion(con, recored);		
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
