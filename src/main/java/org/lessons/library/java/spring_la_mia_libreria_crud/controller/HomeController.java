package org.lessons.library.java.spring_la_mia_libreria_crud.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/")
public class HomeController {
        @GetMapping
    public String home() {
        
        return "home"; 
    }
}
