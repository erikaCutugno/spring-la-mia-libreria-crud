package org.lessons.library.java.spring_la_mia_libreria_crud.repository;

import java.util.List;

import org.lessons.library.java.spring_la_mia_libreria_crud.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
    // List<Book> findByGenre_NameIgnoreCaseAndTitleContainingIgnoreCaseOrGenre_NameIgnoreCaseAndAuthorContainingIgnoreCase(
    // String genre1, String title,
    // String genre2, String author);

   public List<Book> findByGenre_NameContainingIgnoreCase(String genreName);
    public List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrGenre_NameContainingIgnoreCase(String title, String author, String genre);
}
