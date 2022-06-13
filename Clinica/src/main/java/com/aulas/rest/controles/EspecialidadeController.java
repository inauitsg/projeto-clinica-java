package com.aulas.rest.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping("/especialidade")
public class EspecialidadeController {
	
	@Autowired
	EspecialidadeService service;
	
	@ApiOperation(value = "Lista todos as especialidades médicas cadastradas")
	@GetMapping
	public ResponseEntity<List<EspecialidadeDTO>> pegaEspecialidade() {
		return ResponseEntity.ok(service.listarTodos());
	}
	
	@ApiOperation(value = "Seleciona especialidade médica pelo id cadastrado")
	@GetMapping("/{idespecialidade}")
	public ResponseEntity<EspecialidadeDTO> pegarEspecialidade(@PathVariable("idespecialidade") int idespecialidade) {
		EspecialidadeDTO userDTO = service.pegarEspecialidade(idespecialidade);
		return ResponseEntity.status(HttpStatus.OK).body(userDTO);
	}
	
	@ApiOperation(value = "Salva o cadastro da especialidae médica")
	@PostMapping
	public ResponseEntity<EspecialidadeDTO> salvar(@RequestBody EspecialidadeDTO especialidade) {
		EspecialidadeDTO user = service.salvar(especialidade);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	@ApiOperation(value = "Altera o cadastro da especialidae médica")
	@PutMapping("/{idespecialidade}")
	public ResponseEntity<EspecialidadeDTO> alterar(@PathVariable("idespecialidade") int idespecialidade, @RequestBody EspecialidadeDTO especialidade) {
		return ResponseEntity.status(HttpStatus.OK).body(service.alterar(idespecialidade, especialidade));
	}
	
	@ApiOperation(value = "Deleta o cadastro da especialidade médica")
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