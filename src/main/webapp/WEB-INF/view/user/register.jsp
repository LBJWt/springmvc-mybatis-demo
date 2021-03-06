<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
<head>
<title>注册</title>
<script type="text/javascript" src="${ctx}/js/common/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/common/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/common/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/js/common/easyui-util.js"></script>
<script type="text/javascript" src="${ctx}/js/user/register.js"></script>
<link rel="stylesheet" href="${ctx}/js/common/easyui/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="${ctx}/js/common/easyui/themes/icon.css" type="text/css"></link>
</head>

<body>
	<!-- jsp文件名，方便查找页面对应的jsp文件 -->
	<input id="jspPage" type="hidden" value="view/user/register.jsp">
	<input id="ctx" type="hidden" value="${ctx}">

	<div align="center">
		<!-- 注册表单 -->
		<form id="registerForm" method="post">
			<table>
				<tr>
					<td align="right">用户名：</td>
					<td>
						<input id="registerName" class="easyui-validatebox" type="text" name="name" onblur="addCheckName('registerName')" onkeydown="removeCheckName('registerName')" data-options="required:true, missingMessage:'请输入用户名', validType:'checkName'" value="tykyuser">
					</td>
				</tr>
				<tr>
					<td align="right">电子邮箱：</td>
					<td>
						<input id="registerEmail" class="easyui-validatebox" name="email" data-options="required:true, missingMessage:'请填写电子邮箱', validType:'email'" value="tykyuser@tyky.com">
					</td>
				</tr>
				<tr>
					<td align="right">密码：</td>
					<td>
						<input id="registerPassword" class="easyui-validatebox" type="password" name="password" data-options="required:true, missingMessage:'请输入密码'" value="123">
					</td>
				</tr>
				<tr>
					<td align="right">确认密码：</td>
					<td>
						<input class="easyui-validatebox" type="password" data-options="required:true, missingMessage:'请再次输入密码', validType:'eqPwd[\'#registerPassword\']'" value="123">
					</td>
				</tr>
				<tr>
					<td align="right">
						<a class="easyui-linkbutton" onclick="register()">注册</a>
					</td>
					<td>
						<a href="${ctx}/user/toLogin.do">返回登录</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
