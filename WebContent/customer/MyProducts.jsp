<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购买的产品</title>
</head>


<%
	//权限验证
	if(session.getAttribute("currentUser")==null){
		response.sendRedirect("index.jsp");
		return;
	}
%>
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

<script type="text/javascript">
	var url;
	//通过这个方法来获取查询的操作
	function searchOrder() {
		$('#dg').datagrid('load', {
			BPnum : $('#BPnum').val(),
			BuyRecored : $('#BuyRecored').val(),
		});
	}
	function openOrderModifyDialog() {
		var selectedRows = $("#dg").datagrid('getSelections');
	 	if (selectedRows.length != 1) {
			$.messager.alert("系统提示", "请选择一条要编辑的数据！");
			return;
		} 
		var row = selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle", "评价");
		$("#fm").form("load", row);
		url = "http://localhost:8080/EnterpriseManager/RecoredSave?Pid=" + row.id;
	}

	function resetValue(){
		$("#BPnum").val("");
		$("#suggestion").val("");
	}
	
	
	function closeProductDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
</script>
<script type="text/javascript"
	src="../jquery-easyui-1.7.0/loading.js">
</script>
<script type="text/javascript" src="../jquery-easyui-1.7.0/deletePerformed.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.7.0/saveInfo.js"></script>
<body style="margin: 5px;">

<div id="loading" style="position:absolute;z-index:1000;top:0px;left:0px;width:100%;height:100%;background:#DDDDDB;text-align :center;padding-top:20%;">
     <h1><font color="#15428B">加载中....</font></h1>
</div> 
	<table id="dg" title="订单信息" class="easyui-datagrid" fitColumns="true"
		pagination="true" rownumbers="true" url="http://localhost:8080/EnterpriseManager/recoredList?customer=${currentUser.cName }" fit="true"
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
	<div id="tb">
		<div>
			<a href="javascript:openOrderModifyDialog()"
				class="easyui-linkbutton" iconCls="icon-edit" plain="true">反馈</a> <a
				href="javascript:deletePerformed('recoredDelete')" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>
			&nbsp;订单号：&nbsp;<input type="text" name="BPnum" id="BPnum"
				size="10" /> &nbsp;商品名：&nbsp;<input type="text"
				name="BuyRecored" id="BuyRecored" size="10" /> &nbsp;<a
				href="javascript:searchOrder()" class="easyui-linkbutton"
				iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width: 570px; height: 350px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table cellspacing="5px;">
				<tr>
					<td>商品号：</td>
					<td><input type="text" name="BPnum" id="BPnum" readonly /></td>
				</tr>
				<tr>
					<td valign="top">反馈信息：</td>
					<td colspan="4"><textarea rows="7" cols="50" name="suggestion"
							id="suggestion"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	
		</div>
	
		<div id="dlg-buttons">
		<a href="javascript:saveInfo()" class="easyui-linkbutton" 
		iconCls="icon-ok">保存</a>
		<a href="javascript:closeProductDialog()" class="easyui-linkbutton" 
		iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>