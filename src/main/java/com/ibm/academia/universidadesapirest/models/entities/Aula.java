package com.ibm.academia.universidadesapirest.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibm.academia.universidadesapirest.enums.TipoPizarron;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "aulas", schema = "universidad")
public class Aula implements Serializable {
    @Serial
    private static final long serialVersionUID = -5837636043930763670L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero_aula", nullable = false)
    private Integer numeroAula;

    @Column(name = "medidas")
    private String medidas;

    @Column(name = "cantidad_pupitres")
    private Integer cantidadPupitres;

    @Column(name = "tipo_pizarron")
    @Enumerated(EnumType.STRING)
    private TipoPizarron tipoPizarron;

    @Column(name = "fecha_alta")
    private Date fechaAlta;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "pabellon_id", foreignKey = @ForeignKey(name = "FK_PABELLON_ID"))
    @JsonIgnore
    private Pabellon pabellon;

    public Aula(Integer id, Integer numeroAula, String medidas, Integer cantidadPupitres, TipoPizarron tipoPizarron)
    {
        this.id = id;
        this.numeroAula = numeroAula;
        this.medidas = medidas;
        this.cantidadPupitres = cantidadPupitres;
        this.tipoPizarron = tipoPizarron;
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
        Aula aula = (Aula) o;
        return id != null && Objects.equals(id, aula.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
