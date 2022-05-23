package com.ibm.academia.universidadesapirest.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "carreras", schema = "universidad")
public class Carrera implements Serializable {
    @Serial
    private static final long serialVersionUID = 2707204908618885801L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El nombre de la carrera no puede ser nulo")
    @NotEmpty(message = "El nombre de la carrera no puede estar vacio")
    @Size(min = 5, max = 80)
    @Column(name = "nombre", unique = true, nullable = false, length = 80)
    private String nombre;

    @Positive(message = "El numero de cantidad de materias debe ser positivo")
    @Column(name = "cantidad_materias")
    private Integer cantidadMaterias;

    @Positive(message = "El numero de cantidad de años debe ser positivo")
    @Column(name = "cantidad_anios")
    private Integer cantidadAnios;

    @Column(name = "fecha_alta")
    private Date fechaAlta;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @OneToMany(mappedBy = "carrera", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"carrera"})
    private Set<Alumno> alumnos;

    @ManyToMany(mappedBy = "carreras", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"carreras"})
    private Set<Profesor> profesores;

    public Carrera(Integer id, String nombre, Integer cantidadMaterias, Integer cantidadAnios)
    {
        this.id = id;
        this.nombre = nombre;
        this.cantidadMaterias = cantidadMaterias;
        this.cantidadAnios = cantidadAnios;
    }

    @PrePersist
    private void antesPersistir()
    {
        this.fechaAlta = new Date();
    }

    @PreUpdate
    private void antesActualizar()
    {
        this.fechaModificacion = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Carrera carrera = (Carrera) o;
        return id != null && Objects.equals(id, carrera.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "nombre = " + nombre + ", " +
                "cantidadMaterias = " + cantidadMaterias + ", " +
                "cantidadAnios = " + cantidadAnios + ", " +
                "fechaAlta = " + fechaAlta + ", " +
                "fechaModificacion = " + fechaModificacion + ")";
    }
}
