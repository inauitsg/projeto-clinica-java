package com.aulas.rest.dto;

import java.io.Serializable;
import java.time.Instant;
import com.aulas.rest.entidades.Medico;

public class MedicoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private String crm;
	private String telefone;
	private String dias;
	private Instant createdAT;
	private Instant updateAT;
	private EspecialidadeDTO especialidade;

	public MedicoDTO() {

	}

	public MedicoDTO(Medico medico) {
		if (medico != null) {
			this.id = medico.getId();
			this.nome = medico.getNome();
			this.crm = medico.getCrm();
			this.telefone = medico.getTelefone();
			this.dias = medico.getDias();

			this.especialidade = new EspecialidadeDTO(medico.getEspecialidade());
			this.createdAT = medico.getCreatedAt();
			this.updateAT = medico.getUpdateAt();
		}
	}

	public MedicoDTO(Integer idEspecialide, String especialidade, Integer id, String nome, String crm, String telefone,
			String dias) {
		this.id = id;
		this.nome = nome;
		this.crm = crm;
		this.telefone = telefone;
		this.dias = dias;
		this.especialidade = new EspecialidadeDTO(idEspecialide, especialidade);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDias() {
		return dias;
	}

	public void setDias(String dias) {
		this.dias = dias;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Instant getCreatedAT() {
		return createdAT;
	}

	public void setCreatedAT(Instant createdAT) {
		this.createdAT = createdAT;
	}

	public Instant getUpdateAT() {
		return updateAT;
	}

	public void setUpdateAT(Instant updateAT) {
		this.updateAT = updateAT;
	}

	public EspecialidadeDTO getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(EspecialidadeDTO especialidade) {
		this.especialidade = especialidade;
	}
}