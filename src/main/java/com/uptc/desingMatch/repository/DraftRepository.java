package com.uptc.desingMatch.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uptc.desingMatch.models.Draft;


@Repository
public interface DraftRepository extends JpaRepository<Draft, Integer>{
	
	@Query(value = "SELECT * FROM proyectos  where id_compania = ?1", nativeQuery = true)
	 List<Draft> selectAll(Integer id); 
	
}
