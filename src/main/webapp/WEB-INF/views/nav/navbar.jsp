<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<nav class="navbar navbar-expand-lg navbar-dark backgroundNav">
		<a class="navbar-brand" href="#"><img alt="telcel"
			src="/img/telcel-f.png" height="40"></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarTogglerDemo02"
			aria-controls="navbarTogglerDemo02" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarTogglerDemo02">
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<!-- 				<li class="nav-item active"><a class="nav-link" href="#">Home -->
				<!-- 						<span class="sr-only">(current)</span> -->
				<!-- 				</a></li> -->
				<!-- 				<li class="nav-item"><a class="nav-link" href="#">Link</a></li> -->
				<!-- 				<li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a> -->
				<!-- 				</li> -->
			</ul>
			<c:if test="${user!=null}">
				<div class="d-flex">

					<div class="mr-auto p-2 bd-highlight iconUser">
						<i class="fas fa-user "></i><c:out value="${user}"/>
					</div>

					<div class="mr-auto p-2 bd-highlight">
						<form action="/logout" id="logout" method="post" class="form-inline">

							<i class="fas fa-sign-out-alt iconUser" onclick="$('#logout').submit();">
							<button type="submit" style="border: 0px"></button></i>
							<input type="hidden"name="${_csrf.parameterName}" value="${_csrf.token}">
						</form>
					</div>
			</c:if>


		</div>
	</nav>
</body>
</html>