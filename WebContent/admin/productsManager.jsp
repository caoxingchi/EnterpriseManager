<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品信息</title>
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

<script type="text/javascript">
		function searchProduct() {
			$('#dg').datagrid('load',{
				Pname:$('#s_ProductName').val()
			});
		}
		
		function modifyProduct(){
			var selectedRows=$("#dg").datagrid('getSelections');
			if(selectedRows.length!=1){
				$.messager.alert("系统提示","请选择一条要编辑的数据！");
				return;
			}
			var row=selectedRows[0];
			$("#dlg").dialog("open").dialog("setTitle","编辑产品信息");
			$("#fm").form("load",row);
			url="http://localhost:8080/EnterpriseManager/productSave?Pid="+row.id;
			
		}
		
		function resetValue(){
			$("#Pname").val("");
			$("#Pnum").val("");
			$("#Pprice").val("");
			$("#Ptype").val("");
		}
		
		
		function closeProductDialog(){
			$("#dlg").dialog("close");
			resetValue();
		}
		
		function addProduct() {
			//给功能框加入一个标题
			$("#dlg").dialog("open").dialog("setTitle","添加订单信息");
			url="http://localhost:8080/EnterpriseManager/productSave";
		}
</script>

<%
	//权限验证
	if(session.getAttribute("currentUser")==null){
		response.sendRedirect("index.jsp");
		return;
	}
%>
<script type="text/javascript"
	src="../jquery-easyui-1.7.0/loading.js">
</script>
<script type="text/javascript" src="../jquery-easyui-1.7.0/deletePerformed.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.7.0/saveInfo.js"></script>
<body style="margin: 5px;">

<div id="loading" style="position:absolute;z-index:1000;top:0px;left:0px;width:100%;height:100%;background:#DDDDDB;text-align :center;padding-top:20%;">
     <h1><font color="#15428B">加载中....</font></h1>
</div> 
<table id="dg" title="产品信息" class="easyui-datagrid" pagination="true"  fitColumns="true" 
rownumbers="true" fit="true" url="http://localhost:8080/EnterpriseManager/productsList" toolbar="#tb">
	<thead>
		<tr>
			<th field="cb" checkbox="true"></th>
			<th field="id" width="50">编号</th>
			<th field="Pname" width="100">产品名称</th>
			<th field="Ptype" width="100">产品类型</th>
			<th field="Pnum" width="100">产品数量</th>
			<th field="Pprice" width="100">产品价格</th>
		</tr>
	</thead>
</table>
<div id="tb">
	<div>
		<a href="javascript:addProduct()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		<a href="javascript:modifyProduct()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		<a href="javascript:deletePerformed('productDelete')" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	</div>
	<div>&nbsp;产品名称：&nbsp;<input type="text" id="s_ProductName" class="s_ProductName"/>
	<a href="javascript:searchProduct()" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a></div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 400px;height: 280px; padding: 10px 20px" 
	closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td>产品名称：</td>
					<td><input type="text" name="Pname" id="Pname" 
					class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>产品类型：</td>
					<td><input class="easyui-validatebox" type="text" name="Ptype" id="Ptype" /></td>
					<td></td>
				</tr>	
				<tr>
					<td>产品数量：</td>
					<td><input class="easyui-validatebox" name="Pnum" id="Pnum" required="true" /></td>
				</tr>
				<tr>
					<td>产品价格：</td>
					<td><input type="text" name="Pprice" id="Pprice" 
					class="easyui-validatebox" required="true"/>元</td>
					
				</tr>
			</table>
		</form>
		
	</div>
	
		<div id="dlg-buttons">
		<a href="javascript:saveInfo()" class="easyui-linkbutton" 
		iconCls="icon-ok">保存</a>
		<a href="javascript:closeProductDialog()" class="easyui-linkbutton" 
		iconCls="icon-cancel">关闭</a>
	</div>
	
</body>
</html>