package org.lessons.library.java.spring_la_mia_libreria_crud.controller.api;

import java.util.List;
import java.util.Optional;

import org.lessons.library.java.spring_la_mia_libreria_crud.model.Book;
import org.lessons.library.java.spring_la_mia_libreria_crud.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;






@RestController
@CrossOrigin
@RequestMapping("/api/books")
public class BookRestController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> index(){
        List<Book> books = bookService.findAll();
        return books;
    }
@GetMapping("/{id}")
public ResponseEntity<Book> show(@PathVariable Integer id) {
    Optional<Book> bookAttempt = bookService.getById(id);
    if (bookAttempt.isEmpty()) {
        return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Book>(bookAttempt.get(), HttpStatus.OK);
}
    @GetMapping("/searchByGenre")
public List<Book> searchByGenre(@RequestParam("genre") String genreName) {
    return bookService.findByGenreName(genreName);
}
@GetMapping("/search")
public List<Book> search(@RequestParam("query") String query) {
    return bookService.searchByTitleOrAuthorOrGenre(query);
}

@PostMapping
public ResponseEntity<Book> store(@Valid @RequestBody Book book) {
    return new ResponseEntity<Book>(bookService.create(book), HttpStatus.OK);
}

@PutMapping("/{id}")
public ResponseEntity<Book> update(@Valid @PathVariable Integer id, @RequestBody Book book) {

    if (bookService.getById(id).isEmpty()) {
        return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
    }
    book.setId(id);
    return new ResponseEntity<Book>(bookService.update(book), HttpStatus.OK);
    }
@DeleteMapping("/{id}")
public ResponseEntity<Book> delete(@Valid @PathVariable Integer id) {
        if (bookService.getById(id).isEmpty()) {
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
        bookService.deleteById(id);
        return new ResponseEntity<Book>(HttpStatus.OK);
    }

}
