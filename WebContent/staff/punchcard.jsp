<%@page import="java.util.Date"%>
<%@page import="com.Enterprise.model.Staff"%>
<%@page import="com.Enterprise.utils.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工打卡</title>
<link rel="stylesheet" type="text/css"
	href="../jquery-easyui-1.7.0/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="../jquery-easyui-1.7.0/themes/icon.css">
<script type="text/javascript"
	src="../jquery-easyui-1.7.0/jquery.min.js"></script>
<script type="text/javascript"
	src="../jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="../jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js"></script>
</head>
<%
String date=null;
try{
	date=DateUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"); 
	System.out.print(date);
}catch(Exception e){
	e.printStackTrace();
}

Staff staff=(Staff)session.getAttribute("currentUser");
%>
<script type="text/javascript">
	function punchCard_Sworking(){	

		url="http://localhost:8080/EnterpriseManager/staffWorking?date=<%=date %>&type=startW&name=<%=staff.getSname()%>";
		$.messager.confirm("系统提示","上班 正在打卡。。。",function(r){
			if(r){
				$.post(url,function(result){
					if(result.success){
						$.messager.alert("系统提示",result.Msg);
					}else if(!result.success){
						$.messager.alert("系统提示",result.errorMsg);
					}
				},"json");
			}
		});
		
	}
	
	function punchCard_Lworking(){

		url="http://localhost:8080/EnterpriseManager/staffWorking?date=<%=date %>&type=leaveW&name=<%=staff.getSname()%>";
		$.messager.confirm("系统提示","下班 正在打卡。。。",function(r){
			if(r){
				$.post(url,function(result){
					if(result.success){
						$.messager.alert("系统提示",result.Msg);
					}else if(!result.success){
						$.messager.alert("系统提示",result.errorMsg);
					}
				},"json");
			}
		});
		
	}
	
	
	
</script>
<body >

<%
	//权限验证
	if(session.getAttribute("currentUser")==null){
		response.sendRedirect("staffLogin.jsp");
		return;
	}
%>
<script type="text/javascript"
	src="../jquery-easyui-1.7.0/loading.js">
</script>
<div id="loading" style="position:absolute;z-index:1000;top:0px;left:0px;width:100%;height:100%;background:#DDDDDB;text-align :center;padding-top:20%;">
     <h1><font color="#15428B">加载中....</font></h1>
</div> 
<div align="center" style="padding-top: 35px;">
		<div class="login_logo">
	              <span id="register" style="font-size: 30px; "><a href="staffLogin.jsp" style='text-decoration:none;'>用&nbsp;&nbsp;户&nbsp;&nbsp;打&nbsp;&nbsp;卡</a></span>
	    </div>
		<form action="#"  method="post">
			<table align="center" width="500" height="300" >
				<tr height="100">
					<td colspan="2"></td>
				</tr>
				<tr height="20">
				<td></td>
				<td></td>
				<td></td>
					<td><a href="javascript:punchCard_Sworking()" class="easyui-linkbutton" 
		data-options="iconCls:'icon-large-picture',size:'large',iconAlign:'top'">上班</a></td>
				</tr>
				<tr height="40"></tr>
				<tr height="20">
				<td></td>
				<td></td>
				<td></td>
					<td><a href="javascript:punchCard_Lworking()" class="easyui-linkbutton" 
		data-options="iconCls:'icon-large-smartart',size:'large',iconAlign:'top'">下班</a></td>
				</tr>
				<tr height="40"></tr>
				<tr height="20">
					<td ></td>
					<td ></td>
					<td colspan="3"><font color="red">${error }</font></td>
				</tr>
				<tr height="40"></tr>
			</table>
		</form>
	</div>
	
</body>
</html>