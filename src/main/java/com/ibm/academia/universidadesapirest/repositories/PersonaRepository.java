package com.ibm.academia.universidadesapirest.repositories;

import java.util.Optional;

import com.ibm.academia.universidadesapirest.models.entities.Persona;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PersonaRepository extends CrudRepository<Persona, Integer> {

	@Query("select a from Persona a where a.nombre = ?1 and a.apellido = ?2")
	Optional<Persona> buscarPorNombreYApellido(String nombre, String apellido);
	
	@Query("select a from Persona a where a.dni = ?1")
	Optional<Persona> buscarPorDni(String dni);
	
	@Query("select a from Persona a where a.apellido like %?1%")
	Iterable<Persona> buscarPersonaPorApellido(String apellido);
	
	@Query("select a from Persona a where a.id = ?1")
	Persona buscarPorId(Integer id);
}