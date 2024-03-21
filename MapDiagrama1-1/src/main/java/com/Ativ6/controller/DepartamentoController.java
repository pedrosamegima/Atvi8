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

import com.Ativ6.entities.Departamento;
import com.Ativ6.service.DepartamentoService;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController {
	private final DepartamentoService departamentoService;

	@Autowired
	public DepartamentoController(DepartamentoService departamentoService) {
		this.departamentoService = departamentoService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Departamento> getalunoById(@PathVariable Long id) {
		Departamento departamento = departamentoService.getDepartamentoById(id);
		if (departamento != null) {
			return ResponseEntity.ok(departamento);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Departamento>> getAllDepartamento() {
		List<Departamento> departamento = departamentoService.getAllDepartamento();
		return ResponseEntity.ok(departamento);
	}

	@PostMapping("/")
	public ResponseEntity<Departamento> criarDepartamento(@RequestBody Departamento departamento) {
		Departamento criarDepartamento = departamentoService.salvardepartamento(departamento);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarDepartamento);
	}


	@PutMapping("/{id}")
	public ResponseEntity<Departamento> updateDepartamento(@PathVariable Long id, @RequestBody Departamento departamento) {
		Departamento updatedDepartamento = departamentoService.updatedepartamento(id, departamento);
		if (updatedDepartamento != null) {
			return ResponseEntity.ok(updatedDepartamento);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDepartamento(@PathVariable Long id) {
		boolean deleted = departamentoService.deleteDepartamento(id);
		if (deleted) {
			return ResponseEntity.ok().body("O departamento foi excluído com sucesso.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}


}


