<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="nav/navbar.jsp"></jsp:include>
	<div class="container py-4">
		<div class="card bg-danger text-white">
			<div class="card-header">Error: Acceso Denegado!!</div>
		<div class="card-body"></div>
				<span>Lo sentimos <sec:authentication property="principal.username" /> No tiene permisos</span>
				<a href="/inicio" class="btn btn-outline-light">Regresar a Inicio</a>
		</div>
	</div>
</body>
<jsp:include page="nav/fooder.jsp"></jsp:include>
</html>