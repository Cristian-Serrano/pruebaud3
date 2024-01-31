package org.iesvdm.pruebaud3.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class Categoria {
    private int id_categoria;
    private String nombre;
    private Date ultima_actualizacion;
}
