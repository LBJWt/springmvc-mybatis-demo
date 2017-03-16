$(function() {
	// 初始化用户列表表格
	$('#userDatagrid').datagrid({
		url : $('#ctx').val() + '/user/listUser.do',
		fitColumns : true,
		pagination : true,
		idField : 'id',
		selectOnCheck : true,
		checkOnSelect : true,
		columns : [ [ {
			field : 'id',
			title : 'id',
			checkbox : true
		}, {
			field : 'name',
			title : '用户名',
			width : 100,
			align : 'center'
		}, {
			field : 'email',
			title : '邮箱',
			width : 200,
			align : 'center'
		}, {
			field : 'createDate',
			title : '创建时间',
			width : 200,
			align : 'center'
		}, {
			field : 'modifyDate',
			title : '修改时间',
			width : 200,
			align : 'center'
		}, {
			field : 'operate',
			title : '操作',
			width : 100,
			align : 'center',
			formatter : function(value, row, index) {
				// 提供操作按钮
				return '<a href="#" onclick="edit(' + index
						+ ')">编辑</a> | '
						+ '<a href="#" onclick="removeOne('
						+ index + ')">删除</a>';
			}
		} ] ],
		toolbar : [ {
			iconCls : 'icon-add',
			text : '添加',
			handler : function() {
				add();
			}
		}, '-', {
			iconCls : 'icon-remove',
			text : '批量删除',
			handler : function() {
				removeSelected();
			}
		} ],
		onDblClickRow : function(index, row) {
			edit(index);
		}
	});

	// 初始化添加用户对话框
	$('#addDialog').dialog({
		title : '添加用户',
		width : 270,
		buttons : [ {
			text : '保存',
			iconCls : 'icon-save',
			handler : function() {
				$('#addForm').form('submit');
			}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				$('#addDialog').dialog('close');
			}
		} ]
	});
	// 用方法主动关闭，打开时对话框不会变形
	$('#addDialog').dialog('close');

	// 初始化编辑用户对话框
	$('#editDialog').dialog({
		title : '编辑用户',
		width : 270,
		buttons : [ {
			text : '保存',
			iconCls : 'icon-save',
			handler : function() {
				$('#editForm').form('submit');
			}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				$('#editDialog').dialog('close');
			}
		} ],
	});
	// 用方法主动关闭，打开时对话框不会变形
	$('#editDialog').dialog('close');
});

// 响应添加按钮，初始化添加用户表单，打开添加用户对话框
function add() {
	initAddForm();
	$('#addDialog :input').val("");
	$('#addDialog').dialog('open');
	$('#addForm').form('submit');// 提交一次让提示信息都显示出来
}

// 初始化添加用户表单
function initAddForm() {
	$('#addForm').form({
		url : $('#ctx').val() + '/user/add.do',
		success : function(data) {
			var result = jQuery.parseJSON(data);// 返回对象要转为json
			if (result.success) {
				$('#userDatagrid').datagrid('appendRow', result.obj);// 添加成功后，在datagrid当前页的尾部加上新添加的数据
				$('#addDialog').dialog('close');
				$.messager.show({
					title : "提示",
					msg : result.msg
				});
			} else {
				$.messager.show({
					title : "提示",
					msg : result.msg
				});
			}
		}
	});
}

// 响应编辑按钮，初始化编辑用户表单，打开编辑用户对话框
function edit(index) {
	$('#userDatagrid').datagrid('selectRow', index);// 点击按钮会选择所在行，再选一次取消选中
	var row = $('#userDatagrid').datagrid('getRows')[index];// 获取选中的列
	initEditForm(index, row);
	$('#editForm').form('load', row);
	$('#editPassword').val('123');
	$('#editForm').form('validate');
	$('#editDialog').dialog('open');
	$('#editName').focus();
}

// 初始化编辑用户表单
function initEditForm(index, row) {
	$('#editId').val(row.id);
	$('#editForm').form({
		url : $('#ctx').val() + '/user/edit.do',
		success : function(data) {
			var result = jQuery.parseJSON(data);
			if (result.success) {
				$('#userDatagrid').datagrid('updateRow', {// 编辑成功后，在datagrid中更新该记录
					index : index,
					row : result.obj
				});
				$('#editDialog').dialog('close');
				$.messager.show({
					title : "提示",
					msg : result.msg
				});
			} else {
				$.messager.show({
					title : "提示",
					msg : result.msg
				});
			}
		}
	});
}

// 删除一条记录
function removeOne(index) {
	$('#userDatagrid').datagrid('selectRow', index);// 点击按钮会选择所在行，再选一次取消选中
	var row = $('#userDatagrid').datagrid('getRows')[index];// 获取选中的列
	$.messager.confirm('确认', '确定要删除用户【' + row.name + '】吗？', function(r) {
		if (r) {
			$.ajax({
				url : $('#ctx').val() + '/user/remove.do',
				type : 'post',
				async : 'false',
				dataType : 'json',
				data : {
					id : row.id
				},
				success : function(data) {
					if (data.success) {
						$('#userDatagrid').datagrid('deleteRow', index);// 删除成功后，在datagrid中也删除该记录
					}
					$.messager.show({
						title : "提示",
						msg : data.msg
					});
				}
			});
		}
	});
}

// 删除勾选的记录
function removeSelected() {
	var rows = $('#userDatagrid').datagrid('getChecked');// 获取勾选的列
	if (rows.length > 0) {
		$.messager.confirm('确认', '确定要删除这' + rows.length + '个用户吗？', function(r) {
			if (r) {
				var ids = "";
				for (i = 0; i < rows.length; i++) {
					ids += rows[i].id + ",";
				}
				ids = ids.substring(0, ids.length - 1);
				$.ajax({
					url : $('#ctx').val() + '/user/remove.do',
					type : 'post',
					async : 'false',
					dataType : 'json',
					data : {
						ids : ids
					},
					success : function(data) {
						if (data.success) {
							$('#userDatagrid').datagrid('reload');// 删除成功后重新加载datagrid
						}
						$.messager.show({
							title : "提示",
							msg : data.msg
						});
					}
				});
			}
		});
	}
}

// 执行查询
function searchUser() {
	$('#userDatagrid').datagrid('load', serializeObject($('#searchForm')));
}

// 清空查询并重新加载datagrid
function empty() {
	$('#searchForm :input').val('');
	$('#userDatagrid').datagrid('load', "{}");
}

// 跳转到登录页面
function toLogin() {
	window.location = $('#ctx').val() + '/user/logout.do';
}

// 抛出异步异常
function toAjaxException() {
	$.ajax({
		url : $('#ctx').val() + '/user/ajaxException.do',
		type : 'post',
		success : function(data) {
			var res = jQuery.parseJSON(data);
			if (res.success) {
				//noway
			} else {
				$.messager.show({
					title : '错误提示',
					msg : res.msg
				});
			}
		}
	});
}

// 抛出同步异常
function toForwardException() {
	window.location = $('#ctx').val() + '/user/forwardException.do';
}