<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>全部客户</title>
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
	//权限验证
	if(session.getAttribute("currentUser")==null){
		response.sendRedirect("index.jsp");
		return;
	}
%>
<script type="text/javascript">

		function searchCustomer() {
			$('#dg').datagrid('load',{
				cName:$('#s_CustomerName').val()
			});
		}
</script>

<script type="text/javascript"
	src="../jquery-easyui-1.7.0/loading.js">
</script>

<script type="text/javascript" src="../jquery-easyui-1.7.0/deletePerformed.js">
	
</script>

<body style="margin: 5px;">

<div id="loading" style="position:absolute;z-index:1000;top:0px;left:0px;width:100%;height:100%;background:#DDDDDB;text-align :center;padding-top:20%;">
     <h1><font color="#15428B">加载中....</font></h1>
</div> 
<table id="dg" title="客户信息" class="easyui-datagrid" pagination="true"  fitColumns="true" 
rownumbers="true" fit="true" url="http://localhost:8080/EnterpriseManager/customerList" toolbar="#tb">
	<thead>
		<tr>
			<th field="cb" checkbox="true"></th>
			<th field="id" width="50">编号</th>
			<th field="cName" width="100">客户姓名</th>
			<th field="sex" width="100">性别</th>
			<th field="cTel" width="100">联系方式</th>
			<th field="cAddress" width="100">地址</th>
			<th field="cEmail" width="100">电子邮箱</th>
		</tr>
	</thead>
</table>
<div id="tb">
	<div>
		
		<a href="javascript:deletePerformed('customerDelete')" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	</div>				
	<div>&nbsp;客户姓名：&nbsp;<input type="text" id="s_CustomerName" class="s_CustomerName"/>
	<a href="javascript:searchCustomer()" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a></div>
</div>
</body>
</html>