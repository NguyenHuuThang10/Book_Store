<%@page import="java.util.ArrayList"%>
<%@page import="model.Product"%>
<%@page import="database.DAOProduct"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Store</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">
	
<style>
.user-avatar {
	width: 30px;
	border-radius: 50%;
	height: 30px;
}
</style>
</head>
<body>
	<jsp:include page="/partials/header.jsp" />
	
	<%
		DAOProduct dao = new DAOProduct();
		ArrayList<Product> arr = dao.selectAll();
	
	%>


	<div class="container mt-3">
		<div class="row">
			<div class="col-lg-3">
				<div class="list-group">
						<a href="#" class="list-group-item list-group-item-action active" aria-current="true"> Hành động </a> 
						<a href="#" class="list-group-item list-group-item-action">Tình cảm</a> 
						<a href="#" class="list-group-item list-group-item-action">Phiêu lưu</a> 
				</div>
			</div>

			<div class="col-lg-9">
				<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
					  <div class="carousel-inner">
						    <div class="carousel-item active">
						      	<img src="./img/banner.jpg" class="d-block w-100" alt="...">
						    </div>
						    <div class="carousel-item">
						      	<img src="./img/banner2.jpg" class="d-block w-100" alt="...">
						    </div>
						    <div class="carousel-item">
						      	<img src="./img/banner3.jpg" class="d-block w-100" alt="...">
						    </div>
					  </div>
					 <button class="carousel-control-prev" type="button" data-target="#carouselExampleControls" data-slide="prev">
						    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
						    <span class="sr-only">Previous</span>
					  </button>
					  <button class="carousel-control-next" type="button" data-target="#carouselExampleControls" data-slide="next">
						    <span class="carousel-control-next-icon" aria-hidden="true"></span>
						    <span class="sr-only">Next</span>
					  </button>
				</div>
				
				
				<div class="row mt-3">
				
				<%
					for(Product item : arr) {
				%>
					<div class="col-4 mb-3">
						<div class="card" >
						  <img src="./img/<%= item.getImg() %>" class="card-img-top" alt="...">
						  <div class="card-body text-center">
						    <h5 class="card-title"><%= item.getName() %></h5>
						   	<p><%= item.getSellPrice() %>00 VND</p>		
						   	<button class="btn btn-danger">Mua ngay</button>				  
						  </div>
						</div>
					</div>
					
				<%
					}
				%>
					
				</div>
			
			</div>
				
		</div>


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