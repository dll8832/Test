function formatState(value, row, index) {
	
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

function searchSaleChance() {
	var customerName = $("#s_customerName").val();
	var overview = $("#s_overview").val();
	var createMan = $("#s_createMan").val();
	var state = $("#s_state").combobox('getValue');
	$('#dg').datagrid('reload',{
		customerName: customerName,
		overview: overview,
		createMan: createMan,
		state: state
	});


}