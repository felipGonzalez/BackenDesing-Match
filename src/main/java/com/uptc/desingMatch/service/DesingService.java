package com.uptc.desingMatch.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uptc.desingMatch.models.Desing;
import com.uptc.desingMatch.models.Draft;
import com.uptc.desingMatch.repository.DesingRepository;

@Service
public class DesingService {
	
	@Autowired
	private DesingRepository repository;
	
	public List<Desing> getList() {
		return repository.findAll();
	}
	
		
	public List<Desing> getList(int id) {
		return repository.selectAll(id);
	}
	
	public List<Desing> getListDesingAvailable(int id) {
		return repository.selectDesingAvailable(id);
	}
	
	public List<Desing> getListDesingNotAvailable() {
		return repository.selectDesingNotAvailable();
	}
	
	
	 public void save(Desing desing) {
		 repository.save(desing);
	 }
	 
	 public void remove(Integer id) {
		 repository.deleteById(id);
	 }
	 
	 public Optional<Desing> getCompany(Integer id) {
		 return repository.findById(id);
	 }

}
