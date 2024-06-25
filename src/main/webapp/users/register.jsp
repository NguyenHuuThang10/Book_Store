<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Register</title>
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
		String errors = request.getAttribute("errors")+"";
		errors = (errors.equals("null"))?"":errors;
	
		String name = request.getAttribute("name")+"";
		name = (name.equals("null"))?"":name;
	
		String fullname = request.getAttribute("fullname")+"";
		fullname = (fullname.equals("null"))?"":fullname;
		
		String sex = request.getAttribute("sex")+"";
		sex = (sex.equals("null"))?"":sex;
		
		String birthday = request.getAttribute("birthday")+"";
		birthday = (birthday.equals("null"))?"":birthday;
		
		String address = request.getAttribute("address")+"";
		address = (address.equals("null"))?"":address;
		
		String deliveryAddress = request.getAttribute("deliveryAddress")+"";
		deliveryAddress = (deliveryAddress.equals("null"))?"":deliveryAddress;
		
		String purchaseAddress = request.getAttribute("purchaseAddress")+"";
		purchaseAddress = (purchaseAddress.equals("null"))?"":purchaseAddress;
		
		String phone = request.getAttribute("phone")+"";
		phone = (phone.equals("null"))?"":phone;
		
		String email = request.getAttribute("email")+"";
		email = (email.equals("null"))?"":email;
		
		String emailNews = request.getAttribute("emailNews")+"";
		emailNews = (emailNews.equals("null"))?"":emailNews;
		
		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	%>
	<div class="container mt-3">
		<div class="text-center">
			<h1>ĐĂNG KÝ TÀI KHOẢN</h1>
		</div>
		
		
		
		<div class="red" id="baoLoi">
			<%= errors %>
		</div>
		<form class="form" action="<%= url %>/user" method="post">
			<input type="hidden" name="action" value="register">
			<div class="row">
				<div class="col-sm-6">
					<h3>Tài khoản</h3>
					<div class="mb-3">
						<label for="name" class="form-label">Tên đăng nhập<span
							class="red">*</span></label> <input type="text" class="form-control"
							id="name" name="name" required="required" value="<%= name %>">
					</div>
					<div class="mb-3">
						<label for="password" class="form-label">Mật khẩu<span
							class="red">*</span></label> <input type="password" class="form-control"
							id="password" name="password" required="required" onkeyup="kiemTraMatKhau()">
					</div>
					<div class="mb-3">
						<label for="passwordConfirm" class="form-label" >Nhập lại
							mật khẩu<span class="red">*</span> <span id="msg" class="red" ></span>
						</label> <input type="password" class="form-control" id="passwordConfirm"
							name="passwordConfirm" required="required" onkeyup="kiemTraMatKhau()">
					</div>
					<hr />
					<h3>Thông tin khách hàng</h3>
					<div class="mb-3">
						<label for="fullname" class="form-label">Họ và tên</label> <input
							type="text" class="form-control" id="fullname" name="fullname" value="<%= fullname %>">
					</div>
					<div class="mb-3">
						<label for="sex" class="form-label">Giới tính</label> <select
							class="form-control" id="sex" name="sex">
							<option></option>
							<option value="Nam"<%= (sex.equalsIgnoreCase("Nam")) ? "selected" : "" %>>Nam</option>
							<option value="Nữ" <%= (sex.equalsIgnoreCase("Nữ")) ? "selected" : "" %>>Nữ</option>
							<option value="Khác" <%= (sex.equalsIgnoreCase("Khác")) ? "selected" : "" %>>Khác</option>
						</select>
					</div>
					<div class="mb-3">
						<label for="birthday" class="form-label">Ngày sinh</label> <input
							type="date" class="form-control" id="birthday" name="birthday" value="<%= birthday %>">
					</div>
				</div>
				<div class="col-sm-6">
					<h3>Địa chỉ</h3>
					<div class="mb-3">
						<label for="address" class="form-label">Địa chỉ
							khách hàng</label> <input type="text" class="form-control"
							id="address" name="address" value="<%= address %>">
					</div>
					<div class="mb-3">
						<label for="deliveryAddress" class="form-label">Địa chỉ nhận hàng </label> <input type="text" class="form-control" id="deliveryAddress"
							name="deliveryAddress" value="<%= deliveryAddress %>">
					</div>
					<div class="mb-3">
						<label for="purchaseAddress" class="form-label">Địa chỉ mua
							hàng</label> <input type="text" class="form-control"
							id="purchaseAddress" name="purchaseAddress" value="<%= purchaseAddress %>">
					</div>
					<div class="mb-3">
						<label for="phone" class="form-label">Điện thoại</label> <input
							type="tel" class="form-control" id="phone" name="phone" value="<%= phone %>">
					</div>
					<div class="mb-3">
						<label for="email" class="form-label">Email</label> <input
							type="email" class="form-control" id="email" name="email"  value="<%= email %>">
					</div>
					<hr />
					
					
					<div class="custom-control custom-checkbox mb-3">
					  <input type="checkbox" class="custom-control-input" id="agreeRegulation"  name="agreeRegulation" required="required" onchange="xuLyChonDongY()">
					  <label class="custom-control-label" for="agreeRegulation">Đồng ý với <a> điều khoản của công ty </a><span class="red">*</span></label>
					</div>
					
					<div class="custom-control custom-checkbox mb-3">
					  <input type="checkbox" class="custom-control-input" id="emailNews" name="emailNews" required="required">
					  <label class="custom-control-label" for="emailNews">Đồng ý nhận email</label>
					</div>
					
						<a href="../index.jsp" class="btn btn-primary">Quay lại</a>
						<button type="submit" class="btn btn-success" style="visibility: hidden;" name="submit" id="submit">Đăng ký</button>
				</div>
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

<script>
	function kiemTraMatKhau(){
		matKhau = document.getElementById("password").value;
		matKhauNhapLai = document.getElementById("passwordConfirm").value;
		if(matKhau!=matKhauNhapLai){
			document.getElementById("msg").innerHTML = "Mật khẩu không khớp!";
			return false;
		}else{
			document.getElementById("msg").innerHTML = "";
			return true;
		}
	}
	
	function xuLyChonDongY(){
		dongYDeuKhoan = document.getElementById("agreeRegulation");
		if(dongYDeuKhoan.checked==true){
			document.getElementById("submit").style.visibility="visible";
		} else {
			document.getElementById("submit").style.visibility="hidden";
		}
	}
</script>

</html>