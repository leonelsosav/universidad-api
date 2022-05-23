package com.ibm.academia.universidadesapirest.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarreraDTO {
    private Integer id;

    @NotNull(message = "El nombre de la carrera no puede ser nulo")
    @NotEmpty(message = "El nombre de la carrera no puede estar vacio")
    @Size(min = 5, max = 80)
    private String nombre;

    @Positive(message = "El numero de cantidad de materias debe ser positivo")
    private Integer cantidadMaterias;

    @Positive(message = "El numero de cantidad de años debe ser positivo")
    private Integer cantidadAnios;
}
