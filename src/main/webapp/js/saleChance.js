function formatState(value) {
	
//	JSON.parse(json字符串); eval
//	console.log(value + '----' + JSON.stringify(row) + '---' + index);
	if(value == null) {
		return '未知';
	} else if (value == 0) {
		return '未分配';
	} else if (value == 1) {
		return '已分配';
	}
}
function searchSaleChance(){
	var customerName = $("#s_customerName").val();
	var overview = $("#s_overview").val();
	var createMan = $("#s_createMan").val();
	var state = $("#s_state").combobox('getValue');
	
	$("#dg").datagrid('reload',{
		customerName: customerName,
		overview: overview,
		createMan: createMan,
		state: state
	});
}

//弹出添加的弹出框
function openSaleChanceAddDialog() {
	$('#dlg').dialog('open').dialog('setTitle', "添加营销机会");    
}
function closeSaleChanceDialog(){
	resultValue();
	$("#dlg").dialog("close");
	
}
//重置
function resultValue(){
	
	$("#fm").form('reset');
}

//保存营销机会
function saveSaleChance(){
	var customerName=$("#customerId").combobox('getText');
	if(customerName==null||$.trim(customerName).length==0){
		alert('请输入用户来执行添加。。。');
	}
	
	$("#customerName").val(customerName);

	var id = $("#id").val();
	var url = "add";
	if (id != null) {
		url = "update";
	}
	
	$('#fm').form('submit',{    
	    url:url,    
	    onSubmit: function(){    
	          return $(this).form('validate');
	    },    
	    success:function(data){ 
	    	data = JSON.parse(data); // 转化为json对象 弱对象
	        if (data.resultCode==1) {
	        	//提示消息
	        	$.messager.alert('提示',data.result);    
	        	//置空
	        	resultValue();
	        	//关闭
	        	$("#dlg").dialog("close");
	        	//重新刷新一次
	        	$("#dg").datagrid('reload');
			}
	    }    
	}); 
	
}


//打开修改窗体
function openSaleChanceModifyDialog() {
	// 必须选中一条记录
	var selectedRows = $("#dg").datagrid('getSelections');
	if (selectedRows.length != 1) {
		$.messager.alert("提示", "请选择一行进行修改");
		return;
	}
	// 选中行的数据复制给form
	var row = selectedRows[0];
	console.log(JSON.stringify(row));
	$("#fm").form('load', row);
	var createMan = $.cookie('userName');
	$("#createMan").val(createMan);
	$("#dlg").dialog('open').dialog('setTitle', '修改营销机会');
}


//删除
function deleteSaleChance() {
	// 先判断是否选中
	var ids = [];
	// 获取选中行
	var selectedRows = $("#dg").datagrid('getSelections');
	if (selectedRows.length == 0) {
		$.messager.alert("提示", "请选择行进行删除");
		return;
	}
	for (var i = 0; i < selectedRows.length; i++) {
		var row = selectedRows[i];
		ids.push(row.id);
	}
	console.log(ids + "---" + ids.join(','));
	
	$.messager.confirm('确认删除', '您确定要删除<span style="color:red">' + ids.length + "</span>条记录吗？", function(r){
		if (r) {
			$.ajax({
	    		url:'del',
	    		type:'post',
	    		dataType:'json',
	    		data:{ids:ids.join(',')},
	    		success:function(data){
	    			if (data.resultCode==0) {
	    				$.messager.alert("提示", data.resultMessage);
					}else{
						$.messager.alert("提示", data.result);
						$("#dg").datagrid('reload');
					}
	    		}
	    		
			});
		}
	});
}





/*//删除
function deleteSaleChance(){
	// 先判断是否选中
	var ids = [];
	
	//获取选中行
	var selectRows=$("#dg").datagrid('getSelections');
	if (selectRows.length==0) {
		alert('请选中行进行删除。。。');
		return;
	}

	for(var i=0,i<selectRows.length,i++){
		var row = selectRows[i];
		ids.push(row.id);
	}
	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
	    if (r){    
	        //ajax 请求删除
	    	$.ajax({
	    		url:'del',
	    		data:{ids:ids.join(',')},
	    		success:function(data){
	    			if (data.resultCode==0) {
	    				$.messager.alert("提示", resp.resultMessage);
					}else{
						$.messager.alert("提示", resp.result);
						$("#dg").datagrid('reload');
					}
	    		}
	    		
	    		
	    		
	    		
	    	})
	    }    
	});  
	
}*/