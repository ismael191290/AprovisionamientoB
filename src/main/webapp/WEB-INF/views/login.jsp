<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Login</title>
<link rel="stylesheet" href="/css/bootstrap/bootstrap4.css"> 
<link rel="stylesheet" href="/css/loginCss/login.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/nav/navbar.jsp"/>
<span>${error}</span>
<span>${success}</span>
<div class="d-flex justify-content-center withForm">
<div>
  <h2 class="text-center">Aprovisionamiento</h2>
  <form action="/login" method="post" class="was-validated">
    <div class="form-group">
      <label for="username">Usuario:</label>
      <input type="text" class="form-control" id="username" placeholder="Ingresa Usuario..." name="username" required>
      <div class="valid-feedback">Correcto.</div>
      <div class="invalid-feedback">Favor de llenar este campo.</div>
    </div>
    <div class="form-group">
      <label for="pwd">Contraseña:</label>
      <input type="password" class="form-control" id="pwd" placeholder="Ingresa Contraseña" name="password" required>
      <div class="valid-feedback">Correcto.</div>
      <div class="invalid-feedback">Favor de llenar este campo.</div>
    </div>
    <button type="submit" class="btn btn-primary">Iniciar Sesion</button>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
  </form>
</div>
</div>
</body>
<jsp:include page="nav/fooder.jsp"/>
</html>