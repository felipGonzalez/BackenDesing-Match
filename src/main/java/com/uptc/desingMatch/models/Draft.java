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
@Table(name="proyectos")
@AccessType(Type.FIELD)
public class Draft {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_proyecto", unique=true, nullable=false )
	private Integer idDraft;
	
	@Column(name="nombre_proyecto", nullable=true,length=60 )
	private String nameDraft;
	
	@Column(name="descripcion", nullable=true,length=255 )
	private String description;
	
	@Column(name="valor", nullable=true)
	private Integer price;
	
	@Column(name="id_compania", nullable=true)
	private Integer idCompany;
	
	
//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name = "id_compania", nullable = false)
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	@JsonIgnore
//	private Company company;
	
	public Draft() {
	}
	
	public Draft(Integer idDraft) {
		this.idDraft = idDraft;
	}


	public Integer getIdDraft() {
		return idDraft;
	}

	public void setIdDraft(Integer idDraft) {
		this.idDraft = idDraft;
	}

	public String getNameDraft() {
		return nameDraft;
	}

	public void setNameDraft(String nameDraft) {
		this.nameDraft = nameDraft;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(Integer idCompany) {
		this.idCompany = idCompany;
	}
	
	
	
	
	
}

