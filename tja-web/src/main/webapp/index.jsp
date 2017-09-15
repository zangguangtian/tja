<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="Refresh" content="0;url=<%=basePath%>/admin/dashboard">
<title>首页</title>
</head>
<body>
</body>
</html>