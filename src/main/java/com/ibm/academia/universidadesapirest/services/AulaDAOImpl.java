package com.ibm.academia.universidadesapirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import com.ibm.academia.universidadesapirest.enums.TipoPizarron;
import com.ibm.academia.universidadesapirest.models.entities.Aula;
import com.ibm.academia.universidadesapirest.models.entities.Pabellon;
import com.ibm.academia.universidadesapirest.repositories.AulaRepository;

@Service
public class AulaDAOImpl extends GenericoDAOImpl<Aula, AulaRepository>  implements AulaDAO {

  @Autowired
  public AulaDAOImpl(AulaRepository repository) {
    super(repository);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<List<Aula>> findAulasByPizarron(TipoPizarron tipoPizarron) {
    List<Aula> result = (List<Aula>) repository.findAulasByTipoPizarron(tipoPizarron);
    
    if (!result.isEmpty()) {
      return Optional.of(result);
    }

    return Optional.empty();
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<List<Aula>> findAulasByPabellonNombre(String nombre) {
    List<Aula> result = (List<Aula>) repository.findAulasByPabellonNombre(nombre);
    
    if (!result.isEmpty()) {
      return Optional.of(result);
    }

    return Optional.empty();
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Aula> findAulaByNumeroAula(Integer numeroAula) {
    Aula result = repository.findAulaByNumeroAula(numeroAula);

    if (result != null) {
      return Optional.of(result);
    }

    return Optional.empty();
  }

  @Override
  @Transactional
  public Optional<Aula> update(Aula actual, Aula changed) {
    actual.setMedidas(changed.getMedidas());
    actual.setCantidadPupitres(changed.getCantidadPupitres());
    actual.setNumeroAula(changed.getNumeroAula());
    actual.setPabellon(changed.getPabellon());
    actual.setTipoPizarron(changed.getTipoPizarron());

    Aula result = repository.save(actual);
    return Optional.of(result);
  }

  @Override
  public Optional<Aula> addPabellonToAula(Aula aulaFound, Pabellon pabellonFound) {

    aulaFound.setPabellon(pabellonFound);
    Aula result = repository.save(aulaFound);

    return Optional.of(result);
  }
  
}
