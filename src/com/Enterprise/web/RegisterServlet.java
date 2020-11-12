
package com.Enterprise.web;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.Enterprise.dao.CustomerDao;
import com.Enterprise.model.Customer;
import com.Enterprise.utils.DbUtils;

/**
 *
 * @author Administrator
 */
public class RegisterServlet extends HttpServlet {
	Connection con;
	 DbUtils db=new DbUtils();
     Customer customer=new Customer();
     String msg;
    public void wrong1(){
        msg="不允许有空，注册失败！";      
    }
    public void wrong2(){
        msg="两次密码不同，注册失败！";      
    }
    public void wrong3(){
        msg="用户名已存在，注册失败！";
        
    }
    public void right(){
        msg="注册信息合格，注册成功！";      
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	String userName=new String(request.getParameter("username").getBytes("ISO-8859-1"),"UTF-8");
        String password=new String(request.getParameter("password").getBytes("ISO-8859-1"),"UTF-8");
        String confirmPassword=new String(request.getParameter("confirmPassword").getBytes("ISO-8859-1"),"UTF-8");
        String sex=new String(request.getParameter("sex").getBytes("ISO-8859-1"),"UTF-8");
        String phone=new String(request.getParameter("telnum").getBytes("ISO-8859-1"),"UTF-8");
        String place=new String(request.getParameter("address").getBytes("ISO-8859-1"),"UTF-8");
        String email=new String(request.getParameter("email").getBytes("ISO-8859-1"),"UTF-8");
        if(userName.length()==0||password.length()==0||confirmPassword.length()==0||phone.length()==0||email.length()==0){
            wrong1();
            request.setAttribute("error", msg);
            request.getRequestDispatcher("register/register.jsp").forward(request, response);
            return;
        }else if(!(password.equals(confirmPassword))){
            wrong2();
            request.setAttribute("error", msg);
            request.getRequestDispatcher("register/register.jsp").forward(request, response);
            return;
        }else{
            try{
                customer.setcName(userName);
                customer.setcTel(phone);
                customer.setEmail(email);
                customer.setAddress(place);
                customer.setPassword(password);
                customer.setSex(sex);
                customer.setWeight(1);
                CustomerDao cDao=new CustomerDao();
                con=db.getConn();
                boolean isExist=cDao.isExistCustomer(customer, con);
                if(isExist){
                    wrong3();
                    request.setAttribute("error", msg);
                    request.getRequestDispatcher("register/register.jsp").forward(request, response);
                    return;
                }else{
                    cDao.Register(con, customer);
                }
                right();
              
                response.sendRedirect("http://localhost:8080/EnterpriseManager/index.jsp");
                
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
