package org.iesvdm.pruebaud3.service;

import org.iesvdm.pruebaud3.dao.PeliculaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.iesvdm.pruebaud3.domain.Pelicula;

import java.util.List;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaDAO peliculaDAO;

    public List<Pelicula> listAll() {

        return peliculaDAO.getAll();

    }

    public int conteoPeliculas(){
        List<Pelicula> listPel = listAll();

        int conteo = (int)listPel.stream()
                .count();

        return conteo;
    }

    public List<Pelicula> peliculaPorCategoria(int id_categoria){
        List<Pelicula> listPel = peliculaDAO.getPeliculaByIdCategoria(id_categoria);
        return listPel;
    }

    public void create(Pelicula pelicula) {

        peliculaDAO.create(pelicula);

    }
}
