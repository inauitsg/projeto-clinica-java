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
import com.aulas.rest.servicos.EspecialidadeService;

@RestController
@RequestMapping("/especialidade")
public class EspecialidadeController {
	
	@Autowired
	EspecialidadeService service;

	@GetMapping
	public ResponseEntity<List<EspecialidadeDTO>> pegaEspecialidade() {
		return ResponseEntity.ok(service.listarTodos());
	}

	@GetMapping("/{idespecialidade}")
	public ResponseEntity<EspecialidadeDTO> pegarEspecialidade(@PathVariable("idespecialidade") int idespecialidade) {
		EspecialidadeDTO userDTO = service.pegarEspecialidade(idespecialidade);
		return ResponseEntity.status(HttpStatus.OK).body(userDTO);
	}

	@PostMapping
	public ResponseEntity<EspecialidadeDTO> salvar(@RequestBody EspecialidadeDTO especialidade) {
		EspecialidadeDTO user = service.salvar(especialidade);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

	@PutMapping("/{idespecialidade}")
	public ResponseEntity<EspecialidadeDTO> alterar(@PathVariable("idespecialidade") int idespecialidade, @RequestBody EspecialidadeDTO especialidade) {
		return ResponseEntity.status(HttpStatus.OK).body(service.alterar(idespecialidade, especialidade));
	}

	@DeleteMapping("/{idespecialidade}")
	public ResponseEntity<EspecialidadeDTO> excluir(@PathVariable("idespecialidade") int idespecialidade) {
		try {
			service.excluir(idespecialidade);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}