<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/resources/css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<p>${loginUser.username }</p>
<c:forEach items="${users}" var="um">
	${um.value.username }
	--${um.value.nickname }
	--${um.value.password }
	--${um.value.email }	
	--<a href="${um.value.username}/update">update</a>
	--<a href="${um.value.username}/delete">delete</a>
	<br />
</c:forEach>
</body>
</html>