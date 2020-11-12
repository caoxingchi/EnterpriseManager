package com.Enterprise.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Enterprise.dao.ProductDao;
import com.Enterprise.dao.RecoredDao;
import com.Enterprise.model.Product;
import com.Enterprise.model.Recored;
import com.Enterprise.utils.DbUtils;
import com.Enterprise.utils.ResponseUtil;
import com.Enterprise.utils.StringUtil;

import net.sf.json.JSONObject;

public class BuyProductServlet extends HttpServlet {

	DbUtils dbUtil = new DbUtils();// 驱动数据库的对象
	RecoredDao recoredDao = new RecoredDao();// 定义一个数据库中的用户实体对象
	ProductDao productDao=new ProductDao();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String PrId = req.getParameter("pid");
		String cName = req.getParameter("cName");
		String BuyRecored = req.getParameter("BuyRecored");
		String suggestion = req.getParameter("suggestion");
		int count = Integer.parseInt(req.getParameter("buyNum"));

		Recored recored = null;
		Product product =null;
		// 如果传过来的id不是空的
		if(StringUtil.isEmpty(suggestion)){
			suggestion="";
		}
		Connection con = null;
		try {
			con = dbUtil.getConn();
			
			product=new Product();
			product.setPid(Integer.parseInt(PrId));
			int Pnum= productDao.getPnum(con, product);
			int saveNums = 0;
			JSONObject result = new JSONObject();
			
			if(count>Pnum || count<0){
				result.put("errorMsg", "true");
				result.put("message", "购买失败");
				ResponseUtil.write(resp, result);
			}else{
				double amount = count*recoredDao.getPrice(con, Integer.parseInt(PrId));
				recored = new Recored(cName, BuyRecored, suggestion, count, amount);
				saveNums = recoredDao.BuyProducts(con, recored);
				if (saveNums > 0) {
					System.out.println("count="+count);
					//更新数据，产品数量
					int CurrentCount=Pnum-count;
					product=new Product(Integer.parseInt(PrId),CurrentCount);
					productDao.UpdatePnum(con, product);
					result.put("success", "true");
					result.put("message", "购买成功！");
					ResponseUtil.write(resp, result);
				} else {
					result.put("success", "false");
					result.put("errorMsg", "购买失败！");
					ResponseUtil.write(resp, result);
				}
			}
			

			
			ResponseUtil.write(resp, result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeCon(con);
		}
	}

}
