	package com.Ativ6.controller;

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

import com.Ativ6.entities.Dependente;
import com.Ativ6.service.DependenteService;

@RestController
@RequestMapping("/dependente")
public class DependenteController {
	private final DependenteService dependenteService;

	@Autowired
	public DependenteController(DependenteService dependenteService) {
		this.dependenteService = dependenteService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Dependente> getDependenteById(@PathVariable Long id) {
		Dependente dependente = dependenteService.getDependenteById(id);
		if (dependente != null) {
			return ResponseEntity.ok(dependente);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Dependente>> getAllDependente() {
		List<Dependente> dependente = dependenteService.getAllDependente();
		return ResponseEntity.ok(dependente);
	}

	@PostMapping("/")
	public ResponseEntity<Dependente> criarDependente(@RequestBody Dependente dependente) {
		Dependente criarDependente = dependenteService.salvarDependente(dependente);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarDependente);
	}


	@PutMapping("/{id}")
	public ResponseEntity<Dependente> updateDependente(@PathVariable Long id,@RequestBody Dependente dependente) {
		Dependente updatedDependente = dependenteService.updateDependente(id, dependente);
		if (updatedDependente != null) {
			return ResponseEntity.ok(updatedDependente);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDependente(@PathVariable Long id) {
		boolean deleted = dependenteService.deleteDependente(id);
		if (deleted) {
			return ResponseEntity.ok().body("O depedente foi excluído com sucesso.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}


}

