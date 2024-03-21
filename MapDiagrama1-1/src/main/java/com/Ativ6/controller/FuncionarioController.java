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

import com.Ativ6.DTO.FuncionarioDTO;
import com.Ativ6.entities.Funcionario;
import com.Ativ6.service.FuncionarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
	private final FuncionarioService funcionarioService;
	
	@Autowired
	public FuncionarioController(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> getFuncionarioById(@PathVariable Long id){
		Funcionario funcionario = funcionarioService.buscarPorId(id);
		if(funcionario != null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Funcionario>>getAllFuncionario(){
		List<Funcionario> Funcionario = funcionarioService.buscarTodos();
		return ResponseEntity.ok(Funcionario);
	}

	@PostMapping("/")
	public ResponseEntity<FuncionarioDTO> criar(@RequestBody FuncionarioDTO funcionarioDTO){
		FuncionarioDTO salvarFuncionarioDTO = funcionarioService.salvar(funcionarioDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvarFuncionarioDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<FuncionarioDTO> updateFuncionario(@PathVariable Long id, @RequestBody @Valid FuncionarioDTO funcionarioDTO){
		FuncionarioDTO updateFuncionarioDTO = funcionarioService.atualizar(id, funcionarioDTO);
		if(updateFuncionarioDTO != null) {
			return ResponseEntity.ok(updateFuncionarioDTO);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Funcionario> deleteFuncionario(@PathVariable Long id){
		boolean delete = 
				funcionarioService.delete(id);
		if(delete) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
		
	}
}

	


