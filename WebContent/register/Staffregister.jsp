<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注 册</title>
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
<style>

	a:link{color:green;}

	a:visited{color:black;}

	a:hover{color:green;}

	a:active{color:#ccc;}	
	
	
	#back{
		background-image: url('../images/test.jpg');
		width:100%;
		height:100%;
	}
	
	
	
</style>

<body id="back">
<div align="center" id="main" style="padding-top: 35px;">
	<div class="login_logo">
              <span id="denglu" style="font-size: 30px; "><a style='text-decoration:none;' href="register.jsp">员&nbsp;&nbsp;工 &nbsp;&nbsp;注&nbsp;&nbsp;册</a></span>
    </div>
	<form action="http://localhost:8080/EnterpriseManager/staffRegister" method="post">
		<table border=1>
		<tr>
			<td>用户名</td>
			<td><input class="easyui-textbox" name="username"></td>
		</tr>
		
		<tr>
			<td>密码</td>
			<td><input class="easyui-passwordbox" name="password"></td>
		</tr>
		
		<tr>
			<td>确认密码</td>
			<td><input class="easyui-passwordbox" name="confirmPassword"></td>
		</tr>
		
		<tr>
			<td>性别</td>
			<td>
				<input type="radio" name="sex" value="男" checked>男&nbsp;&nbsp;&nbsp;
				<input type="radio" name="sex" value="女">女
			</td>
		</tr>
		
		<tr>
			<td>年龄</td>
			<td><input class="easyui-textbox" name="age"></td>
		</tr>
		
		<tr>
			<td>学历</td>
			<td><input class="easyui-textbox" name="edu"></td>
		</tr>
		
		<tr>
			<td>电话</td>
			<td><input class="easyui-textbox" name="telnum"></td>
		</tr>
				
		<tr>
			<td>地址</td>
			<td><input class="easyui-textbox" name="address"
						data-options="multiline:true" style="height: 100px; width: 200px"></input>
					</td>
		</tr>
		
		<tr>
			<td><input class="easyui-linkbutton"
						style="width: 100px; height: 30px" type="submit" value="提交">
					</td>
					<td><input class="easyui-linkbutton"
						style="width: 100px; height: 30px" type="reset" value="重置">
						&nbsp;&nbsp;
						<a  style="text-decoration:none;" href="http://localhost:8080/EnterpriseManager/staffLogin.jsp">返回登录</a>
					</td>
		</tr>
		<tr></tr>
		
		<tr height="20">
			<td></td>
					<td><font color="red">${error } ${success }</font></td>
		</tr>
	</table>
	</form>
	
		
</div>

</body>
</html>