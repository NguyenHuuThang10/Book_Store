<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Reset password</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">
<style>
.red {
	color: red;
}

.user-avatar {
	width: 30px;
	border-radius: 50%;
	height: 30px;
}
</style>
</head>
<body>
	<jsp:include page="../partials/header.jsp" />

	<%
	String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String errors = request.getAttribute("errors") + "";
	errors = (errors.equals("null")) ? "" : errors;

	String success = request.getAttribute("success") + "";
	success = (success.equals("null")) ? "" : success;
	%>
	
	<%
		Object obj = session.getAttribute("user");
		User user = null;
		
		if (obj != null) {
			user = (User)obj;
		}
		
		if(user == null){
	
	%>
		<jsp:forward page="login.jsp"></jsp:forward>
	<%
		}
	%>
	<div class="container">
		<h1 class="text-center">Đặt lại mật khẩu</h1>

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
			<input type="hidden" name="action" value="reset">
			<div class="form-group">
				<label for="oldPassword">Mật khẩu cũ</label> <input type="password"
					class="form-control" id="oldPassword" name="oldPassword" required="required">
			</div>
			<div class="form-group">
				<label for="newPassword">Mật khẩu mới</label> <input type="password"
					class="form-control" id="newPassword" name="newPassword" required="required" onkeyup="checkMatchPass()">
			</div>
			<div class="form-group">
				<label for="confirmNewPassword">Nhập lại mật khẩu mới</label> <input type="password"
					class="form-control" id="confirmNewPassword" name="confirmNewPassword" required="required" onkeyup="checkMatchPass()">
					<span class="red" id="msg"></span>
			</div>

			</br>
			<button type="submit" class="btn btn-success">Xác nhận</button>
			<a href="<%= url %>/index.jsp" class="btn btn-primary">Quay lại</a>
		</form>

	</div>
	
	<script type="text/javascript">
		function checkMatchPass() {
			let newPass = document.getElementById("newPassword").value;
			let confirmPass = document.getElementById("confirmNewPassword").value;
			
			if(newPass != confirmPass) {
				document.getElementById("msg").innerHTML = "Nhập lại mật khẩu không trùng khớp!";
				return false;
			}else{
				document.getElementById("msg").innerHTML = "";
				return true;
			}
		}
		
	
	</script>	
		

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