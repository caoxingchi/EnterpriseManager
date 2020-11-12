<%@page import="com.Enterprise.model.Customer"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>展示客户信息</title>
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
		function modify(){
			$("#dlg").dialog("open").dialog("setTitle","编辑个人信息");
			url="http://localhost:8080/EnterpriseManager/customerModify?cName=${currentUser.cName}";
		}
		
		function resetValue(){
			$("#cTel").val("");
			$("#email").val("");
			$("#password").val("");
			$("#sex").combobox("setValue","");
			$("#confirmpassword").val("");
			$("#address").val("");
		}
		
		function closeDialog(){
			$("#dlg").dialog("close");
		}
</script>
<script type="text/javascript"
	src="../jquery-easyui-1.7.0/loading.js">
</script>
<script type="text/javascript" src="../jquery-easyui-1.7.0/saveInfo.js"></script>
<body>
<div id="loading" style="position:absolute;z-index:1000;top:0px;left:0px;width:100%;height:100%;background:#DDDDDB;text-align :center;padding-top:20%;">
     <h1><font color="#15428B">加载中....</font></h1>
</div> 
<div id="tb">
	<div>
		<a href="javascript:modify()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
	</div>
	
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
<table id="dg" title="个人信息" class="easyui-datagrid" pagination="true"  fitColumns="true" 
rownumbers="true" fit="true" url="http://localhost:8080/EnterpriseManager/customerList?cName=${currentUser.cName }" toolbar="#tb">
	<thead>
		<tr>
			<th field="cName" height="60" width="100">姓名</th>
			<th field="sex" height="60"  width="100">性别</th>
			<th field="cTel" height="60" width="100">手机号码</th>
			<th field="cEmail"height="60"  width="100">email</th>
			<th field="cAddress"height="60"  width="100">地址</th>
		</tr>
	</thead>
</table>
        
        <div id="dlg" class="easyui-dialog" style="width: 400px;height: 280px; padding: 10px 20px" 
	closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table>
			
				<tr>
					<td>电话：</td>
					<td><input class="easyui-validatebox" type="text" name="cTel" id="cTel" required="true"/></td>
					<td></td>
				</tr>
				
				<tr>
					<td>密码：</td>
					<td><input class="easyui-validatebox" type="password" name="password" id="password" required="true"/></td>
					<td></td>
				</tr>	
				
				<tr>
					<td>确认密码：</td>
					<td><input class="easyui-validatebox"  type="password" name="confirmpassword" id="confirmpassword" required="true" /></td>
				</tr>
				
				<tr>
					<td>用户性别：</td>
					<td><select class="easyui-combobox" id="sex" name="sex" editable="false" panelHeight="auto" style="width: 144px">
					    <option value="">请选择...</option>
						<option value="男">男</option>
						<option value="女">女</option>
					</select></td>
				</tr>
				
				<tr>
					<td>Email：</td>
					<td><input type="text" name="email" id="email" 
					class="easyui-validatebox" /></td>
					
				</tr>
				
				<tr>
					<td>地址：</td>
					<td><input type="text" name="address" id="address" 
					class="easyui-validatebox" /></td>
					
				</tr>
			</table>
		</form>
		
	</div>
    
    	<div id="dlg-buttons">
		<a href="javascript:saveInfo()" class="easyui-linkbutton" 
		iconCls="icon-ok">保存</a>
		<a href="javascript:closeDialog()" class="easyui-linkbutton" 
		iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>