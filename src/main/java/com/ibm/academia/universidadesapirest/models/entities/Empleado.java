package com.ibm.academia.universidadesapirest.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.ibm.academia.universidadesapirest.enums.TipoEmpleado;

import java.io.Serial;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "empleados", schema = "universidad")
public class Empleado  extends Persona{
    @Serial
    private static final long serialVersionUID = -9193375253005755196L;

    @Column(name = "sueldo")
    private BigDecimal sueldo;

    @Column(name = "tipo_empleado")
    @Enumerated(EnumType.STRING)
    private TipoEmpleado tipoEmpleado;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pabellon_id", foreignKey = @ForeignKey(name = "FK_PABELLON_ID"))
    private Pabellon pabellon;

    public Empleado(Integer id, String nombre, String apellido, String dni, Direccion direccion, BigDecimal sueldo, TipoEmpleado tipoEmpleado)
    {
        super(id, nombre, apellido, dni, direccion);
        this.sueldo = sueldo;
        this.tipoEmpleado = tipoEmpleado;
    }

    @Override
    public String toString()
    {
        return super.toString() + "\tEmpleado [sueldo=" + sueldo + ", tipoEmpleado=" + tipoEmpleado + "]";
    }

}
