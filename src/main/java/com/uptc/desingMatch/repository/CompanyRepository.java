package com.uptc.desingMatch.repository;


import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uptc.desingMatch.models.Company;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{
	
	@Query(value = "SELECT id_compania FROM companias  WHERE email = ?1 AND contrasena = ?2", nativeQuery = true)
	 Integer verifyCompany(String email, String password); 
	
	
	@Query(value = "select count(*)  from companias  where UPPER(nombre_compania) = UPPER(?1)", nativeQuery = true)
	 Integer countUrl(String url); 
	
	@Query(value = "select *  from companias  where UPPER(url_compania) = UPPER(?1)", nativeQuery = true)
	List<Map<String,Object>> getCompany(String url);
	
}
