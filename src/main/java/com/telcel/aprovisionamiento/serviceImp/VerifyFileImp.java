package com.telcel.aprovisionamiento.serviceImp;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.telcel.aprovisionamiento.exceptions.RequestExceptions;
import com.telcel.aprovisionamiento.model.Documento;
import com.telcel.aprovisionamiento.repository.IVerifyFile;

@Service
public class VerifyFileImp implements IVerifyFile {

	private Logger logger = LoggerFactory.getLogger(VerifyFileImp.class);
	
	private Documento documento;
	private List<Documento> lista;
	private List<Documento> listaExcel;
	private final int celdaChecar = 26;
	final Path direcciones = Paths.get("src//main//resources//uploadfile");
	final String rootpath = direcciones.toFile().getAbsolutePath();

	@Override
	public List<Documento> checkDoctos(MultipartFile[] files) {
		lista = new LinkedList<>();
		deleteFile(rootpath);
		for (MultipartFile archivo : files) {
			try {
				byte[] bytes = archivo.getBytes();
				Path rutaFull = Paths.get(rootpath + "//" + archivo.getOriginalFilename());
				Files.write(rutaFull, bytes);
				documento = new Documento();
				documento.setNombre(archivo.getOriginalFilename());
				documento.setListError(lecturafile(rutaFull.toString()));
				String causa = "Documento Correcto";
				if (!documento.getListError().isEmpty()) {
					causa = "";
				}
				documento.setCausa(causa);
				lista.add(documento);
			} catch (Exception e) {
				throw new RequestExceptions("\"Error:checkDoctos Fechas: {} \""+e.getMessage(), e);
			}

		}
		return lista;
	}

	// listo checa fechas
	@Override
	public List<String> lecturafile(String path) {

		String fActual = getFormartFechaActual();
		File archivo = null;
		List<String> resultadod = new LinkedList<>();
		outerloop: try {
			archivo = new File(path);
			try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
				String linea;
				while ((linea = br.readLine()) != null) {
					String[] datos = linea.split(Pattern.quote("|"));
					if (datos.length > celdaChecar) {
						if (!datos[26].equals(fActual)) {
							resultadod.add(datos[26]);
							break outerloop;
						}
					}
				}
			} catch (Exception e) {
				throw new RequestExceptions("\"Error:lecturafile Fechas: {} \""+e.getMessage(), e);
			}

		} catch (Exception e) {
			throw new RequestExceptions("\"Error:lecturafile Fechas ciclo Archivos: {} \""+e.getMessage(), e);
		}
		return resultadod;
	}

	@SuppressWarnings("deprecation")
	@Override
	public ByteArrayInputStream generateCSV() throws IOException {

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet("ejemplo");
			Cell cell;
			Row row;

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			headerCellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
			headerCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

			int rowIdx = 0;
			for (int i = 0; i < listaExcel.size(); i++) {
				if (i != 0) {
					row = sheet.createRow(rowIdx);
					for (int j = 0; j < 10; j++) {
						cell = row.createCell(j);
						cell.setCellStyle(headerCellStyle);
					}
					rowIdx++;
				}
				Path rutaFull = Paths.get(rootpath + "//" + listaExcel.get(i).getNombre());
				try (BufferedReader br = new BufferedReader(new FileReader(rutaFull.toFile()))) {
					String linea;
					while ((linea = br.readLine()) != null) {
						row = sheet.createRow(rowIdx);
						String[] tokens = linea.split(Pattern.quote("|"));
						for (int iToken = 0; iToken < tokens.length; iToken++) {
							cell = row.createCell(iToken);
							cell.setCellValue(tokens[iToken]);

						}
						rowIdx++;
					}
				} catch (Exception e) {
					throw new RequestExceptions("\"Error: generateCSV Archivos: {} \""+e.getMessage(), e);
				}
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public String getFormartFechaActual() {
		Calendar c = Calendar.getInstance();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = (Integer.toString(c.get(Calendar.MONTH) + 1));
		String annio = Integer.toString(c.get(Calendar.YEAR));

		if (dia.length() == 1)
			dia = "0" + dia;
		if (mes.length() == 1)
			mes = "0" + mes;

		annio = annio.substring(0, 2);

		String miFecha = dia + "/" + mes + "/" + annio;
		return miFecha;
	}

	@Override
	public List<Documento> checkDoctosExcel(MultipartFile[] files) {
		listaExcel = new LinkedList<>();
		for (MultipartFile archivo : files) {
			try {
				byte[] bytes = archivo.getBytes();
				Path rutaFull = Paths.get(rootpath + "//" + archivo.getOriginalFilename());
				Files.write(rutaFull, bytes);
				documento = new Documento();
				documento.setNombre(archivo.getOriginalFilename());
				listaExcel.add(documento);
			} catch (Exception e) {
				throw new RequestExceptions("\"Error: checkDoctosExcel Archivos: {} \""+e.getMessage(), e);
			}
		}
		return listaExcel;
	}

	public void deleteFile(String Path) {
		File file = new File(Path);
		File[] files = file.listFiles();
		for (File f : files) {
			if (f.isFile() && f.exists() && f.delete()) 
				logger.info("Archivos Eliminados Correctamente");
		}
	}
}