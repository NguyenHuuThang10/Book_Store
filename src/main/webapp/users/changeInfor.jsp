<%@page import="java.util.Date"%>
<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Register</title>
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
	Object obj = session.getAttribute("user");
	User user = null;

	if (obj != null) {
		user = (User) obj;
	}

	if (user == null) {
	%>
	<jsp:forward page="login.jsp"></jsp:forward>
	<%
	} else {
	String errors = request.getAttribute("errors") + "";
	errors = (errors.equals("null")) ? "" : errors;

	String success = request.getAttribute("success") + "";
	success = (success.equals("null")) ? "" : success;

	String fullname = user.getFullname();

	String sex = user.getSex();

	Date birthday = user.getBirthday();

	String address = user.getAddress();

	String deliveryAddress = user.getDeliveryAddress();

	String purchaseAddress = user.getPurchaseAddress();

	String phone = user.getPhone();

	String email = user.getEmail();

	Boolean emailNews = user.isEmailNews();
	%>
	<div class="container mt-3">
		<div class="text-center">
			<h1>Thông tin cá nhân</h1>
		</div>



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
		<form class="form" action="<%= url %>/user" method="post">
			<input type="hidden" name="action" value="changeInfor">
			<div class="row">
				<div class="col-sm-6">
					<h3>Thông tin khách hàng</h3>
					<div class="mb-3">
						<label for="fullname" class="form-label">Họ và tên</label> <input
							type="text" class="form-control" id="fullname" name="fullname"
							value="<%=fullname%>">
					</div>
					<div class="mb-3">
						<label for="sex" class="form-label">Giới tính</label> <select
							class="form-control" id="sex" name="sex">
							<option></option>
							<option value="Nam"
								<%=(sex.equalsIgnoreCase("Nam")) ? "selected" : ""%>>Nam</option>
							<option value="Nữ"
								<%=(sex.equalsIgnoreCase("Nữ")) ? "selected" : ""%>>Nữ</option>
							<option value="Khác"
								<%=(sex.equalsIgnoreCase("Khác")) ? "selected" : ""%>>Khác</option>
						</select>
					</div>
					<div class="mb-3">
						<label for="birthday" class="form-label">Ngày sinh</label> <input
							type="date" class="form-control" id="birthday" name="birthday"
							value="<%=birthday%>">
					</div>
				</div>
				<div class="col-sm-6">
					<h3>Địa chỉ</h3>
					<div class="mb-3">
						<label for="address" class="form-label">Địa chỉ khách hàng</label>
						<input type="text" class="form-control" id="address"
							name="address" value="<%=address%>">
					</div>
					<div class="mb-3">
						<label for="deliveryAddress" class="form-label">Địa chỉ
							nhận hàng </label> <input type="text" class="form-control"
							id="deliveryAddress" name="deliveryAddress"
							value="<%=deliveryAddress%>">
					</div>
					<div class="mb-3">
						<label for="purchaseAddress" class="form-label">Địa chỉ
							mua hàng</label> <input type="text" class="form-control"
							id="purchaseAddress" name="purchaseAddress"
							value="<%=purchaseAddress%>">
					</div>
					<div class="mb-3">
						<label for="phone" class="form-label">Điện thoại</label> <input
							type="tel" class="form-control" id="phone" name="phone"
							value="<%=phone%>">
					</div>
					<div class="mb-3">
						<label for="email" class="form-label">Email</label> <input
							type="email" class="form-control" id="email" name="email"
							value="<%=email%>">
					</div>
					<hr />
					<div class="mb-3">
						<label for="emailNews" class="form-label">Đồng ý nhận
							email</label> <input type="checkbox" class="form-check-input"
							id="emailNews" name="emailNews" <%=(emailNews ? "checked" : "")%>>
					</div>
					<a href="../index.jsp" class="btn btn-primary">Quay lại</a>
					<button type="submit" class="btn btn-success" name="submit"
						id="submit">Xác nhận</button>
				</div>
			</div>
		</form>
	</div>
	<%
	}
	%>
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