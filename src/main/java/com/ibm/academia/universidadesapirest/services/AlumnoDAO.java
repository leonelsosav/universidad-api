package com.ibm.academia.universidadesapirest.services;

import com.ibm.academia.universidadesapirest.models.entities.Carrera;
import com.ibm.academia.universidadesapirest.models.entities.Persona;

public interface AlumnoDAO extends PersonaDAO{
    Iterable<Persona> buscarAlumnoPorNombreCarrera(String nombre);
    Persona actualizar(Persona alumnoEncontrado, Persona alumno);
    Persona asociarCarreraAlumno(Persona alumno, Carrera carrera);
}