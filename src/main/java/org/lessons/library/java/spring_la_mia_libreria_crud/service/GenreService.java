package org.lessons.library.java.spring_la_mia_libreria_crud.service;

import java.util.List;
import java.util.Optional;

import org.lessons.library.java.spring_la_mia_libreria_crud.model.Book;
import org.lessons.library.java.spring_la_mia_libreria_crud.model.Genre;
import org.lessons.library.java.spring_la_mia_libreria_crud.repository.BookRepository;
import org.lessons.library.java.spring_la_mia_libreria_crud.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private BookRepository bookRepository;

    GenreService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }
     public Genre findById(Integer id) {
        Optional<Genre> genreAttempt = genreRepository.findById(id);
        if(genreAttempt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Genre not found");
        }
        return genreAttempt.get();
    }
    public Genre create(Genre genre) {
        return genreRepository.save(genre);
    }
    public Genre update(Genre genre) {
        return genreRepository.save(genre);
    }
    public void deleteById(Integer id) {
        Genre genre = findById(id);
        for (Book book : genre.getBooks()) {
            bookRepository.delete(book);
        }
        genreRepository.delete(genre);
    }

}
