<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
<head>
<title>登录</title>
<script type="text/javascript" src="${ctx}/js/common/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/common/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/common/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/js/user/login.js"></script>
<link rel="stylesheet" href="${ctx}/js/common/easyui/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="${ctx}/js/common/easyui/themes/icon.css" type="text/css"></link>
</head>

<body>
	<!-- jsp文件名，方便查找页面对应的jsp文件 -->
	<input id="jspPage" type="hidden" value="view/user/login.jsp">
	<input id="ctx" type="hidden" value="${ctx}">

	<div align="center">
		<!-- 登录表单 -->
		<form id="loginForm" method="post">
			<table>
				<tr>
					<td align="right">用户名：</td>
					<td>
						<input class="easyui-validatebox" type="text" name="name" data-options="required:true, missingMessage:'请输入用户名'" value="tykyuser">
					</td>
				</tr>
				<tr>
					<td align="right">密码：</td>
					<td>
						<input class="easyui-validatebox" type="password" name="password" data-options="required:true, missingMessage:'请输入密码'" value="123">
					</td>
				</tr>
				<tr>
					<td align="right">
						<a class="easyui-linkbutton" onclick="login()">登录</a>
					</td>
					<td align="right">
						没有账号？去
						<a href="${ctx}/user/toRegister.do">注册</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
