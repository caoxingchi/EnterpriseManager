<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>企业信息管理系统</title>
<script type="text/javascript">
	function resetValue() {
		document.getElementById("userName").value = "";
		document.getElementById("password").value = "";
	}
</script>

<link rel="stylesheet" type="text/css" href="jquery-easyui-1.7.0/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.7.0/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.7.0/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js"></script>

<style>

	a:link{color:red;}

	a:visited{color:black;}

	a:hover{color:red;}

	a:active{color:#ccc;}

		#main{
		background-image: url('images/test.jpg');
		width:100%;
		height:100%;
	}
	
	#re{
		color:red;
	}
</style>
</head>

<body id="main">
	<div align="center" style="padding-top: 35px;">
		<div class="login_logo">
	              <span id="login" style="font-size: 30px; "><a href="index.jsp" style='text-decoration:none;'>员&nbsp;&nbsp;工&nbsp;&nbsp;登&nbsp;&nbsp;录</a></span>
	    </div>
		<form action="StaffLogin"  method="post">
			<table align="center" width="500" height="400">
				<tr height="100">
					<td colspan="2"></td>
				</tr>
				<tr height="20">
					<td width="30%"></td>
					<td width="10%">用户名：</td>
					<td width="10%"><input type="text" class="easyui-textbox" style="width:200px;height: 35px" value="${userName }" name="userName"
						id="userName"></td>
						<td ><input type="button" id="re" class="easyui-linkbutton" value="注册"   style="width:80px;height: 25px;" onclick="javascript:window.location.href='register/Staffregister.jsp'"></td>
					<td width="30%"></td>
				</tr>
				<tr height="30"></tr>
				<tr height="20">
					<td width="30%"></td>
					<td width="10%">密 码：</td>
					<td><input type="password" class="easyui-textbox" style="width:200px;height: 35px" value="${password }"
						name="password" id="password"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40"></tr>
				<tr height="20">
					<td width="40%"></td>
					<td width="10%"><input type="submit" class="easyui-linkbutton"  style="width:100px;height: 35px" value="登录" /></td>
					<td></td>
					<td><input type="button" class="easyui-linkbutton" value="重置"   style="width:100px;height: 35px" onclick="resetValue()">
					</td>
					<td width="20%"></td>
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