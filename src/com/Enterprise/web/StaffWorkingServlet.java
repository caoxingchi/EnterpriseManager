package com.Enterprise.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Enterprise.dao.StaffDao;
import com.Enterprise.dao.StaffWorkRDao;
import com.Enterprise.model.Product;
import com.Enterprise.model.Staff;
import com.Enterprise.model.StaffWorkR;
import com.Enterprise.utils.DateUtil;
import com.Enterprise.utils.DbUtils;
import com.Enterprise.utils.ResponseUtil;

import net.sf.json.JSONObject;


public class StaffWorkingServlet extends HttpServlet {

	DbUtils dbUtils=new DbUtils();
	StaffDao staffDao=new StaffDao();
	StaffWorkRDao staffWorkRDao=new StaffWorkRDao();
	private JSONObject result;
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String Sname=req.getParameter("name");
		String ATime=req.getParameter("date");
		String type=req.getParameter("type");
		
		Staff staff=null;
		StaffWorkR staffWorkR=null;
		String WType=null;
		Connection con=null;
		con=dbUtils.getConn();
		if("startW".equals(type)){
			
			try {
				staff = new Staff();
				staff.setSname(Sname);
				boolean isExist = staffDao.isExistStaff(staff, con);
				System.out.println(isExist);
				if(isExist)
				System.out.println("存在");
				Date aDate = DateUtil.formatString(ATime, "yyyy-MM-dd HH:mm:ss");
				String yString=DateUtil.formatDate(new Date(), "yyyy-MM-dd");
				Date sDate = DateUtil.formatString(yString+" 08:00:00", "yyyy-MM-dd HH:mm:ss");
				if (aDate.before(sDate)) {
					WType = "正常";
				} else if (aDate.after(sDate)) {
					WType = "迟到";
				} else {
					WType = "旷工";
				}
				System.out.println(WType);
				staffWorkR = new StaffWorkR(Sname, WType, staff.getDepartment(), staff.getSpostion(), aDate,
						staff.getTelnum());
				 result = new JSONObject();
				int punchCard = staffWorkRDao.PunchCard(con, staffWorkR);
				if (punchCard > 0) {
					result.put("success", "true");
					System.out.println("打卡成功！");
					result.put("Msg", "打卡成功"+ATime);
					ResponseUtil.write(resp, result);
				} else {
					System.out.println("打卡失败！");
					result.put("success", "false");
					result.put("errorMsg", "打卡失败"+ATime);
				} 
			} catch (Exception e) {
				result.put("success", "false");
				result.put("errorMsg", "打卡失败"+ATime);
				e.printStackTrace();
			}finally {
				DbUtils.closeCon(con);
			}
			
		}else{
			try {
				staff = new Staff();
				staff.setSname(Sname);
				Date eDate = DateUtil.formatString(ATime, "yyyy-MM-dd HH:mm:ss");
				String yString=DateUtil.formatDate(new Date(), "yyyy-MM-dd");
				Date sDate = DateUtil.formatString(yString+" 18:30:00", "yyyy-MM-dd HH:mm:ss");
				if (eDate.before(sDate)) {
					WType = "早退";
				} else if (eDate.after(sDate)) {
					WType = "正常";
				} else {
					WType = "旷工";
				}
				result = new JSONObject();
				staffWorkR = new StaffWorkR();
				staffWorkR.setSname(Sname);
				staffWorkR.setETime(eDate);
				staffWorkR.setWType(WType);
				int punchLeaveWork = staffWorkRDao.PunchLeaveWork(con, staffWorkR);
				if(punchLeaveWork>0){
					result.put("success", "true");
					System.out.println("打卡成功！");
					result.put("Msg", "打卡成功"+ATime);
					ResponseUtil.write(resp, result);
				}else{
					System.out.println("打卡失败！");
					result.put("success", "false");
					result.put("errorMsg", "打卡失败"+ATime);
					ResponseUtil.write(resp, result);
				}
			} catch (Exception e) {
				result.put("success", "false");
				result.put("errorMsg", "打卡失败"+ATime);
				
				e.printStackTrace();
			}finally {
				DbUtils.closeCon(con);
			}
		}
		
		
		
	}

}
