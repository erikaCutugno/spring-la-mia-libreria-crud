package org.lessons.library.java.spring_la_mia_libreria_crud.controller;


import org.lessons.library.java.spring_la_mia_libreria_crud.model.Genre;
import org.lessons.library.java.spring_la_mia_libreria_crud.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;


@Controller
@RequestMapping("/genres")
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("genres", genreService.findAllGenres());
        return "genres/index";
    }
    @GetMapping("/create")
public String create(Authentication authentication, Model model) {
    model.addAttribute("genre", new Genre());
    model.addAttribute("genres", genreService.findAllGenres());
    model.addAttribute("username", authentication.getName());

    return "genres/create-or-edit";
}

@PostMapping("/create")
public String store(@Valid @ModelAttribute("genre") Genre formGenre, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
        model.addAttribute("genres", genreService.findAllGenres());
        return "genres/create-or-edit";
    }
    model.addAttribute("genre", formGenre);

    genreService.create(formGenre);
    return "redirect:/genres";

}
@GetMapping("/edit/{id}")
public String edit(@PathVariable Integer id, Model model, Authentication authentication) {
    Genre genre = genreService.findById(id);
    model.addAttribute("genre", genre);
    // model.addAttribute("genres", genreService.findAllGenres());
    model.addAttribute("username", authentication.getName());
    model.addAttribute("edit", true);
    return "genres/create-or-edit";
}
@PostMapping("/edit/{id}")
public String update(@Valid @ModelAttribute("genre") Genre formGenre, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
        model.addAttribute("genres", genreService.findAllGenres());
        return "genres/create-or-edit";
    }
    model.addAttribute("genre", formGenre);

    genreService.update(formGenre);
    return "redirect:/genres";
}
@PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        genreService.deleteById(id);
        return "redirect:/genres";
}
}
