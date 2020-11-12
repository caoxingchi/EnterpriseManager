function saveInfo() {
    $("#fm").form("submit",{
        //这是一个异步提交的地址
        //对应上面的url=这句
        url:url,
        
        //下面的这句话在提交之前会执行的
        onSubmit:function(){
            //这里的this是指的form这个对象
            //就是在提交的时候，调用validate这个方法
            return $(this).form("validate");
        },
        success:function(result){
            //如果出现错误
            if(result.errorMsg){
                //把错误提示一下
                $.messager.alert("系统提示",result.errorMsg,"error");
                //结束方法
                return;
            }else{
                $.messager.alert("系统提示","保存成功!");
                //清空框体中的内容
                resetValue();
                //成功保存了之后就关掉这个框，同上面的关闭一样
                $("#dlg").dialog("close");
                //这句话的作用是刷新，重新加载
                $("#dg").datagrid("reload");
            }
        }
    });
}