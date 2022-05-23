package com.ibm.academia.universidadesapirest.repositories;
import com.ibm.academia.universidadesapirest.models.entities.Persona;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("repositorioAlumnos")
public interface AlumnoRepository extends PersonaRepository {

	@Query("select a from Alumno a join fetch a.carrera b where b.nombre = ?1")
	Iterable<Persona> buscarAlumnoPorNombreCarrera(String nombre);
}