package com.ibm.academia.universidadesapirest.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pabellones", schema = "universidad")
public class Pabellon implements Serializable {
    @Serial
    private static final long serialVersionUID = 8678590545264428072L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "metros_cuadrados")
    private Double metrosCuadrados;

    @Column(name = "nombre", unique = true, nullable = false)
    private String nombre;

    @Column(name = "fecha_alta")
    private Date fechaAlta;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "codigoPostal", column = @Column(name = "codigo_postal")),
            @AttributeOverride(name = "departamento", column = @Column(name = "departamento"))
    })
    private Direccion direccion;

    @OneToMany(mappedBy = "pabellon", fetch = FetchType.LAZY)
    private Set<Aula> aulas;

    public Pabellon(Integer id, Double metrosCuadrados, String nombre, Direccion direccion)
    {
        this.id = id;
        this.metrosCuadrados = metrosCuadrados;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    @PrePersist
    private void antesPersistir() {
        this.fechaAlta = new Date();
    }

    @PreUpdate
    private void antesActualizar() {
        this.fechaModificacion = new Date();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "metrosCuadrados = " + metrosCuadrados + ", " +
                "nombre = " + nombre + ", " +
                "fechaAlta = " + fechaAlta + ", " +
                "fechaModificacion = " + fechaModificacion + ", " +
                "direccion = " + direccion + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Pabellon pabellon = (Pabellon) o;
        return id != null && Objects.equals(id, pabellon.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
