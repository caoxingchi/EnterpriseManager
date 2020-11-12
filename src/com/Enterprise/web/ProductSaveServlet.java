package com.Enterprise.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.util.DateUtil;

import com.Enterprise.dao.ProductDao;
import com.Enterprise.model.Product;
import com.Enterprise.utils.DbUtils;
import com.Enterprise.utils.ResponseUtil;
import com.Enterprise.utils.StringUtil;

import net.sf.json.JSONObject;


public class ProductSaveServlet extends HttpServlet {	
	DbUtils dbUtil=new DbUtils();//驱动数据库的对象
	ProductDao productDao=new ProductDao();//定义一个数据库中的用户实体对象

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String PrId=req.getParameter("Pid");
		String Pname=req.getParameter("Pname");
		String Ptype=req.getParameter("Ptype");
		int Pnum=Integer.parseInt(req.getParameter("Pnum"));
		Double price=Double.parseDouble(req.getParameter("Pprice"));
		
		Product product=null;
		product = new Product(Pname,Ptype,Pnum,price);		
		//如果传过来的id不是空的
		if(StringUtil.isNotEmpty(PrId)){
			product.setPid(Integer.parseInt(PrId));
		}
		Connection con=null;
		try{
			con=dbUtil.getConn();
			int saveNums=0;
			JSONObject result=new JSONObject();
			//如果id不是空的就进行编辑的操作
			if(StringUtil.isNotEmpty(PrId)){
				saveNums=productDao.productModify(con, product);
			}else{
				saveNums=productDao.productAdd(con, product);
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
