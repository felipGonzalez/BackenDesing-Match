package com.uptc.desingMatch.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uptc.desingMatch.models.Company;
import com.uptc.desingMatch.repository.CompanyRepository;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository repository;

	public List<Company> getList() {
		return repository.findAll();
	}
		
	 public Company save(Company company) {
		 return repository.save(company);
	 }
	 
	 public void remove(Integer id) {
		 repository.deleteById(id);
	 }
	 
	 public Optional<Company> getCompany(Integer id) {
		 return repository.findById(id);
	 }
	 
	 public List<Map<String,Object>> getCompany(String url) {
		 return repository.getCompany(url);
	 }
	 
	 public int verifyCompany(String email, String password) throws Exception{
		 return repository.verifyCompany(email, password);
	 }
	 
	 public int countUrl(String url) {
		 return repository.countUrl(url);
	 }
	

}
