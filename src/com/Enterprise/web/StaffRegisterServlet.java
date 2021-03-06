/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Enterprise.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.Enterprise.dao.StaffDao;
import com.Enterprise.model.Staff;
import com.Enterprise.utils.DbUtils;

/**
 *
 * @author Administrator
 */
public class StaffRegisterServlet extends HttpServlet {
	Connection con;
	 DbUtils db=new DbUtils();
    Staff staff=new Staff();
    String msg;
    public void wrong1(){
       msg="不允许有空，注册失败！";

    }
    public void wrong2(){
        msg="两次密码不同，注册失败！";
    }
    public void wrong3(){
       msg="不存在该用户名，注册失败！";

    }
    public void right(){
       msg="注册员工，注册成功！";

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	String userName=new String(request.getParameter("username").getBytes("ISO-8859-1"),"UTF-8");
        String password=new String(request.getParameter("password").getBytes("ISO-8859-1"),"UTF-8");
        String confirmPassword=new String(request.getParameter("confirmPassword").getBytes("ISO-8859-1"),"UTF-8");
        String sex=new String(request.getParameter("sex").getBytes("ISO-8859-1"),"UTF-8");
        String age=new String(request.getParameter("age").getBytes("ISO-8859-1"),"UTF-8");
        String address=new String(request.getParameter("address").getBytes("ISO-8859-1"),"UTF-8");
        String edu=new String(request.getParameter("edu").getBytes("ISO-8859-1"),"UTF-8");
        String phone=new String(request.getParameter("telnum").getBytes("ISO-8859-1"),"UTF-8");
        if(userName.length()==0||password.length()==0||
        		confirmPassword.length()==0||age.length()==0||sex.length()==0
        		||address.length()==0||phone.length()==0||edu.length()==0
        		){
            wrong1();
            request.setAttribute("error", msg);
            request.getRequestDispatcher("register/Staffregister.jsp").forward(request, response);
            return;

        }else if(!(password.equals(confirmPassword))){
            wrong2();
            request.setAttribute("error", msg);
            request.getRequestDispatcher("register/Staffregister.jsp").forward(request, response);
            return;
        }else{
            try{
               
                staff.setSname(userName);
                staff.setPassword(password);
                staff.setAge(Integer.parseInt(age));
                staff.setSex(sex);
                staff.setTelnum(phone);
                staff.setAddress(address);
                staff.setEdu(edu);
                staff.setWeight(5);
                StaffDao sDao=new StaffDao();
                con=db.getConn();
                
                boolean isExist=sDao.isExistStaff(staff, con);
                if(!isExist){
                    wrong3();
                    request.setAttribute("error", msg);
                    request.getRequestDispatcher("register/Staffregister.jsp").forward(request, response);
                    return;
                }else{
                    int r=sDao.Register(staff,con);
                    if(r>0){
                    	 right();
                    	response.sendRedirect("http://localhost:8080/EnterpriseManager/staffLogin.jsp");
                    }else{
                    	request.setAttribute("error", "注册失败！");
                    	request.getRequestDispatcher("register/Staffregister.jsp").forward(request, response);
                    	return;
                    }
                }
               
            }catch(Exception e){
                e.printStackTrace();
            }finally{
            	DbUtils.closeCon(con);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

   
}
