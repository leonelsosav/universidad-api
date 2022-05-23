package com.ibm.academia.universidadesapirest.repositories;

import com.ibm.academia.universidadesapirest.models.entities.Direccion;
import com.ibm.academia.universidadesapirest.models.entities.Pabellon;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PabellonRepository extends CrudRepository<Pabellon, Integer> {

  Iterable<Pabellon> findPabellonesByDireccion(Direccion direccion);

  Iterable<Pabellon> findPabellonesByNombre(String nombre);

}
