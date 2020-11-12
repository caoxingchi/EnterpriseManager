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
		response.sendRedirect("index.jsp");
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
		
		if('${currentUser.weight}'==1){
		var treeData=[{
			text:"用户操作",
			children:[{
				text:"个人信息",
				attributes:{
					url:"customer/showCustomerMessage.jsp"
				}
			},{
				text:"商店",
				attributes:{
					url:"customer/BuyProducts.jsp"
				}
			},{
				text:"已购买的产品",
				attributes:{
					url:"customer/MyProducts.jsp"
				}
			}]
		
		}];
		
		//实例化树菜单
		$("#tree").tree({
			data:treeData,
			lines:true,
			onClick:function(node){
				if(node.attributes){
					openTab(node.text,node.attributes.url);
				}
				
			}
		});
		}else if('${currentUser.weight}'==10){
		var treeData1=[{
			text:"客户",
			children:[{
				text:"客户信息",
				attributes:{
					url:"admin/AllCustomerMessage.jsp"
				}
			},{
				text:"售后信息",
				attributes:{
					url:"admin/customerBuyRecoreds.jsp"
				}
			}]
		
		}];
		
		//实例化树菜单
		$("#customer").tree({
			data:treeData1,
			lines:true,
			onClick:function(node){
				if(node.attributes){
					openTab(node.text,node.attributes.url);
				}
				
			}
		});
		
		var treeData2=[{
			text:"产品信息",
			children:[{
					text:"产品管理",
					attributes:{
						url:"admin/productsManager.jsp"
					}
				}]
		}];
		
		//实例化树菜单
		$("#product").tree({
			data:treeData2,
			lines:true,
			onClick:function(node){
				if(node.attributes){
					openTab(node.text,node.attributes.url);
				}
				
			}
		});
		
		var treeData3=[{
			text:"员工信息",
			children:[{
				text:"员工信息",
				attributes:{
					url:"admin/staffManage.jsp"
				}
			
			},{
				text:"员工考勤",
				attributes:{
					url:"admin/StaffWorkRecoreds.jsp"
				}
			
			}]
		
		}];
		
		//实例化树菜单
		$("#staff").tree({
			data:treeData3,
			lines:true,
			onClick:function(node){
				if(node.attributes){
					openTab(node.text,node.attributes.url);
				}
				
			}
		});
	}else{
		location.href="index.jsp";
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
<style>
	a:link{color:orange;}

	a:visited{color:black;}

	a:hover{color:orange;}

	a:active{color:#ccc;}	
	
	a{
		text-decoration:none;
	}
</style>
</head>
<body class="easyui-layout">
    <div region="north" style="height: 80px; background: #00c7f0">
		<div align="left" style="width:80%; float:left"><img src="images/main.png"></div>
		<div style="padding-top: 50px;padding-right: 20px;">
		当前用户：&nbsp;<font color="red">${currentUser.cName}</font> 
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
    <div region="west" style="width: 150px;" title="导航菜单" split="true">
		<ul id="tree"></ul>
		<ul id="customer"></ul>
		<ul id="product"></ul>
		<ul id="staff"></ul>
		<ul id="staffwork"></ul>
	</div>
    <div region="south" height="25px" align="center">版权所有 <a href="main.jsp">计科1712 2017117102 操星驰  2017117150 程春丽  2017117147 李斯</a></div>
	
</body>
</html>