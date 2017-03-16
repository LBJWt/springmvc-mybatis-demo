$(function() {
	var ctx = $('#ctx').val();
	// 初始化登录表单
	$('#loginForm').form({
		url : ctx + '/user/login.do',
		success : function(data) {
			var result = jQuery.parseJSON(data);
			if (result.success) {
				window.location = ctx + "/user/toUserList.do";
			} else {
				$.messager.show({
					title : "登录错误",
					msg : result.msg
				});
			}
		}
	});
});

function login() {
	$('#loginForm').form('submit');
}