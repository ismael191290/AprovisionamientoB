package com.telcel.aprovisionamiento.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.telcel.aprovisionamiento.exceptions.RequestExceptions;
import com.telcel.aprovisionamiento.model.Documento;
import com.telcel.aprovisionamiento.repository.IVerifyFile;
import com.telcel.aprovisionamiento.serviceImp.VerifyFileImp;

@Controller
@RequestMapping("/file")
public class VerifyFile {
	
	@Autowired
	private VerifyFileImp VerifyFileImp;
	
	@Autowired
	private IVerifyFile iVerifyFile;
	
	@PostMapping("/verify")
	public ResponseEntity<List<Documento>> Doctos(@RequestParam("fotos") MultipartFile[] file) {
		try {	
			if( file.length !=0 ) {	
				return new ResponseEntity<List<Documento>>(VerifyFileImp.checkDoctos(file), HttpStatus.OK);
			}else {
				return new ResponseEntity<List<Documento>>(Collections.emptyList(), HttpStatus.PARTIAL_CONTENT);
			}
			
		} catch (Exception e) {
			throw new RequestExceptions("Error: Lectura de Archivos TXT (FECHAS) : "+e.getMessage(),e);
		}
	}

	@PostMapping("/verifyExcel")
	public ResponseEntity<List<Documento>> DoctosVerifyExcel(@RequestParam("fotos") MultipartFile[] file) {
		try {
			if( file.length !=0 ) {	
				return new ResponseEntity<List<Documento>>(VerifyFileImp.checkDoctosExcel(file), HttpStatus.OK);
			}else {
				return new ResponseEntity<List<Documento>>(Collections.emptyList(), HttpStatus.PARTIAL_CONTENT);
			}
		} catch (Exception e) {
			throw new RequestExceptions("Error: Lectura de Archivos TXT (CONCEPTOS): "+e.getMessage(),e);
		}
	}
	
	@GetMapping("/generateDocto")
	public ResponseEntity<InputStreamResource> excelCustomersReport() throws IOException {
		try {
			ByteArrayInputStream in = iVerifyFile.generateCSV();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment; filename=ejemplo.xlsx");
			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
		} catch (Exception e) {
			throw new RequestExceptions("Error: Generar Archivo EXCEL: "+e.getMessage(),e);
		}
	}
}
