package org.iesvdm.pruebaud3.dao;

import org.iesvdm.pruebaud3.domain.Categoria;
import org.iesvdm.pruebaud3.domain.Pelicula;

import java.util.List;

public interface PeliculaDAO {
    public void create(Pelicula pelicula);
    public List<Pelicula> getAll();
    public List<Pelicula> getPeliculaByIdCategoria(int idCategoria);
}
