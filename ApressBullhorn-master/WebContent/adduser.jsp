<!-- adduser.jsp -->
<%@ page language="java" contentType="text/html;
    charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<fmt:setLocale value="en_US" /><!-- fixes date not displaying correctly in Eclipse browser -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>BullHorn</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
</head>
<body>

<form action="AddUser" method="post">
    <input type="hidden" name="action" value="addUser">
	<h1>Add New User</h1>
	<h2>Name: <input type="text" name="userName" value=""/></h2>
	<h2>Email: <input type="text" name="userEmail" value=""/></h2>
	<h2>Password: <input type="password" name="userPassword" value=""/></h2>
	<h2>Motto: <input type="text" name="userMotto" value=""/></h2>
	<!-- <h2>Join Date: <input type="text" value=""/></h2>-->
	<input type="submit" value="Join Us"/>
</form>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>