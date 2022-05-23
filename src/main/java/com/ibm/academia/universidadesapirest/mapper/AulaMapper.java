package com.ibm.academia.universidadesapirest.mapper;

import com.ibm.academia.universidadesapirest.models.dto.AulaDTO;
import com.ibm.academia.universidadesapirest.models.entities.Aula;

import org.springframework.stereotype.Component;

@Component
public class AulaMapper {
  public static AulaDTO mapAula(Aula aula){
    return new AulaDTO(
            aula.getId(),
            aula.getNumeroAula(),
            aula.getTipoPizarron()
    );
  }
}
