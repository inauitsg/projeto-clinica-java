package com.aulas.rest.dto;

import java.io.Serializable;

import com.aulas.rest.entidades.Especialidade;

public class EspecialidadeDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String especialidade;

	public EspecialidadeDTO() {
		
	}
	
	public EspecialidadeDTO(String especialidade) {
		this.especialidade = especialidade;
	}

	public EspecialidadeDTO(Especialidade especialidade) {
		this.id = especialidade.getId();
		this.especialidade = especialidade.getEspecialidade();
	}
	
	public EspecialidadeDTO(Integer id, String especialidade) {
		this.id = id;
		this.especialidade = especialidade;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
}