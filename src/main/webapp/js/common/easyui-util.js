// 扩展validatebox，验证确认密码是否与密码一致
$.extend($.fn.validatebox.defaults.rules, {
	eqPwd : {
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : "两次输入的密码不一致！"
	}
});

// 扩展validatebox，验证用户名是否可以使用
$.extend($.fn.validatebox.defaults.rules, {
	checkName : {
		validator : function(value) {
			return checkUserName(value);
		},
		message : "该用户已存在！"
	}
});

// 用ajax远程验证用户名是否可以使用，参数为用户名
function checkUserName(value) {
	var result;
	var id = $('#editId').val();// 编辑用户时，原来的用户名是可用的，要获取id作为查询条件
	if (!id) {
		id = 0;
	}
	$.ajax({
		url : $('#ctx').val() + '/user/checkUserName.do',
		async : false,
		type : "post",
		dataType : "json",
		data : {
			id : id,
			name : value
		},
		success : function(data) {
			result = data.success;
		}
	});
	return result;
}

// 添加用户名验证，参数为输入框id
function addCheckName(id) {
	$('#' + id).validatebox({
		required : true,
		validType : 'checkName'
	});
}

// 移除用户名验证，参数为输入框id
function removeCheckName(id) {
	$('#' + id).validatebox({
		required : false,
		validType : ''
	});
}

// 表单序列化，可用于重新加载datagrid，参数为表单对象
function serializeObject(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (this['value'] != undefined && this['value'].length > 0) {// 如果表单项的值非空，才进行序列化操作
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + this['value'];
			} else {
				o[this['name']] = this['value'];
			}
		}
	});
	return o;
};