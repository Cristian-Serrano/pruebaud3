package org.iesvdm.pruebaud3.dao;

import org.iesvdm.pruebaud3.domain.Pelicula;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilDAO {
    public static Pelicula newPelicula(ResultSet rs) throws SQLException {
        return new Pelicula(
                rs.getInt("id_pelicula"),
                rs.getString("titulo"),
                rs.getDate("fecha_lanzamiento"),
                rs.getInt("id_idioma"),
                rs.getInt("duracion_alquiler"),
                rs.getBigDecimal("rental_rate"),
                rs.getInt("duracion"),
                rs.getBigDecimal("replacement_cost"),
                rs.getDate("ultima_actualizacion")
        );
    }
}
