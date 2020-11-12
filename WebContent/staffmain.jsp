<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主 页</title>
</head>

<%
	//权限验证
	if(session.getAttribute("currentUser")==null){
		response.sendRedirect("staffLogin.jsp");
		return;
	}
%>


<link rel="stylesheet" type="text/css" href="jquery-easyui-1.7.0/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.7.0/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.7.0/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	$(function () {
		//数据
		if('${currentUser.weight}'==5){
			var treeData1=[{
				text:"职工",
				children:[{
					text:"职工信息",
					attributes:{
						url:"staff/showStaffMessage.jsp"
					}
				},{
					text:"工作",
					attributes:{
						url:"staff/staffWorking.jsp"
					}
				},{
					text:"打卡",
					attributes:{
						url:"staff/punchcard.jsp"
					}
				}]
			
			}];
			
			//实例化树菜单
			$("#staffwork").tree({
				data:treeData1,
				lines:true,
				onClick:function(node){
					if(node.attributes){
						openTab(node.text,node.attributes.url);
					}
					
				}
			});
			
		}else{
			location.href="staffLogin.jsp";
		}
		//新增Tab
		function openTab(text,url) {
			var content="<iframe frameborder='0' scrolling='auto' style='width:100%; height:100%;'  src="+url+"></iframe>"
			if($("#tabs").tabs('exists',text)){
				$("#tabs").tabs('select',text);
			}else{
				$("#tabs").tabs("add",{
					title:text,
					closable:true,
					content:content
				});
			}
			
		}
	});
	
	function loginOut() {
		var result = confirm("确定要退出吗？");
		if(result){
			location.href="http://localhost:8080/EnterpriseManager/loginOut?weight="+${currentUser.weight};
		}
	}
</script>

</head>
<style>
	
	a:link{color:orange;}

	a:visited{color:black;}

	a:hover{color:orange;}

	a:active{color:#ccc;}	
	
	a{
		text-decoration:none;
	}

</style>

<body class="easyui-layout">
    <div region="north" style="height: 80px; background: #00c7f0">
		<div align="left" style="width:80%; float:left"><img src="images/main.png"></div>
		<div style="padding-top: 50px;padding-right: 20px;">
		当前用户：&nbsp;<font color="red">${currentUser.sname}</font> 
		<a href="javascript:loginOut()" style="margin-left: 25px; ">退出登录</a>
		</div>	
	</div>
    <div region="center" >
		<div class="easyui-tabs" fit="true" id="tabs">
			<div title="首页">
				<div align="center" style="padding-top: 150px">
				<font color="red" size="10">
				 欢迎使用&nbsp;&nbsp;&nbsp;权限为${currentUser.weight}
				</font>
				</div>
			</div>
		</div>
	</div>
	
    <div region="west" style="width:150px;" title="导航菜单" split="true">
		<ul id="staffwork"></ul>
	</div>
	
    <div region="south" height="25px" align="center">版权所有 <a href="main.jsp">计科1712 2017117102 操星驰  2017117150 程春丽  2017117147 李斯</a></div>
	
</body>
</html>