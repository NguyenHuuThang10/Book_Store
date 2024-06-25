<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">PITIS</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="#">Trang
						chủ <span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Combo
						giảm giá</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" role="button"
					data-toggle="dropdown" aria-expanded="false"> Sản phẩm </a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="#">Hành động</a> <a
							class="dropdown-item" href="#">Tình cảm</a>
						<a class="dropdown-item" href="#">Phiêu lưu</a>
					</div></li>
				<li class="nav-item"><a class="nav-link disabled">Hết hàng</a>
				</li>
			</ul>
			<%
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
			Object obj = session.getAttribute("user");
			User user = null;

			if (obj != null) {
				user = (User) obj;
			}

			if (user == null) {
			%>
			<a href="<%= url %>/users/login.jsp" class="btn btn-success ml-3">Đăng nhập</a>
			<%
			} else {
			%>
			<div class="dropdown">
				<a class="dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-expanded="false"><img src="<%= url %>/img/logo.jpg" alt="" class="user-avatar"> Hi, <%=user.getName()%> </a>

				<div class="dropdown-menu dropdown-menu-right">
					<a class="dropdown-item" href="<%= url %>/users/changeInfor.jsp">Thông tin cá nhân</a> 
					<a class="dropdown-item" href="<%= url %>/users/reset.jsp">Đổi mật khẩu</a> 
					<hr>
					<a class="dropdown-item" href="<%= url %>/user?action=logout">Đăng xuất</a>
				</div>
			</div>

			<%
			}
			%>

		</div>
	</nav>