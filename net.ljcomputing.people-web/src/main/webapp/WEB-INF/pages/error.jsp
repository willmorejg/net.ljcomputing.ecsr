<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error Page</title>
</head>
<body>
	<pre>Timestamp: <fmt:formatDate value="${requestScope.timestamp}" pattern="MMM d, yyyy h:m:s a"/></pre>
	<pre>Status Code: ${requestScope.status}</pre>
	<pre>Error Reported: ${requestScope.error}</pre>
	<pre>Message: ${requestScope.message}</pre>
	<pre>Path Requested: ${requestScope.path}</pre>
</body>
</html>