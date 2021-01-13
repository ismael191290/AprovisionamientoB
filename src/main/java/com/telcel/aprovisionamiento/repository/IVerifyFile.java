package com.telcel.aprovisionamiento.repository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.telcel.aprovisionamiento.model.Documento;

public interface IVerifyFile {
	
	List<Documento> checkDoctos(MultipartFile[] files);
	
	List<Documento> checkDoctosExcel(MultipartFile[] files);
	
	List<String> lecturafile(String path);
	
	ByteArrayInputStream  generateCSV() throws IOException;

}
