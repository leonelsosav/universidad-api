package com.ibm.academia.universidadesapirest.mapper;

import com.ibm.academia.universidadesapirest.models.dto.CarreraDTO;
import com.ibm.academia.universidadesapirest.models.entities.Carrera;

import org.springframework.stereotype.Component;

@Component
public class CarreraMapper {

	public static CarreraDTO mapCarrera(Carrera carrera) {

        return new CarreraDTO(
                carrera.getId(),
                carrera.getNombre(),
                carrera.getCantidadAnios(),
                carrera.getCantidadMaterias()
        );
	}
}