package org.iesvdm.pruebaud3.controller;

import jakarta.validation.Valid;
import org.iesvdm.pruebaud3.domain.Pelicula;
import org.iesvdm.pruebaud3.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping({"/peliculas", "/films"})
    public String listar(Model model){

        List<Pelicula> listAllPel =  peliculaService.listAll();
        model.addAttribute("listaPeliculas", listAllPel);

        model.addAttribute("conteo",peliculaService.conteoPeliculas());

        model.addAttribute("listaPeliculasHorror",peliculaService.peliculaPorCategoria(11));

        return "peliculas";
    }

    @GetMapping({"peliculas/crear","films/create"})
    public String crear(Model model){

        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula", pelicula);

        return "crearPelicula";
    }

    @PostMapping({"peliculas/crear","films/create"})
    public String submitCrear(@Valid @ModelAttribute("pelicula") Pelicula pelicula, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {
            model.addAttribute("pelicula", pelicula);

            return "crearPelicula";
        }

        peliculaService.create(pelicula);

        return "redirect:/peliculas";
    }
}
