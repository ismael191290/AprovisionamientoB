<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<section>
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <nav>
                            <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                                <a class="nav-item nav-link active" id="nav-file-tab" data-toggle="tab" href="#nav-file" role="tab" aria-controls="nav-home" aria-selected="true">Valida/Fecha</a>
                                <a class="nav-item nav-link" id="nav-query-tab" data-toggle="tab" href="#tabquery" role="tab" aria-controls="nav-profile" aria-selected="false">Valiada/Consulta</a>
								 <a class="nav-item nav-link" id="nav-excel-tab" data-toggle="tab" href="#tabexcel" role="tab" aria-controls="nav-profile" aria-selected="false">Exporta/Excel( Conceptos )</a>
								
                            </div>
                        </nav>
                        <div class="tab-content" id="nav-tabContent">
                            <div class="tab-pane fade show active" id="nav-file" role="tabpanel" aria-labelledby="nav-home-tab">
								<jsp:include page="verifyFile.jsp"></jsp:include>
                            </div>
                            <div class="tab-pane fade" id="tabquery" role="tabquery" aria-labelledby="nav-profile-tab">
En Desarrollo
                            </div>
                            <div class="tab-pane fade"  id="tabexcel" role="tabexcel" aria-labelledby="nav-profile-tab">
                            		<jsp:include page="verifyFileConvertExcel.jsp"></jsp:include>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
</body>
