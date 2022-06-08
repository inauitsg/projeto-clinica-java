package com.aulas.rest.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aulas.rest.dto.EspecialidadeDTO;


@Entity
@Table(name = "tb_especialidade")
public class Especialidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String especialidade;


	public Especialidade() {

	}

	public Especialidade(Integer id, String especialidade)  {
		this.id = id;
		this.especialidade = especialidade;
	}

	public Especialidade(EspecialidadeDTO especialidade) {
		this.id = especialidade.getId();
		this.especialidade = especialidade.getEspecialidade();
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