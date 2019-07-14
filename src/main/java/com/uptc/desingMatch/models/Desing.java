package com.uptc.desingMatch.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.AccessType.Type;

@Entity
@Table(name="disenos")
@AccessType(Type.FIELD)
public class Desing {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_diseno", unique=true, nullable=false )
	private Integer idDesing;
	
	@Column(name="precio", nullable=false)
	private Integer value;
	
	@Column(name="url_img_original", nullable=false,length=255 )
	private String urlImgOriginal;
	
	@Column(name="url_img_procesado", nullable=true,length=255 )
	private String urlImgConvert;
	
	@Column(name="id_proyecto", nullable=false)
	private Integer idDraft;
	
	@Column(name="estado", nullable=false)
	private String state;
	
	@Column(name="nombre_disenador", nullable=false,length=60 )
	private String nameDesigner;
	
	@Column(name="nombre_compania", nullable=false,length=255 )
	private String nameCompany;
	
	@Column(name="apellido_disenador", nullable=false,length=60 )
	private String lastNameDesigner;

	@Column(name="email", nullable=false,length=100 )
	private String email;
	
	@Column(name="fecha", nullable=false,length=60 )
	private Date date;

	
	public Desing() {
	}
	
	public Integer getIdDesing() {
		return idDesing;
	}

	public void setIdDesing(Integer idDesing) {
		this.idDesing = idDesing;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getUrlImgOriginal() {
		return urlImgOriginal;
	}

	public void setUrlImgOriginal(String urlImgOriginal) {
		this.urlImgOriginal = urlImgOriginal;
	}

	public String getUrlImgConvert() {
		return urlImgConvert;
	}

	public void setUrlImgConvert(String urlImgConvert) {
		this.urlImgConvert = urlImgConvert;
	}

	public Integer getIdDraft() {
		return idDraft;
	}

	public void setIdDraft(Integer idDraft) {
		this.idDraft = idDraft;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getNameDesigner() {
		return nameDesigner;
	}

	public void setNameDesigner(String nameDesigner) {
		this.nameDesigner = nameDesigner;
	}

	public String getLastNameDesigner() {
		return lastNameDesigner;
	}

	public void setLastNameDesigner(String lastNameDesigner) {
		this.lastNameDesigner = lastNameDesigner;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNameCompany() {
		return nameCompany;
	}

	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}
	
	
	
	
	

}
