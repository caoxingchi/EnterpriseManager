<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购买界面</title>

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

		function searchProduct() {
			$('#dg').datagrid('load',{
				Pname:$('#s_ProductName').val()
			});
		}
		
		function resetValue(){
			$("#Pname").val("");
			$("#buyNum").val("");
		}
		
		function closeProductDialog(){
			$("#dlg").dialog("close");
			resetValue();
		}
		
		function buyProduct() {
			//给功能框加入一个标题
			var selectedRows=$("#dg").datagrid('getSelections');
			if(selectedRows.length!=1){
				$.messager.alert("系统提示","请选择一个要买的商品！");
				return;
			}
			var row=selectedRows[0];
			$("#dlg").dialog("open").dialog("setTitle","购买商品");
			
			$("#fm").form("load",row);
			url="http://localhost:8080/EnterpriseManager/buyProduct?pid="+row.id+"&cName=${currentUser.cName}"
					+"&BuyRecored="+row.Pname+"&count=${buyNum}";
			
		}
</script>

<script type="text/javascript"
	src="../jquery-easyui-1.7.0/loading.js">
</script>
<script type="text/javascript" src="../jquery-easyui-1.7.0/deletePerformed.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.7.0/buyProductSave.js"></script>
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

<div id="dlg" class="easyui-dialog" style="width: 400px;height: 280px; padding: 10px 20px" 
	closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td>商品：</td>
					<td><input type="text" name="Pname" id="Pname" 
					class="easyui-validatebox" readonly/></td>
				</tr>	
				<tr>
					<td>购买数量：</td>
					<td><input class="easyui-validatebox" name="buyNum" id="buyNum"  required="true"/></td>
				</tr>
			</table>
		</form>
		
	</div>
	<div id="tb">
		<div>
			<a href="javascript:buyProduct()"
				class="easyui-linkbutton" iconCls="icon-add" plain="true">购买</a>
		</div>
		<div>&nbsp;产品名称：&nbsp;<input type="text" id="s_ProductName" class="s_ProductName"/>
	<a href="javascript:searchProduct()" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a></div>
	</div>
	
		<div id="dlg-buttons">
		<a href="javascript:saveInfo()" class="easyui-linkbutton" 
		iconCls="icon-ok">购买</a>
		<a href="javascript:closeProductDialog()" class="easyui-linkbutton" 
		iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>