<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/nav/navbar.jsp" />
	
<%-- 	<sec:authorize access="!isAuthenticated()"> --%>
<!--   <div class="d-flex flex-row-reverse bd-highlight"> -->
<!-- 		<div class="p-2 bd-highlight"> -->
<%-- 			<spam>Fecha: <c:out value="${time}" /></spam> --%>
<!-- 		</div> -->
<!-- 	</div> -->
<%-- </sec:authorize> --%>
	<div class="d-flex flex-row-reverse bd-highlight">
		<div class="p-2 bd-highlight">
<%-- 			<spam sec:authorize="isAuthenticated()">Fecha: <c:out value="${time}" /></spam> --%>
			<samp>Fecha: <c:out value="${time}" /> </samp>
		</div>
	</div>
	
	<section id="tabs" class="project-tab">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <nav>
                            <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                                <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">Inicio</a>
                                <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-apro" role="tab" aria-controls="nav-profile" aria-selected="false">Aprovisionamiento</a>

                            </div>
                        </nav>
                        <div class="tab-content" id="nav-tabContent">
                            <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">

                            </div>
                            <div class="tab-pane fade" id="nav-apro" role="tabpanel" aria-labelledby="nav-profile-tab">
								<jsp:include page="tabAprovisionamiento.jsp"></jsp:include>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>


</body>
<jsp:include page="/WEB-INF/views/nav/fooder.jsp" />
</html>