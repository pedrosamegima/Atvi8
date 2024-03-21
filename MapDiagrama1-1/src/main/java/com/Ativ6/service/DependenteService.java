package com.Ativ6.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ativ6.entities.Dependente;
import com.Ativ6.repository.DependenteRepository;

@Service
public class DependenteService {
	private final DependenteRepository dependenteRepository;
	
	@Autowired
    public DependenteService(DependenteRepository dependenteRepository) {
        this.dependenteRepository = dependenteRepository;
    }
	
	  public List<Dependente> getAllDependente() {
	        return dependenteRepository.findAll();
	    }

	    public Dependente getDependenteById(Long id) {
	        Optional<Dependente> dependente = dependenteRepository.findById(id);
	        return dependente.orElse(null);
	    }

	    public Dependente salvarDependente(Dependente dependente) {
	        return dependenteRepository.save(dependente);
	    }

	    public Dependente updateDependente(Long id, Dependente updatedDependente) {
	        Optional<Dependente> existingDependente = dependenteRepository.findById(id);
	        if (existingDependente.isPresent()) {
	            updatedDependente.setId(id);
	            return dependenteRepository.save(updatedDependente);
	        }
	        return null;
	    }

	    public boolean deleteDependente(Long id) {
	        Optional<Dependente> existingDependente = dependenteRepository.findById(id);
	        if (existingDependente.isPresent()) {
	        	dependenteRepository.deleteById(id);
	            return true;
	        }
	        return false;
	    }
	   


}