function deletePerformed(str) {
  //获取所有的选中行,获取的是一个集合
	var selectedRows=$("#dg").datagrid('getSelections');
	if(selectedRows.length==0){
		//这个是确认框，起到的只有提示作用
		$.messager.alert("系统提示","请选择要删除的数据！");
		return;
	}
	var strIds=[];
	for(var i=0;i<selectedRows.length;i++){
		if(str=='customerDelete'){
   		 if(selectedRows[i].cName=='admin'){
   	            $.messager.alert("系统提示","管理员不能删除！","error");
   	            return;
   	        }
   	}
		strIds.push(selectedRows[i].id);
	}
	var ids=strIds.join(",");
	$.messager.confirm("系统提示","您确定要删除这<font color=red>"
			+selectedRows.length+"</font>条数据吗？",function(r){
		if(r){
			$.post("http://localhost:8080/EnterpriseManager/"+str,{delIds:ids},function(result){
				if(result.success){
					$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNum+"</font>条数据！");
					//这句话的作用是刷新
					$("#dg").datagrid("reload");
				}else if(!result.success){
					$.messager.alert("系统提示",result.errorMsg);
				}
			},"json");
		}
	});

}