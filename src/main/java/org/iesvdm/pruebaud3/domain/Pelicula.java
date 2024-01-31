package org.iesvdm.pruebaud3.domain;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pelicula {
    //no descripcion porque es muy larga
    private int id_pelicula;

    @NotBlank
    @Size(min=3, message = "#{msg.size}")
    private String titulo;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date fecha_lanzamiento;

    private int id_idioma;
    private int duracion_alquiler;

    @Min(value=0, message = "#{msg.rental_rate.min}")
    private BigDecimal rental_rate;

    @Min(value=1, message = "#{msg.duracion.min}")
    private int duracion;

    @DecimalMin(value="19.99",message = "#{msg.min}")
    private BigDecimal replacement_cost;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date ultima_actualizacion;
}
