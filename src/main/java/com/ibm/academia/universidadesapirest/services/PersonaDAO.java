package com.ibm.academia.universidadesapirest.services;

import java.util.Optional;

import com.ibm.academia.universidadesapirest.models.entities.Persona;

public interface PersonaDAO extends GenericoDAO<Persona>{
    Optional<Persona> buscarPorNombreYApellido(String nombre, String apellido);
    Optional<Persona> buscarPorDni(String dni);
    Iterable<Persona> buscarPersonaPorApellido(String apellido);
}
