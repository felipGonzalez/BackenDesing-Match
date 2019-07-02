package com.uptc.desingMatch.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uptc.desingMatch.models.Draft;
import com.uptc.desingMatch.repository.DraftRepository;

@Service
public class DraftService {

	
	@Autowired
	private DraftRepository repository;

	public List<Draft> getList() {
		return repository.findAll();
	}
	
	public List<Draft> getList(int id) {
		return repository.selectAll(id);
	}
		
	 public void save(Draft draft) {
		 repository.save(draft);
	 }
	 
	 public void remove(Integer id) {
		 repository.deleteById(id);
	 }
	 
	 public Optional<Draft> getCompany(Integer id) {
		 return repository.findById(id);
	 }
	
}
