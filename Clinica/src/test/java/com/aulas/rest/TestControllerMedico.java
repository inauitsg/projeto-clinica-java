package com.aulas.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.aulas.rest.dto.EspecialidadeDTO;
import com.aulas.rest.dto.MedicoDTO;
import com.aulas.rest.entidades.Especialidade;
import com.aulas.rest.entidades.Medico;
import com.aulas.rest.servicos.MedicoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TestControllerMedico {
	private Integer idExistente;
    private Integer idInexistente;
    private Medico medico;
    private Medico med;
    private MedicoDTO medicoDTO;
    private List<Medico> medicos;
    private EspecialidadeDTO especialidadeDTO;
    private Especialidade especialidade; 
    
    private MedicoDTO medicoExistente;
    private Medico medicoNovo;
    
    @Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MedicoService service;
	
	@Autowired
	ObjectMapper objectMapper;
	

	@BeforeEach
	void setup() {
		idExistente= 1;
		idInexistente = 2;
		medico = new Medico();
		med = new Medico();
		medicoDTO = new MedicoDTO();
		medicos = new ArrayList<>();
		especialidade = new Especialidade();
		especialidadeDTO = new EspecialidadeDTO(especialidade);
		
		medicoNovo = new Medico();
		medicoExistente = new MedicoDTO(1, "oftalmo", idExistente, "maria", "4444", "333333", "segunda");
		
		Mockito.when(service.pegarMedico(idExistente)).thenReturn(medicoExistente);
		Mockito.when(service.pegarMedico(idInexistente)).thenThrow(EntityNotFoundException.class);
		//Mockito.doThrow(service.delete(idInexistente)).doThrow(EntityNotFoundException.class);
		Mockito.when(service.salvar(any())).thenReturn(medicoExistente);
		Mockito.when(service.alterar(eq(idExistente), any())).thenReturn(medicoExistente);
		Mockito.when(service.alterar(eq(idInexistente), any())).thenThrow(EntityNotFoundException.class);
	}
	

	@Test
    void deveRetornarOkQuandoPesquisaIdExistente() throws Exception {
	    ResultActions result =	mockMvc.perform(get("/medico/{idmedico}", idExistente)
		       .accept(MediaType.APPLICATION_JSON));
	    result.andExpect(status().isOk());
	}
	
	@Test
	void deveRertornarStatus404QuandoPesquisaIdInexistente() throws Exception {
		ResultActions obj =	mockMvc.perform(get("/{idmedico}", idInexistente)
			                           .accept(MediaType.APPLICATION_JSON));
		
		    obj.andExpect(status().isNotFound());		    
	}
	
	@Test
	void deveRetornarStatus404QuandoAlterarMedicoInexistente() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(medicoNovo);
		ResultActions result = mockMvc.perform(put("/{idmedico}", idInexistente)
			   .content(jsonBody)
			   .contentType(MediaType.APPLICATION_JSON)
			   .accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNotFound());		       
	}
	
	//@Test
	void deveRetornarStatu201QuandoContatoSalvoComSucesso() throws Exception {
		medicoDTO.setEspecialidade(especialidadeDTO);
		String jsonBody = objectMapper.writeValueAsString(medicoNovo);
		ResultActions result = mockMvc.perform(post("/medico")
			   .content(jsonBody)
			   .contentType(MediaType.APPLICATION_JSON)
			   .accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isCreated());				
	}
	
	@Test
	void deveRetornarStatus200QuandoAlterarContatoExistenteComSucesso() throws Exception {
		
		String jsonBody = objectMapper.writeValueAsString(medicoExistente);
		ResultActions result = mockMvc.perform(put("/medico/{idmedico}", idExistente)
			   .content(jsonBody)
			   .contentType(MediaType.APPLICATION_JSON)
			   .accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());		       
	}

}
