<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员工</title>
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
	
		function modifyStaff(){
			var selectedRows=$("#dg").datagrid('getSelections');
			if(selectedRows.length!=1){
				$.messager.alert("系统提示","请选择一条要编辑的数据！");
				return;
			}
			var row=selectedRows[0];
			$("#dlg").dialog("open").dialog("setTitle","编辑产品信息");
			$("#fm").form("load",row);
			url="http://localhost:8080/EnterpriseManager/staffSave?id="+row.id;
			
		}
	
	
		function searchStaff() {
			$('#dg').datagrid('load',{
				Sname:$('#s_StaffName').val()
			});
		}
		
		
		function resetValue(){
			$("#Sname").val("");
			$("#department").val("");
			$("#SworkingDate").datebox("setValue","");
			$("#Spostion").val("");
			$("#salary").val("");
		}
		
		function closeStaffDialog(){
			$("#dlg").dialog("close");
			resetValue();
		}

		function addStaff() {
			//给功能框加入一个标题
			$("#dlg").dialog("open").dialog("setTitle","添加员工");
			url="http://localhost:8080/EnterpriseManager/staffSave";
		}
</script>
<script type="text/javascript"
	src="../jquery-easyui-1.7.0/loading.js">
</script>
<script type="text/javascript" src="../jquery-easyui-1.7.0/deletePerformed.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.7.0/saveInfo.js"></script>
<body style="margin: 5px;">

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
<table id="dg" title="员工信息" class="easyui-datagrid" pagination="true"  fitColumns="true" 
rownumbers="true" fit="true" url="http://localhost:8080/EnterpriseManager/staffList" toolbar="#tb">
	<thead>
		<tr>
			<th field="cb" checkbox="true"></th>
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

<div id="tb">
	<div>
		<a href="javascript:addStaff()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		<a href="javascript:modifyStaff()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		<a href="javascript:deletePerformed('staffDelete')" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	</div>
	<div>&nbsp;员工姓名：&nbsp;<input type="text" id="s_StaffName" class="s_StaffName"/>
	<a href="javascript:searchStaff()" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a></div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 400px;height: 280px; padding: 10px 20px" 
	closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td>员工姓名：</td>
					<td><input type="text" name="Sname" id="Sname" 
					class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>部门：</td>
					<td><input class="easyui-validatebox" type="text" name="department" id="department" required="true"/></td>
					<td></td>
				</tr>	
				
				<tr>
					<td>入职时间：</td>
					<td><input class="easyui-datebox" name="SworkingDate" id="SworkingDate" required="true" editable="false" /></td>
				</tr>
				
				<tr>
					<td>职位：</td>
					<td><input class="easyui-validatebox" type="text" name="Spostion" id="Spostion" required="true" /></td>
				</tr>
				
				<tr>
					<td>工资：</td>
					<td><input type="text" name="salary" id="salary" 
					class="easyui-validatebox" required="true"/>元</td>
					
				</tr>
			</table>
		</form>
		
	</div>
	
		<div id="dlg-buttons">
		<a href="javascript:saveInfo()" class="easyui-linkbutton" 
		iconCls="icon-ok">保存</a>
		<a href="javascript:closeStaffDialog()" class="easyui-linkbutton" 
		iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>