package com.Enterprise.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Enterprise.dao.RecoredDao;
import com.Enterprise.model.PageBean;
import com.Enterprise.model.Recored;
import com.Enterprise.utils.DbUtils;
import com.Enterprise.utils.JsonUtils;
import com.Enterprise.utils.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class RecoredListServlet extends HttpServlet {

	DbUtils dbutils=new DbUtils();
	RecoredDao recoredDao=new RecoredDao();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String  page=req.getParameter("page");
		String  rows=req.getParameter("rows");
		String BuyRecored=req.getParameter("BuyRecored");
		String BPnum=req.getParameter("BPnum");
		String cName=req.getParameter("customer");
		if(BuyRecored==null){
			BuyRecored="";
		}
		
		if(BPnum==null){
			BPnum="";
		}
		
		if(cName==null){
			cName="";
		}
		
		Recored recored=new Recored();
		recored.setBuyRecored(BuyRecored);
		recored.setBPnum(BPnum);
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Connection con=null;
		try {
			con = dbutils.getConn();
			JSONObject result=new JSONObject();
			JSONArray jsonArray=JsonUtils.formatRsToJsonArray(recoredDao.recoredList(con,pageBean,recored,cName));
			int total=recoredDao.recoredCount(con,recored,cName);
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
