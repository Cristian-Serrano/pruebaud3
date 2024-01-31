package org.iesvdm.pruebaud3.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.pruebaud3.domain.Categoria;
import org.iesvdm.pruebaud3.domain.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class PeliculaDAOImpl implements PeliculaDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Pelicula pelicula){
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert
                .withTableName("pelicula")
                .usingGeneratedKeyColumns("id_pelicula");
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("titulo",pelicula.getTitulo())
                .addValue("fecha_lanzamiento",pelicula.getFecha_lanzamiento())
                .addValue("id_idioma",pelicula.getId_idioma())
                .addValue("duracion_alquiler",pelicula.getDuracion_alquiler())
                .addValue("rental_rate",pelicula.getRental_rate())
                .addValue("duracion",pelicula.getDuracion())
                .addValue("replacement_cost",pelicula.getReplacement_cost())
                .addValue("ultima_actualizacion",pelicula.getUltima_actualizacion());

        Number number = simpleJdbcInsert.executeAndReturnKey(params);

        pelicula.setId_pelicula(number.intValue());

        log.info("Insertados {} registros.", number.intValue());
    }

    @Override
    public List<Pelicula> getAll(){
        List<Pelicula> listPeliculas = jdbcTemplate.query(
                "SELECT * FROM pelicula",
                (rs, rowNum) -> new Pelicula(
                        rs.getInt("id_pelicula"),
                        rs.getString("titulo"),
                        rs.getDate("fecha_lanzamiento"),
                        rs.getInt("id_idioma"),
                        rs.getInt("duracion_alquiler"),
                        rs.getBigDecimal("rental_rate"),
                        rs.getInt("duracion"),
                        rs.getBigDecimal("replacement_cost"),
                        rs.getDate("ultima_actualizacion")
                )
        );

        log.info("Devueltos {} registros.", listPeliculas.size());

        return listPeliculas;
    }

    public List<Pelicula> getPeliculaByIdCategoria(int idCategoria){
        List<Pelicula> listPel = jdbcTemplate.query("""
                            SELECT * FROM pelicula P 
                                left join pelicula_categoria PC on PC.id_pelicula = P.id_pelicula
                                left join categoria C on PC.id_categoria = C.id_categoria
                                WHERE P.id_categoria = ?""",
                (rs, rowNum) -> UtilDAO.newPelicula(rs),
                idCategoria
        );

        log.info("Devueltos {} registros.", listPel.size());

        return listPel;
    }
}
