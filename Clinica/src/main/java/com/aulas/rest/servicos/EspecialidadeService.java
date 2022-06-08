package com.aulas.rest.servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aulas.rest.dto.EspecialidadeDTO;
import com.aulas.rest.entidades.Especialidade;
import com.aulas.rest.repositorios.EspecialidadeRepositorio;
import com.aulas.rest.servicos.excecoes.RecursoNaoEncontrado;

@Service
public class EspecialidadeService {

	@Autowired
	private EspecialidadeRepositorio repo;

	public List<EspecialidadeDTO> listarTodos() {
		List<Especialidade> especialidades = repo.findAll();

		List<EspecialidadeDTO> especialidadesDTO = new ArrayList<>();

		for (Especialidade esp : especialidades) {
			especialidadesDTO.add(new EspecialidadeDTO(esp));
		}
		return especialidadesDTO;
	}

	public EspecialidadeDTO salvar(EspecialidadeDTO especialidade) {
		Especialidade esp = repo.save(new Especialidade(especialidade));
		return new EspecialidadeDTO(esp);
	}

	public EspecialidadeDTO pegarEspecialidade(int idespecialidade) {
		Optional<Especialidade> obj = repo.findById(idespecialidade);
		Especialidade esp = obj.orElseThrow(() -> new RecursoNaoEncontrado("Especialidade não encontrada."));
		return new EspecialidadeDTO(esp);
	}

	public EspecialidadeDTO alterar(int idespecialidade, EspecialidadeDTO especialidade) {
		Optional<Especialidade> obj = repo.findById(idespecialidade);
		Especialidade esp = obj.orElseThrow(() -> new RecursoNaoEncontrado("Especialidade não encontrada."));

		esp.setEspecialidade(especialidade.getEspecialidade());
		esp = repo.save(esp);
		return new EspecialidadeDTO(esp);
	}

	public void excluir(int idespecialidade) {
		repo.deleteById(idespecialidade);
	}

	public EspecialidadeDTO buscaPorNome(String especialidade) {
		Optional<Especialidade> obj = repo.findByEspecialidade(especialidade);
		Especialidade esp = obj.orElseThrow(() -> new RecursoNaoEncontrado("Especialidade não encontrada."));
		return new EspecialidadeDTO(esp);
	}
}