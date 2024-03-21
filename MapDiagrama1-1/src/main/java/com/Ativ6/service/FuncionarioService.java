package com.Ativ6.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ativ6.DTO.FuncionarioDTO;
import com.Ativ6.entities.Funcionario;
import com.Ativ6.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	private final FuncionarioRepository funcionarioRepository;
	
	@Autowired
	public FuncionarioService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	public FuncionarioDTO salvar(FuncionarioDTO funcionarioDTO) {
		Funcionario funcionario = new Funcionario(funcionarioDTO.id(),funcionarioDTO.nome());
		Funcionario salvarFuncionario = funcionarioRepository.save(funcionario);
		return new FuncionarioDTO(salvarFuncionario.getId(), salvarFuncionario.getNome());
	}
	public FuncionarioDTO atualizar(Long id, FuncionarioDTO funcionarioDTO) {
	Funcionario existeFuncionario = funcionarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado") );
	

	existeFuncionario.setId(funcionarioDTO.id());
	existeFuncionario.setNome(funcionarioDTO.nome());
	
	Funcionario updateFuncionario = funcionarioRepository.save(existeFuncionario);
	return new FuncionarioDTO(updateFuncionario.getId(), updateFuncionario.getNome());
}

	public boolean delete(Long id) {
	Optional <Funcionario> existeFuncionario = funcionarioRepository.findById(id);
	if (existeFuncionario.isPresent()) {
		funcionarioRepository.deleteById(id);
		return true;
	}
	return false;
}
	public List<Funcionario> buscarTodos(){
	return funcionarioRepository.findAll();
}
	public Funcionario buscarPorId(Long id) {
	Optional <Funcionario> usuario = funcionarioRepository.findById(id);
	return usuario.orElse(null);
}

}

