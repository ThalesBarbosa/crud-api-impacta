package com.thales.crudApiImpacta.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.thales.crudApiImpacta.dtos.PersonDTO;
import com.thales.crudApiImpacta.entity.Person;

@Repository
public interface PersonRepo extends CrudRepository<Person,Long> {

	List<Person> findByNome(String nome);
	
}
