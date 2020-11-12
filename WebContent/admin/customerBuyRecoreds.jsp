<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户售后</title>
</head>
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
<script type="text/javascript"
	src="../jquery-easyui-1.7.0/loading.js">
</script>
<script type="text/javascript" src="../jquery-easyui-1.7.0/deletePerformed.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.7.0/buyProductSave.js"></script>
<body>

<%
	//权限验证
	if(session.getAttribute("currentUser")==null){
		response.sendRedirect("index.jsp");
		return;
	}
%>
<div id="loading" style="position:absolute;z-index:1000;top:0px;left:0px;width:100%;height:100%;background:#DDDDDB;text-align :center;padding-top:20%;">
     <h1><font color="#15428B">加载中....</font></h1>
</div> 
	<table id="dg" title="订单信息" class="easyui-datagrid" fitColumns="true"
		pagination="true" rownumbers="true" url="http://localhost:8080/EnterpriseManager/recoredList" fit="true"
		toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="id" width="50" align="center">编号</th>
				<th field="BPnum" width="100" align="center">订单号</th>
				<th field="BuyRecored" width="100" align="center">商品名</th>
				<th field="count" width="100" align="center">数量</th>
				<th field="Amount" width="100" align="center">总花费</th>
				<th field="cName" width="100" align="center">所属用户</th>
				<th field="suggestion" width="150" align="center">反馈</th>
				<th field="time" width="100" align="center">订单日期</th>
			</tr>
		</thead>
	</table>
</body>
</html>