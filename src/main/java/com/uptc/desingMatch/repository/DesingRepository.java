package com.uptc.desingMatch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uptc.desingMatch.models.Desing;


public interface DesingRepository extends JpaRepository<Desing, Integer>{

	
	@Query(value = "SELECT * FROM disenos where id_proyecto = ?1 ORDER BY fecha DESC", nativeQuery = true)
	 List<Desing> selectAll(Integer id); 
	
	@Query(value = "SELECT * FROM disenos where id_proyecto = ?1 AND estado = 'D' ORDER BY fecha DESC", nativeQuery = true)
	 List<Desing> selectDesingAvailable(Integer id); 
	
	@Query(value = "SELECT * FROM disenos where  estado = 'P' ORDER BY fecha DESC", nativeQuery = true)
	 List<Desing> selectDesingNotAvailable(); 
	
}
