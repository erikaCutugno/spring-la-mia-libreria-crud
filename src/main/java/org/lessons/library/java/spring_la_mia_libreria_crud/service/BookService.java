package org.lessons.library.java.spring_la_mia_libreria_crud.service;

import java.util.List;
import java.util.Optional;

import org.lessons.library.java.spring_la_mia_libreria_crud.model.Book;
import org.lessons.library.java.spring_la_mia_libreria_crud.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Integer id) {
        Optional<Book> bookAttempt = bookRepository.findById(id);
        if(bookAttempt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Book not found");
        }
        return bookAttempt.get();
    }
    public Optional<Book> getById(Integer id) {
    return bookRepository.findById(id);
    }

//     public List<Book> searchByTitleOrAuthorAndGenre(String query, String genre) {
//     return bookRepository.findByGenre_NameIgnoreCaseAndTitleContainingIgnoreCaseOrGenre_NameIgnoreCaseAndAuthorContainingIgnoreCase(
//         genre, query, genre, query);
// }

    public List<Book> findByGenreName(String genreName) {
    return bookRepository.findByGenre_NameContainingIgnoreCase(genreName);
}
    public List<Book> searchByTitleOrAuthorOrGenre(String query) {
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrGenre_NameContainingIgnoreCase(query, query, query);
    }
    public Book create(Book book) {
        return bookRepository.save(book);
    }
    public Book update(Book book) {
        return bookRepository.save(book);
    }
    public void deleteById(Integer id) {
        Book book = findById(id);
    bookRepository.delete(book);
    }

}
