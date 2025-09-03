package org.lessons.library.java.spring_la_mia_libreria_crud.repository;


import java.util.Optional;

import org.lessons.library.java.spring_la_mia_libreria_crud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

}