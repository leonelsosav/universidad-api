package com.ibm.academia.universidadesapirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import com.ibm.academia.universidadesapirest.enums.TipoEmpleado;
import com.ibm.academia.universidadesapirest.models.entities.Persona;
import com.ibm.academia.universidadesapirest.repositories.EmpleadoRepository;
import com.ibm.academia.universidadesapirest.repositories.PersonaRepository;

@Service
public class EmpleadoDAOImpl extends PersonaDAOImpl implements EmpleadoDAO {

  @Autowired
  public EmpleadoDAOImpl(@Qualifier("repositorioEmpleados") PersonaRepository repository) {
    super(repository);
  }


  @Override
  @Transactional(readOnly = true)
  public Optional<Iterable<Persona>> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado) {
    Iterable<Persona> result = ((EmpleadoRepository) repository).findEmpleadoByTipoEmpleado(tipoEmpleado);

    if (result != null && result.iterator().hasNext()) {
      return Optional.of(result);
    }

    return Optional.empty();
  }

  @Override
  @Transactional
  public Optional<Persona> updateEmployee(Persona actual, Persona changed) {
    actual.setNombre(changed.getNombre());
    actual.setApellido(changed.getApellido());
    actual.setDireccion(changed.getDireccion());

    Persona result = repository.save(actual);
    return Optional.of(result);
  }
  
}
