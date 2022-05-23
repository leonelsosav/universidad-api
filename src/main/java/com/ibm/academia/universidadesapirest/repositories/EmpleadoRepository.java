package com.ibm.academia.universidadesapirest.repositories;

import com.ibm.academia.universidadesapirest.enums.TipoEmpleado;
import com.ibm.academia.universidadesapirest.models.entities.Persona;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("repositorioEmpleados")
public interface EmpleadoRepository extends PersonaRepository {

  @Query("select a from Empleado a where a.tipoEmpleado = ?1")
  Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado);

}