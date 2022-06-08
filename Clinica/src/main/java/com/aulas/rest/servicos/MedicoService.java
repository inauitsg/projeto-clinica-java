package com.aulas.rest.servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.aulas.rest.dto.MedicoDTO;
import com.aulas.rest.entidades.Especialidade;
import com.aulas.rest.entidades.Medico;
import com.aulas.rest.repositorios.EspecialidadeRepositorio;
import com.aulas.rest.repositorios.MedicoRepositorio;
import com.aulas.rest.servicos.excecoes.RecursoNaoEncontrado;

@Service
public class MedicoService {

	@Autowired	
	private MedicoRepositorio repo;
	
	@Autowired
	private EspecialidadeRepositorio repoesp;

	public List<MedicoDTO> listarTodos(){
		List<Medico> medicos = repo.findAll();

		List<MedicoDTO> medicosDTO = new ArrayList<>();		

		for(Medico med : medicos) {
			medicosDTO.add(new MedicoDTO(med));
		}		
		return medicosDTO;
	}

	public MedicoDTO salvar(MedicoDTO medico) {
		Medico med = new Medico(medico);
		med = repo.save(med);
		return new MedicoDTO(med);
	}

	public MedicoDTO pegarMedico(int idmedico) {
		Optional<Medico> obj = repo.findById(idmedico);	
		Medico med = obj.orElseThrow(() -> new RecursoNaoEncontrado("Médico não encontrado."));
		//med.setEspecialidade(repoesp.findById(med.getEspecialidade().getId()));
		med.setEspecialidade(new Especialidade());
		
		return new MedicoDTO(med);
	}

	public MedicoDTO alterar(int idmedico, MedicoDTO medico) {
		Optional<Medico> obj = repo.findById(idmedico);
		Medico med = obj.orElseThrow(() -> new RecursoNaoEncontrado("Médico não encontrado."));

		med.setEspecialidade(new Especialidade(medico.getEspecialidade()));
		med.setNome(medico.getNome());
		med.setCrm(medico.getCrm());
		med.setTelefone(medico.getTelefone());
		med.setDias(medico.getDias());
		med = repo.save(med);
		return new MedicoDTO(med);
	}

	public void excluir(int idmedico) {
		try {
			repo.deleteById(idmedico);
		}
		catch(EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("Contato inexistente");
		}
	}
}