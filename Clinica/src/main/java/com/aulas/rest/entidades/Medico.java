package com.aulas.rest.entidades;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.aulas.rest.dto.MedicoDTO;

@Entity
@Table(name = "tb_medico")
public class Medico {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String crm;
	private String telefone;
	private String dias;
	@Column(name="CREATED_AT", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant createdAt;
	@Column(name="UPDATE_AT",columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant updateAt;
	@ManyToOne
	@JoinColumn(name="ESPECIALIDADE_ID")
	private Especialidade especialidade;

	public Medico() {

	}

	public Medico(Integer id, String nome, String crm, String telefone, String dias) {		
		this.id = id;
		this.nome = nome;
		this.crm = crm;
		this.telefone = telefone;
		this.dias = dias;
	}

	public Medico(MedicoDTO medico) {
		this.id = medico.getId();
		this.nome = medico.getNome();
		this.crm = medico.getCrm();
		this.telefone = medico.getTelefone();
		this.dias = medico.getDias();
		this.especialidade = new Especialidade(medico.getEspecialidade());
	}

	@PrePersist
	public void prePersit() {
		createdAt = Instant.now();
	}

	@PreUpdate
	public void preUpdate() {
		updateAt = Instant.now();
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public Instant getUpdateAt() {
		return updateAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

}