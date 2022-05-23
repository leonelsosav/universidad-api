package com.ibm.academia.universidadesapirest.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "profesores", schema = "universidad")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Profesor extends Persona{
    @Serial
    private static final long serialVersionUID = 8687996991425486104L;

    @Column(name = "sueldo")
    private BigDecimal sueldo;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "profesor_carrera", schema = "universidad",
            joinColumns = @JoinColumn(name = "profesor_id"),
            inverseJoinColumns = @JoinColumn(name = "carrera_id")
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "profesores"})
    private Set<Carrera> carreras;

    public Profesor(Integer id, String nombre, String apellido, String dni, Direccion direccion, BigDecimal sueldo)
    {
        super(id, nombre, apellido, dni, direccion);
        this.sueldo = sueldo;
    }

    @Override
    public String toString()
    {
        return super.toString() + "\tProfesor [sueldo=" + sueldo + "]";
    }
}
