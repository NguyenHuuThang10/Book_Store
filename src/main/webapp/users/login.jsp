<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Login</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">
<style>
.red {
	color: red;
}
</style>
</head>
<body>
	<jsp:include page="../partials/header.jsp" />
	<%
	Object obj = session.getAttribute("user");
	User user = null;

	if (obj != null) {
		user = (User) obj;
	}

	if (user != null) {
	%>
	<jsp:forward page="index.jsp"></jsp:forward>
	<%
	}
	%>
	
	<%
	String errors = request.getAttribute("errors") + "";
	errors = (errors.equals("null")) ? "" : errors;

	String success = request.getAttribute("success") + "";
	success = (success.equals("null")) ? "" : success;
	
	String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	%>
	
	
	<div class="container">
		<h1 class="text-center">Đăng nhập</h1>

		<form method="post" action="<%= url %>/user" style="width: 50%; margin: 0 auto;">
			<%
				if (success != null && !success.isEmpty()) {
			%>
				<div class="alert alert-success" role="alert">
					<%=success%>
				</div>

			<%
				}
				if (errors != null && !errors.isEmpty()) {
			%>
				<div class="alert alert-danger" role="alert">
					<%=errors%>
				</div>

			<%
				}
			%>
			<input type="hidden" name="action" value="login">
			<div class="form-group">
				<label for="name">Tên đăng nhập</label> <input type="text"
					class="form-control" id="name" name="name">
			</div>
			<div class="form-group">
				<label for="password">Mật khẩu</label> <input type="password"
					class="form-control" id="password" name="password">
			</div>

			</br>
			<button type="submit" class="btn btn-success">Đăng nhập</button>
			<a href="../index.jsp" class="btn btn-primary">Quay lại</a>
			<hr>
			<div class="text-center">
				<a href="register.jsp">Đăng ký tài khoản</a>
			</div>
		</form>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
		crossorigin="anonymous"></script>
</body>
</html>