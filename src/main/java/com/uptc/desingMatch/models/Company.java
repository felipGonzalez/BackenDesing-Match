package com.uptc.desingMatch.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.AccessType.Type;

@Entity
@Table(name="companias")
@AccessType(Type.FIELD)
public class Company {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_compania", unique=true, nullable=false )
	private Integer idCompany;
	
	@Column(name="url_compania", nullable=false,length=60 )
	private String urlCompany;
	
	@Column(name="nombre_compania", nullable=false,length=60 )
	private String nameCompany;
	
	@Column(name="contrasena", nullable=false,length=256 )
	private String password;
	
	@Column(name="email", nullable=true,length=100 )
	private String email;
	
	public Company() {
	}
	
	
	

	public Company(Integer idCompany, String urlCompany, String nameCompany) {
		this.idCompany = idCompany;
		this.urlCompany = urlCompany;
		this.nameCompany = nameCompany;
	}




	public Integer getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(Integer idCompany) {
		this.idCompany = idCompany;
	}


	public String getNameCompany() {
		return nameCompany;
	}

	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrlCompany() {
		return urlCompany;
	}

	public void setUrlCompany(String urlCompany) {
		this.urlCompany = urlCompany;
	}
}


