$(function() {
	var ctx = $('#ctx').val();
	// 初始化注册表单
	$('#registerForm').form({
		url : ctx + '/user/register.do',
		success : function(data) {
			var result = jQuery.parseJSON(data);
			if (result.success) {
				window.location = ctx + "/user/toUserList.do";
			} else {
				$.messager.show({
					title : "注册错误",
					msg : result.msg
				});
			}
		}
	});
});

function register() {
	$('#registerForm').form('submit');
}