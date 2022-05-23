package com.ibm.academia.universidadesapirest.services;

import java.util.Optional;

import com.ibm.academia.universidadesapirest.enums.TipoEmpleado;
import com.ibm.academia.universidadesapirest.models.entities.Persona;

public interface EmpleadoDAO extends PersonaDAO {
  
  Optional<Iterable<Persona>> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado);

  Optional<Persona> updateEmployee(Persona actual, Persona changed);
}
