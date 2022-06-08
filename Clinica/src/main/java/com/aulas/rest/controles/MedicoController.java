package com.aulas.rest.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aulas.rest.dto.EspecialidadeDTO;
import com.aulas.rest.dto.MedicoDTO;
import com.aulas.rest.servicos.EspecialidadeService;
import com.aulas.rest.servicos.MedicoService;

@RestController
@RequestMapping("/medico")
public class MedicoController {

	@Autowired
	MedicoService service;
	
	@Autowired
	EspecialidadeService especilidadeService;	
		
	@GetMapping
	public ResponseEntity<List<MedicoDTO>> pegaMedicos() {
		return ResponseEntity.ok(service.listarTodos());
	}

	@GetMapping("/{idmedico}")
	public ResponseEntity<MedicoDTO> pegarMedico(@PathVariable("idmedico") int idmedico) {
		MedicoDTO medicosDTO = service.pegarMedico(idmedico);
		return ResponseEntity.status(HttpStatus.OK).body(medicosDTO);
	}

	@PostMapping
	public ResponseEntity<MedicoDTO> salvar(@RequestBody MedicoDTO medico) {
		EspecialidadeDTO especialidade = especilidadeService.buscaPorNome(medico.getEspecialidade().getEspecialidade());
		medico.setEspecialidade(especialidade);
		System.out.println("Iniciando os logs");
		System.out.println("Medico nome: "+medico.getNome());
		System.out.println("Medico crm: "+medico.getCrm());
		System.out.println("Medico telefone: "+medico.getTelefone());
		System.out.println("Medico dias: "+medico.getDias());
		System.out.println("Especialidade id: "+medico.getEspecialidade().getId());
		System.out.println("Especialidade nome: "+medico.getEspecialidade().getEspecialidade());
		MedicoDTO med = service.salvar(medico);
		return ResponseEntity.status(HttpStatus.CREATED).body(med);
	}

	@PutMapping("/{idmedico}")
	public ResponseEntity<MedicoDTO> alterar(@PathVariable("idmedico") int idmedico, @RequestBody MedicoDTO medico) {
	 	return ResponseEntity.status(HttpStatus.OK).body(service.alterar(idmedico, medico));
	}

	@DeleteMapping("/{idmedico}")
	public ResponseEntity<MedicoDTO> excluir(@PathVariable("idmedico") int idmedico) {
		try {
			service.excluir(idmedico);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}