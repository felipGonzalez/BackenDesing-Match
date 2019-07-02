package com.uptc.desingMatch.controllers;

import java.awt.Image;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.ImageIcon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.uptc.desingMatch.models.Company;
import com.uptc.desingMatch.service.CompanyService;
import com.uptc.desingMatch.util.Const;
import com.uptc.desingMatch.util.RestResponse;

@RestController
@RequestMapping("/company")
@CrossOrigin(origins = {Const.DOMAIN})
public class CompanyController {
	
	
	@Autowired
	private CompanyService service;
	
	@GetMapping(value="")
	public List<Company> getList(){
		return service.getList();
	}
	
	
	
	//Obtener compañia
	@GetMapping(value="getCompany/{url}")
	public Company getUsers(@PathVariable String url){
		try {
			List<Map<String,Object>> com = service.getCompany(url);
			
			Company company = new Company(Integer.parseInt(String.valueOf(com.get(0).get("id_compania"))), 
					(String) com.get(0).get("url_compania"), (String) com.get(0).get("nombre_compania"));
			return company;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
		
	}
	
//	@GetMapping(value="verifyCompany")
//	@ResponseBody
//	public Optional<Company> getDataUser(@RequestParam List<String> data){
//		System.out.println(data);
//		try {
//			if(data.get(0) != null && data.get(1) != null) {
//				int id = service.verifyCompany(data.get(0),data.get(1));
//				return service.getCompany(id);
//			}
//			
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			return null;
//		}
//		
//		return null;
//	}
	
	@PostMapping(value="verifyCompany")
	@ResponseBody
	public Optional<Company> getDataUser(@RequestBody Company company){
		System.out.println(company.getNameCompany());
		System.out.println(company.getPassword());
		System.out.println(company.getEmail());
		try {
			if(company.getPassword() != null && company.getEmail()  != null) {
				int id = service.verifyCompany(company.getEmail(),company.getPassword());
				return service.getCompany(id);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
		return null;
	}
	
	
	//agregar Compañia
	@PostMapping(value="")
	public RestResponse save(@RequestBody Company company) {
		Company company2;
		if(!this.validateCompany(company)) {
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(),"Los campos obligatorios no estan diligenciados ");
		}
		System.out.println("pasa validacion");
		try {
			int count = service.countUrl(company.getNameCompany());
			if(count == 0) {
				company.setUrlCompany(company.getNameCompany());
			}else {
				company.setUrlCompany(company.getNameCompany()+ (count++));
			}
			company2 = service.save(company);
		} catch (Exception e) {
			System.out.println(e.getCause());
			if(e.getMessage().contains("4732")) {
				return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(), "El correo ya esta registrado");
			}
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(), "Ocurrio un error, intentelo mas tarde");
		}
		

		return new RestResponse(HttpStatus.OK.value(), company2.getUrlCompany() );
	}
	
	private void trimString(Company company) {
		if(company.getPassword() != null || !company.getPassword().isEmpty()) {
			company.setPassword(company.getPassword().trim());
			company.setPassword(company.getPassword().replace(" ", ""));
		}
		
		if(company.getEmail() != null || !company.getEmail().isEmpty()) {
			company.setEmail(company.getEmail().trim());
			company.setEmail(company.getEmail().replace(" ", ""));
		}
	}

	private boolean validateCompany(Company company) {
		System.out.println("Entro a validar");
		trimString(company);
		return (!validateName(company.getNameCompany()));
	}
	
	private boolean validateName(String name) {
		return name.isEmpty();
	}

	
	@DeleteMapping(value = "{id}")
	public RestResponse remove(@PathVariable int id){
		try {
			service.remove(id);
			return new RestResponse(HttpStatus.OK.value(), "Compañia borrada");
		} catch (Exception e) {

			return new RestResponse(HttpStatus.EXPECTATION_FAILED.value(), "Compañia no encontrado");
		}
	}
	



}
