package org.lessons.library.java.spring_la_mia_libreria_crud.controller;

import java.util.List;

import org.lessons.library.java.spring_la_mia_libreria_crud.model.Book;
import org.lessons.library.java.spring_la_mia_libreria_crud.service.BookService;
import org.lessons.library.java.spring_la_mia_libreria_crud.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private GenreService genreService;

    @GetMapping()
  public String index(Authentication authentication, Model model) {
    List<Book> books = bookService.findAll();
    model.addAttribute("books", books);
    model.addAttribute("username", authentication.getName());

    return "books/index";
  }
@GetMapping("/{id}")
public String show(@PathVariable Integer id, Model model, Authentication authentication) {
    Book book = bookService.findById(id);
    model.addAttribute("username", authentication.getName());
    model.addAttribute("book", book);
    return "books/show";
}
@GetMapping("/search")
public String search(@RequestParam String query, Model model, Authentication authentication) {
    List<Book> books = bookService.searchByTitleOrAuthorOrGenre(query);

    model.addAttribute("books", books);
    model.addAttribute("query", query);
    model.addAttribute("username", authentication.getName());
    return "books/index";
}
@GetMapping("/create")
public String create(Authentication authentication, Model model) {
    model.addAttribute("book", new Book());
    model.addAttribute("genres", genreService.findAllGenres());
    model.addAttribute("username", authentication.getName());

    return "books/create-or-edit";
}

@PostMapping("/create")
public String store(@Valid @ModelAttribute("book") Book formBook, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
        model.addAttribute("genres", genreService.findAllGenres());
        return "books/create-or-edit";
    }
    model.addAttribute("book", formBook);

    bookService.create(formBook);
    return "redirect:/books";

}
@GetMapping("/edit/{id}")
public String edit(@PathVariable Integer id, Model model, Authentication authentication) {
    Book book = bookService.findById(id);
    model.addAttribute("book", book);
    model.addAttribute("genres", genreService.findAllGenres());
    model.addAttribute("username", authentication.getName());
    model.addAttribute("edit", true);
    return "books/create-or-edit";
}
@PostMapping("/edit/{id}")
public String update(@Valid @ModelAttribute("book") Book formBook, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
       
    model.addAttribute("genres", genreService.findAllGenres());
        return "books/create-or-edit";
    }
    model.addAttribute("book", formBook);

    bookService.update(formBook);
    return "redirect:/books";
}

@PostMapping("/delete/{id}")
public String delete(@PathVariable("id") Integer id) {
    
    bookService.deleteById(id);
    return "redirect:/books";
}
  
}


