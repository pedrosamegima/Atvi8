package com.Ativ6.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ativ6.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository <Funcionario, Long> {

}
