<%@page import="com.Enterprise.model.Staff"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<title>员工工作</title>
</head>
<script type="text/javascript"
	src="../jquery-easyui-1.7.0/loading.js">
</script>
<body>

<%
	//权限验证
	if(session.getAttribute("currentUser")==null){
		response.sendRedirect("staffLogin.jsp");
		return;
	}
Staff staff=(Staff)session.getAttribute("currentUser");
System.out.print(staff.getSname());
%>
<div id="loading" style="position:absolute;z-index:1000;top:0px;left:0px;width:100%;height:100%;background:#DDDDDB;text-align :center;padding-top:20%;">
     <h1><font color="#15428B">加载中....</font></h1>
</div> 
<table id="dg" title="员工信息" class="easyui-datagrid" pagination="true"  fitColumns="true" 
rownumbers="true" fit="true" url="http://localhost:8080/EnterpriseManager/staffWorkRList?Sname=<%=staff.getSname() %>" toolbar="#tb">
	<thead>
		<tr>
			<th field="id" width="50">编号</th>
			<th field="Sname" width="100">员工姓名</th>
			<th field="WType" width="100">考勤</th>
			<th field="department" width="100">部门</th>
			<th field="Spostion" width="100">职位</th>
			<th field="ATime" width="100">打卡时间</th>
			<th field="ETime" width="100">下班时间</th>
			<th field="STel" width="100">电话号码</th>
		</tr>
	</thead>
</table>
</body>
</html>