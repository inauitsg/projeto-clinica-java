package com.aulas.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.aulas.rest.dto.EspecialidadeDTO;
import com.aulas.rest.dto.MedicoDTO;
import com.aulas.rest.entidades.Especialidade;
import com.aulas.rest.entidades.Medico;
import com.aulas.rest.repositorios.MedicoRepositorio;
import com.aulas.rest.servicos.MedicoService;
import com.aulas.rest.servicos.excecoes.RecursoNaoEncontrado;

@ExtendWith(SpringExtension.class)
public class MedicoServiceTest {
	
	private Integer idExistente;
    private Integer idInexistente;
    private Medico medico;
    private Medico med;
    private MedicoDTO medicoDTO;
    private List<Medico> medicos;
    private EspecialidadeDTO especialidadeDTO;
    private Especialidade especialidade; 
    
    @InjectMocks
	private MedicoService service;
    
    @Mock
	private MedicoRepositorio repository;
	
	@BeforeEach
	void setup() {
		idExistente = 1;
		idInexistente = 100;
		medico = new Medico();
		med = new Medico();
		medicoDTO = new MedicoDTO();
		medicos = new ArrayList<>();
		especialidade = new Especialidade();
		especialidadeDTO = new EspecialidadeDTO(especialidade);
		
		
		Mockito.when(repository.findById(idExistente)).thenReturn(Optional.of(medico));
		Mockito.when(repository.findAll()).thenReturn(medicos);
		
		Mockito.doThrow(RecursoNaoEncontrado.class).when(repository).findById(idInexistente);
		Mockito.doNothing().when(repository).deleteById(idExistente);
		Mockito.doThrow(EntityNotFoundException.class).when(repository).deleteById(idInexistente);
		Mockito.when(repository.save(medico)).thenReturn(medico);
		
		
	}

	
	@Test
	void retornaNaoNuloQuandoConsultaPorIdExistente() {
		MedicoDTO med = service.pegarMedico(idExistente);
		//med.setEspecialidade(especialidade);
		Assertions.assertNotNull(med);
		Mockito.verify(repository).findById(idExistente);
	}
	
	@Test
	void lancaRecursoNaoEncontradoQuandoPesquisaIdInexistente() {
		Assertions.assertThrows(RecursoNaoEncontrado.class, () -> {
			service.pegarMedico(idInexistente);
		});
		Mockito.verify(repository).findById(idInexistente);
	}
	
	@Test
	void naoFazNadaQuandoDeletaIdExistente() {
		Assertions.assertDoesNotThrow(() -> {
			service.excluir(idExistente);
		});
	   Mockito.verify(repository, Mockito.times(1)).deleteById(idExistente);	
	}
	
	@Test
	void levantaEntityNotFoundExceptionQuandoDeleteIdInexistente() {
		Assertions.assertThrows(EntityNotFoundException.class, () ->{
			service.excluir(idInexistente);
		});
		Mockito.verify(repository, Mockito.times(1)).deleteById(idInexistente);		
	}
	
	@Test
	void retornaNaoNulQuandoConsultaTodos() {
		List<MedicoDTO> listaMedico = service.listarTodos();
		Assertions.assertNotNull(listaMedico);
	}
	
	//@Test
	void retornaMedicoQuandoSalvaComSucesso() {
		
		MedicoDTO medDTO = new MedicoDTO();
		medDTO.setEspecialidade(especialidadeDTO);
		//medico.setId(idExistente);
		MedicoDTO med = service.salvar(medDTO);
		Assertions.assertNotNull(med);
		Mockito.verify(repository).save(medico);		
	}
	
	@Test
	void retornaMedicoQuandoAlteradoComSucesso() {
		
		medicoDTO.setEspecialidade(especialidadeDTO);
		MedicoDTO m = service.alterar(idExistente, medicoDTO);
		
		Assertions.assertNotNull(m);
		Mockito.verify(repository).save(medico);
	}
}
