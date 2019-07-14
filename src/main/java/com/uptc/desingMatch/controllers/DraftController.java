package com.uptc.desingMatch.controllers;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uptc.desingMatch.models.Company;
import com.uptc.desingMatch.models.Draft;
import com.uptc.desingMatch.service.CompanyService;
import com.uptc.desingMatch.service.DraftService;
import com.uptc.desingMatch.util.Const;
import com.uptc.desingMatch.util.RestResponse;
import com.uptc.desingMatch.util.Util;


@RestController
@RequestMapping("/draft")
@CrossOrigin(origins = {Const.DOMAIN})
public class DraftController {
	
	@Autowired
	private DraftService service;
	
	@Autowired
	private CompanyService service2;
	
	@Autowired 
	private ServletContext context;
	
	
	
	
	
	@GetMapping(value="/{id}")
	public List<Draft> getList(@PathVariable int id){
		return service.getList(id);
	}
	
	
	//guardar Proyecto
	@PostMapping(value="")
	public RestResponse save(@RequestBody Draft draft) {
		if(!this.validate(draft)) {
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(),"Los campos obligatorios no estan diligenciados ");
		}
		service.save(draft);
		Optional<Company> company = service2.getCompany(draft.getIdCompany()); 
		Util.createImg(context.getRealPath("/finalDisenos")+"/"+company.get().getUrlCompany()+"/"+draft.getIdDraft());
		Util.createImg(context.getRealPath("/imgDisenos")+"/"+company.get().getUrlCompany()+"/"+draft.getIdDraft());
		return new RestResponse(HttpStatus.OK.value(), "Operacion exitosa");
	}

	private boolean validate(Draft draft) {
		return (!validateString(draft.getNameDraft()));
	}

	private boolean validateString(String string) {
		return string.isEmpty();
	}
	
	
	//Borrar proyecto
	@DeleteMapping(value = "{id}")
	public RestResponse remove(@PathVariable int id){
		Optional<Draft> draft = service.getDraft(id);
		try {
			service.remove(id);
			return new RestResponse(HttpStatus.OK.value(), "Proyecto borrado");
		} catch (Exception e) {
			try {
				service.removeCascade(id);
				return new RestResponse(HttpStatus.OK.value(), "Proyecto y diseños borrados");
			} catch (Exception e2) {
				return new RestResponse(HttpStatus.EXPECTATION_FAILED.value(), "Proyecto no encontrado");
			}
		}
	}
	
	public void deleteData() {
		String filesPath = context.getRealPath("/img");
		File fileFolder = new File(filesPath);
	}
}
