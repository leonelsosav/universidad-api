package com.ibm.academia.universidadesapirest.services;

import java.util.List;
import java.util.Optional;

import com.ibm.academia.universidadesapirest.models.entities.Direccion;
import com.ibm.academia.universidadesapirest.models.entities.Pabellon;

public interface PabellonDAO extends GenericoDAO<Pabellon> {

  Optional<List<Pabellon>> getPabellonesByDireccion(Direccion direccion);

  Optional<List<Pabellon>> getPabellonesByNombre(String nombre);

  Optional<Pabellon> updatePabellon(Pabellon actual, Pabellon changed);
}
