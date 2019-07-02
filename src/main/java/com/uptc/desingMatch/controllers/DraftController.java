package com.uptc.desingMatch.controllers;

import java.util.List;

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

import com.uptc.desingMatch.models.Draft;
import com.uptc.desingMatch.service.DraftService;
import com.uptc.desingMatch.util.Const;
import com.uptc.desingMatch.util.RestResponse;


@RestController
@RequestMapping("/draft")
@CrossOrigin(origins = {Const.DOMAIN})
public class DraftController {
	
	@Autowired
	private DraftService service;
	
	
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
		try {
			service.remove(id);
			return new RestResponse(HttpStatus.OK.value(), "Proyecto borrada");
		} catch (Exception e) {

			return new RestResponse(HttpStatus.EXPECTATION_FAILED.value(), "Proyecto no encontrado");
		}
	}
}
