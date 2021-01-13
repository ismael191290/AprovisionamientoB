document.getElementById("file-es").addEventListener("click", ()=>{
	$(".input-group-append").find('a').remove();
	setTimeout(function(){ $(".file-footer-buttons").remove() }, 9000);
});

document.getElementById("files").addEventListener("click", ()=>{
	$(".input-group-append").find('a').remove();
	setTimeout(function(){ $(".file-footer-buttons").remove() }, 9000);
});

var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

$(document).ready(function() {
	$('#file-es, #files').fileinput({
	    theme: 'fas',
	    language: 'es',
	    uploadUrl: '#',
	    allowedFileExtensions: ['txt']
	});
	
	
	$( ".fileinput-remove" ).click(function( event ) {
	  $(".list-group-flush").html('');
	});
		
	
});


	
let uploadFile = ()=>{
	
	if($('#file-es')[0].files.length == 0){
		return;
	}
		let arraName=[];
		var data = new FormData(); 
		$.each($('#file-es')[0].files, function (i, file) {
	 		data.append('fotos', file);
	 	});

		$.ajax({
		url : '/file/verify',
		data : data,
		cache: false,
		contentType: false,
		processData: false,
		type : "POST",
		dataType : false,
		 beforeSend: function(xhr) {
             xhr.setRequestHeader(header, token);
         },
		success : function(response, textStatus, jqXHR) {	
			let listDelay = $("#conteInfo");
			listDelay.html('');
			let archivoName;
			let conte     ="";
			let conteInfo ="";
			let icon = "";
			listDelay.html('');
			$.each(response, function (i, data) {
				icon="";
				conteInfo="";
				if(data.listError.length == 0){
					icon      += "<li class='list-group-item'><i class='fa fa-check-circle styleIconSuccess'></i></li>";
					conteInfo += "<li class='list-group-item'>"+data.causa+" >>></li>"
				}else{
					
					$.each(data.listError, function (x, dat) {
						icon      += "<li class='list-group-item'><i class='fas fa-times-circle styleIconError'></i></li>";
						conteInfo += "<li class='list-group-item'>"+dat+" >>></li>"
						
					});
				}
				
				conte = templateInfo(data.nombre,conteInfo, icon);
				listDelay.append(conte);
		 	});
			 },complete:function(response){

				 
				 
			 },	error : function(jqXHR, textStatus, errorThrown) {
				 console.log("error",jqXHR);
			}
		});
	}

let templateInfo = (nombre,conteInfo, icon )=>{
	let conte = 
		"<div class='col-4'>"
			+"<ul class='list-group list-group-flush fontSize13'>"
				+"<li class='list-group-item'>"+nombre+"</li>"
			+"</ul>"
		+"</div>"
		+"<div class='col-8 row'>"
			+"<div class='col-10'>"
				+"<ul id='listDelay' class='list-group list-group-flush fontSize13'>"
				+conteInfo
	            +"</ul>" 
			+"</div>"
			+"<div class='col-1'>"
				+"<ul class='list-group list-group-flush fontSize13'>"
					+icon
				+"</ul>"
			+"</div>"
		+"<div>";
	
	return conte;
	
}


let uploadFileExcel = ()=>{
	
	if($('#files')[0].files.length == 0){
		return;
	}
		let arraName=[];
		let data = new FormData(); 
		$.each($('#files')[0].files, function (i, file) {
	 		data.append('fotos', file);
	 	});

		$.ajax({
		url : '/file/verifyExcel',
		data : data,
		cache: false,
		contentType: false,
		processData: false,
		type : "POST",
		dataType : false,
		 beforeSend: function(xhr) {
             xhr.setRequestHeader(header, token);
         },
		success : function(response, textStatus, jqXHR) {	
			 },complete:function(response){

				    let a = document.createElement('a')
					a.href = "/file/generateDocto";
					a.download = "text";
					a.click()
				 
			 },	error : function(jqXHR, textStatus, errorThrown) {
				 console.log("error",jqXHR);
			}
		});
	}



