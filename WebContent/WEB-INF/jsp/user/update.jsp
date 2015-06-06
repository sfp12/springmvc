<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<sf:form method="post" modelAttribute="user">
	username:<sf:input path="username" value="${user.username}"/><sf:errors path="username" /><br />
	nickname:<sf:input path="nickname" value="${user.nickname}"/><sf:errors path="nickname" /><br />
	password:<sf:input path="password" value="${user.password}"/><sf:errors path="password" /><br />
	email:<sf:input path="email" value="${user.email}"/><sf:errors path="email" /><br />
	<input type="submit" value="update user" /><br />
</sf:form>
</body>
</html>