package com.ibm.academia.universidadesapirest.models.dto;

import com.ibm.academia.universidadesapirest.enums.TipoPizarron;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AulaDTO {
    private Integer id;
    private Integer numeroAula;
    private TipoPizarron tipoPizarron;
}
