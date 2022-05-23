package com.ibm.academia.universidadesapirest.repositories;

import com.ibm.academia.universidadesapirest.models.entities.Persona;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("repositorioProfesores")
public interface ProfesorRepository extends PersonaRepository {

  @Query("select a from Profesor a join fetch a.carreras b where b." + "nombre = ?1")
  Iterable<Persona> findProfesoresByCarrera(String carrera);

}