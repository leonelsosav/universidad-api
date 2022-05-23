package com.ibm.academia.universidadesapirest.services;

import java.util.Optional;

import com.ibm.academia.universidadesapirest.models.entities.Carrera;
import com.ibm.academia.universidadesapirest.models.entities.Persona;

public interface ProfesorDAO extends PersonaDAO {
  
  Optional<Iterable<Persona>> findProfesoresByCarrera(String carrera);

  Optional<Persona> updateProfesor(Persona actualProfesor, Persona profesorChanged);

  Optional<Persona> addProfessorToCareer(Persona professor, Carrera career);
}
