<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
<head>
<title>后台异常</title>
<script type="text/javascript" src="${ctx}/js/common/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/common/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" href="${ctx}/js/common/easyui/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="${ctx}/js/common/easyui/themes/icon.css" type="text/css"></link>
</head>

<body>
	<!-- jsp文件名，方便查找页面对应的jsp文件 -->
	<input id="jspPage" type="hidden" value="view/error/backException.jsp">
	<div align="center">
		${msg}<br><br>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-back'" onclick="history.back();">返回</a>
	</div>
</body>
</html>
