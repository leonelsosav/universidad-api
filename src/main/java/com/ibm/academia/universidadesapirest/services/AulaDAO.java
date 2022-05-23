package com.ibm.academia.universidadesapirest.services;

import java.util.List;
import java.util.Optional;

import com.ibm.academia.universidadesapirest.enums.TipoPizarron;
import com.ibm.academia.universidadesapirest.models.entities.Aula;
import com.ibm.academia.universidadesapirest.models.entities.Pabellon;

public interface AulaDAO extends GenericoDAO<Aula> {
  
  Optional<List<Aula>> findAulasByPizarron(TipoPizarron pizarron);
  
  Optional<List<Aula>> findAulasByPabellonNombre(String nombre);
  
  Optional<Aula> findAulaByNumeroAula(Integer numeroAula);

  Optional<Aula> update(Aula actual, Aula changed);

  Optional<Aula> addPabellonToAula(Aula aulaFound, Pabellon pabellonFound);

}
