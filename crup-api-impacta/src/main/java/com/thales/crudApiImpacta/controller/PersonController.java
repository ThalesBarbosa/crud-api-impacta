package com.thales.crudApiImpacta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import com.thales.crudApiImpacta.dtos.PersonDTO;
import com.thales.crudApiImpacta.entity.Person;
import com.thales.crudApiImpacta.repositories.PersonRepo;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    @Autowired
    private PersonRepo personRepo;


    @GetMapping("/findById/{identificador}")
    public ResponseEntity<PersonDTO> getById(@PathVariable("identificador") Long identificador) {
        return personRepo.findById(identificador).map(mapToPersonDTO).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/findByNome/{nome}")
    public List<Person> get(@PathVariable("nome") String nome) {
        return  personRepo.findByNome(nome);
    }
    
    @PostMapping
    public void post(@RequestBody Person person) {
    	System.out.println(person.getIdentificador());
    	System.out.println(person.getNome());
    	System.out.println(person.getDataNascimento());
    	personRepo.save(person);
    }
    
    @PutMapping
    public String put(@RequestBody Person person) {
    	
    	Optional<Person> optional = personRepo.findById(person.getIdentificador());
    	
    	if(optional.isPresent()) {
    		Person db = optional.get();
    		db.setIdentificador(person.getIdentificador());
    		db.setNome(person.getNome());
    		db.setDataNascimento(person.getDataNascimento());
    		
    		personRepo.save(db);
    		
    		return "Person successfully updated!";
    	} else {
    		return "Person not found, please try updating an existent Person register";
    	}
    	
    }
    
    @DeleteMapping
    public String delete(@RequestBody Person person) {
    	
    	Optional<Person> optional = personRepo.findById(person.getIdentificador());
    	
    	if(optional.isPresent()) {
    		Person db = optional.get();
    		db.setIdentificador(person.getIdentificador());
    		db.setNome(person.getNome());
    		db.setDataNascimento(person.getDataNascimento());
    		
    		personRepo.delete(db);
    		
    		return "Person successfully deleted!";
    	} else {
    		return "Person not found, please try deleting an existent Person register";
    	}
    	
    }

    private Function<Person, PersonDTO> mapToPersonDTO = p -> PersonDTO.builder().identificador(p.getIdentificador()).nome(p.getNome()).dataNascimento(p.getDataNascimento()).build();
}
