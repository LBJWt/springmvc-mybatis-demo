<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
<head>
<title>用户列表</title>
<script type="text/javascript" src="${ctx}/js/common/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/common/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/common/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/js/common/easyui-util.js"></script>
<script type="text/javascript" src="${ctx}/js/user/user.js"></script>
<link rel="stylesheet" href="${ctx}/js/common/easyui/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="${ctx}/js/common/easyui/themes/icon.css" type="text/css"></link>
</head>

<body>
	<!-- jsp文件名，方便查找页面对应的jsp文件 -->
	<input id="jspPage" type="hidden" value="view/user/userList.jsp">
	<input id="ctx" type="hidden" value="${ctx}">

	<div align="center">
		<div style="display:inline-block">
			<!-- 用户列表表格 -->
			<table id="userDatagrid" style="width:700px"></table>
			<!-- 异常和退出按钮 -->
			<div style="margin-top:10px" align="right">
				<a class="easyui-linkbutton" data-options="iconCls:'icon-no'" onclick="toAjaxException()">Ajax异常</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-no'" onclick="toForwardException()">跳转异常</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-back'" onclick="toLogin()">返回登录</a>
			</div>
		</div>
		<!-- 当前用户和查询表单 -->
		<div style="display:inline-block; position:fixed;">
			当前用户：<c:out value="${user.name}"></c:out>
			<div style="border:1px solid grey; margin:10px">
				<!-- 查询表单 -->
				<form id="searchForm" method="post">
					<table style="padding:10px;">
						<tr>
							<td style="text-align:center" colspan=2>模糊查询</td>
						</tr>
						<tr>
							<td style="text-align:right">用户名：</td>
							<td>
								<input name="name" type="text" style="width:85px">
							</td>
						</tr>
						<tr>
							<td style="text-align:right">邮箱：</td>
							<td>
								<input name="email" type="text" style="width:85px">
							</td>
						</tr>
						<tr>
							<td></td>
							<td style="text-align:right">
								<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchUser()"></a>
								<a class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick="empty()"></a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>

	<div id="addDialog" style="padding:10px">
		<div align="center">
		<!-- 添加用户表单 -->
		<form id="addForm" method="post">
			<table>
				<tr>
					<td align="right">用户名：</td>
					<td>
						<input id="addName" class="easyui-validatebox" type="text" name="name" onblur="addCheckName('addName')" onkeydown="removeCheckName('addName')" data-options="required:true, missingMessage:'请输入用户名'">
					</td>
				</tr>
				<tr>
					<td align="right">电子邮箱：</td>
					<td>
						<input id="addEmail" class="easyui-validatebox" name="email" data-options="required:true, missingMessage:'请填写电子邮箱', validType:'email'">
					</td>
				</tr>
				<tr>
					<td align="right">密码：</td>
					<td>
						<input id="addPassword" class="easyui-validatebox" type="password" name="password" data-options="required:true, missingMessage:'请输入密码'">
					</td>
				</tr>
				<tr>
					<td align="right">确认密码：</td>
					<td>
						<input class="easyui-validatebox" type="password" data-options="required:true, missingMessage:'请再次输入密码', validType:'eqPwd[\'#addPassword\']'">
					</td>
				</tr>
			</table>
		</form>
		</div>
	</div>

	<div id="editDialog" style="padding:10px">
		<div align="center">
		<!-- 编辑用户表单 -->
		<form id="editForm" method="post">
			<input id="editId" type="hidden" name="id" />
			<table>
				<tr>
					<td align="right">用户名：</td>
					<td>
						<input id="editName" class="easyui-validatebox" type="text" name="name" onblur="addCheckName('editName')" onkeydown="removeCheckName('editName')" data-options="required:true, missingMessage:'请输入用户名'">
					</td>
				</tr>
				<tr>
					<td align="right">电子邮箱：</td>
					<td>
						<input id="editEmail" class="easyui-validatebox" name="email" data-options="required:true, missingMessage:'请填写电子邮箱', validType:'email'">
					</td>
				</tr>
				<tr>
					<td align="right">密码：</td>
					<td>
						<input id="editPassword" class="easyui-validatebox" type="password" name="password" data-options="required:true, missingMessage:'请输入密码'">
					</td>
				</tr>
				<tr>
					<td align="right">确认密码：</td>
					<td>
						<input class="easyui-validatebox" value="123" type="password" data-options="required:true, missingMessage:'请再次输入密码', validType:'eqPwd[\'#editPassword\']'">
					</td>
				</tr>
			</table>
		</form>
		</div>
	</div>

</body>
</html>
