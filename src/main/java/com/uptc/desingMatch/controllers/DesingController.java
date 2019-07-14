package com.uptc.desingMatch.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uptc.desingMatch.models.Desing;
import com.uptc.desingMatch.service.DesingService;
import com.uptc.desingMatch.util.Const;
import com.uptc.desingMatch.util.RestResponse;
import com.uptc.desingMatch.util.Util;

@RestController
@RequestMapping("/desing")
@CrossOrigin(origins = {Const.DOMAIN})
public class DesingController {

	
	@Autowired 
	private ServletContext context;
	
	@Autowired
	private DesingService service;
	

	@GetMapping(value="getImageDesing")
	public void getImageDesing(HttpServletResponse response, @RequestParam List<String> data) throws IOException {
		System.out.println(data.get(0)+"/"+data.get(1)+"/"+data.get(2));
		 InputStream in = context.getResourceAsStream(Const.PATH_IMG_DISENOS+"/"+data.get(0)+"/"+data.get(1)+"/"+data.get(2));
	    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    IOUtils.copy(in, response.getOutputStream());
	}
	
	@GetMapping(value="getImageFinal")
	public void getImageConvert(HttpServletResponse response, @RequestParam List<String> data) throws IOException {
		System.out.println(data.get(0)+"/"+data.get(1)+"/"+data.get(2));
	    InputStream in = context.getResourceAsStream("/finalDisenos/"+data.get(0)+"/"+data.get(1)+"/"+data.get(2));
	    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    IOUtils.copy(in, response.getOutputStream());
	}
	
	

	@GetMapping(value="/{id}")
	public List<Desing> getList(@PathVariable int id){
		return service.getList(id);
	}
	
	@GetMapping(value="DesdingAvailable/{id}")
	public List<Desing> getListDesingAvailable(@PathVariable int id){
		return service.getListDesingAvailable(id);
	}
	
	

	public boolean save(Desing desing) {
		if(!this.validate(desing)) {
			return false;
		}
		try {
			service.save(desing);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		
	}

	private boolean validate(Desing desing) {
		return (!validateString(desing.getLastNameDesigner()));
	}

	private boolean validateString(String string) {
		return string.isEmpty();
	}
	
	
	//Borrar proyecto
	@DeleteMapping(value = "{id}")
	public RestResponse remove(@PathVariable int id){
		try {
			service.remove(id);
			return new RestResponse(HttpStatus.OK.value(), "Proyecto borrada");
		} catch (Exception e) {

			return new RestResponse(HttpStatus.EXPECTATION_FAILED.value(), "Proyecto no encontrado");
		}
	}
	
	
	
	@PostMapping(value="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public RestResponse uploadFile(@RequestParam("file") MultipartFile file,  @RequestParam("desing") String desing,@RequestParam("company") String company) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Desing d = mapper.readValue(desing, Desing.class);
		if(!save(d)) {
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(),"Los campos obligatorios no estan diligenciados ");
		}
		verifyFile(company, d.getIdDraft());
		File convertFile = new File(context.getRealPath(Const.PATH_IMG_DISENOS)+ "/" +company+"/"+d.getIdDraft()+"/"+ file.getOriginalFilename());
		convertFile.createNewFile();
		FileOutputStream fout = new FileOutputStream(convertFile);
		fout.write(file.getBytes());
		fout.close();
		
		return new RestResponse(HttpStatus.OK.value(), "Operacion exitosa");
	}
	
	public void verifyFile(String company, int idDraft) {
		Util.createImg(context.getRealPath(Const.PATH_IMG_DISENOS)+"/"+company);
		Util.createImg(context.getRealPath(Const.PATH_IMG_DISENOS)+"/"+company+"/"+idDraft);
		Util.createImg(context.getRealPath(Const.PATH_FINAL_DISENOS)+"/"+company);
		Util.createImg(context.getRealPath(Const.PATH_FINAL_DISENOS)+"/"+company+"/"+idDraft);
	}
}
