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
<title>员工信息</title>

<script type="text/javascript"
	src="../jquery-easyui-1.7.0/loading.js">
</script>

</head>

<%
	//权限验证
	if(session.getAttribute("currentUser")==null){
		response.sendRedirect("staffLogin.jsp");
		return;
	}
%>

<%Staff staff=(Staff)session.getAttribute("currentUser");
%>
<body style="margin: 10px;">
<script type="text/javascript">


</script>
<div id="loading" style="position:absolute;z-index:1000;top:0px;left:0px;width:100%;height:100%;background:#DDDDDB;text-align :center;padding-top:20%;">
     <h1><font color="#15428B">加载中....</font></h1>
</div> 
<hr noshade>
            <div align="center">
                <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
                    <tr>
                    	<td width="42%"></td>
                        <td >
                         	   查看个人信息
                        </td>
                    </tr>
                </table>
            </div>
            <hr noshade>
            <br><br>
<table id="dg" title="员工信息" class="easyui-datagrid" pagination="true"  fitColumns="true" 
rownumbers="true" fit="true" url="http://localhost:8080/EnterpriseManager/staffList?Sname=<%=staff.getSname() %>" toolbar="#tb">

	<thead>
		<tr>
			<th field="id" width="50">编号</th>
			<th field="Sname" width="100">员工姓名</th>
			<th field="sex" width="100">性别</th>
			<th field="age" width="100">年龄</th>
			<th field="edu" width="100">学历</th>
			<th field="STel" width="100">电话号码</th>
			<th field="department" width="100">部门</th>
			<th field="SworkingDate" width="70">入职时间</th>
			<th field="Spostion" width="100">职位</th>
			<th field="salary" width="100">工资</th>
		</tr>
	</thead>	
	
</table>
</body>
</html>