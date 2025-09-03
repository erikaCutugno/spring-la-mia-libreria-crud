package org.lessons.library.java.spring_la_mia_libreria_crud.repository;

import org.lessons.library.java.spring_la_mia_libreria_crud.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    
}
