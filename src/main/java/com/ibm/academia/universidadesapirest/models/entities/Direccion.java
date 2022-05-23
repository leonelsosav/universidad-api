package com.ibm.academia.universidadesapirest.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Direccion implements Serializable {
    @Serial
    private static final long serialVersionUID = 9066789353733486042L;

    private String calle;
    private String numero;
    private String codigoPostal;
    private String departamento;
    private String piso;
    private String localidad;
}
