package com.ibm.academia.universidadesapirest.services;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import com.ibm.academia.universidadesapirest.models.entities.Persona;
import com.ibm.academia.universidadesapirest.repositories.PersonaRepository;

public class PersonaDAOImpl extends GenericoDAOImpl<Persona, PersonaRepository> implements PersonaDAO{
    public PersonaDAOImpl(PersonaRepository repository)
    {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> buscarPorNombreYApellido(String nombre, String apellido) {
        return repository.buscarPorNombreYApellido(nombre, apellido);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> buscarPorDni(String dni)
    {
        return repository.buscarPorDni(dni);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> buscarPersonaPorApellido(String apellido) {
        return repository.buscarPersonaPorApellido(apellido);
    }
}
