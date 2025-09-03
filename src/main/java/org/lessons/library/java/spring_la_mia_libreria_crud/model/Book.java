package org.lessons.library.java.spring_la_mia_libreria_crud.model;

import java.math.BigDecimal;
import java.time.Year;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il titolo del libro non può essere vuoto")
    private String title;

    @NotNull(message = "Il titolo originale del libro non può essere nullo")
    private String originalTitle;

    @NotNull(message = "Il prezzo del libro non può essere nullo")
    @DecimalMin(value = "0.01", message = "Il prezzo deve essere maggiore di 0")
    private BigDecimal price;

    @NotBlank(message = "L'URL dell'immagine non può essere vuota")
    @Pattern(regexp = "^(|\\w|\\s|-])*\\.(?:jpg|jpeg|png)$",
             message = "L'URL deve essere un link valido a un'immagine (jpg, jpeg, png)")
    private String image;

    @NotBlank(message = "L'autore del libro non può essere vuoto")
    private String author;

    @NotNull(message = "Il codice ISBN non può essere nullo")
    @Size(min = 10, max = 13, message = "Il codice ISBN deve essere lungo da 10 a 13 caratteri")
    private String isbn;

    @NotNull
    @PastOrPresent(message = "L'anno di pubblicazione deve essere nel passato o presente")
    private Year publicationYear;

    @NotBlank(message = "La descrizione del libro non può essere vuota")
    private String description;

    @NotBlank(message = "L'editore del libro non può essere vuoto")
    private String editor;

    @Min(value = 1, message = "Il numero di pagine deve essere almeno 1")
    private int pages;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public Year getPublicationYear() {
        return publicationYear;
    }
    public void setPublicationYear(Year publicationYear) {
        this.publicationYear = publicationYear;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getOriginalTitle() {
        return originalTitle;
    }
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }
        public String getEditor() {
        return editor;
    }
    public void setEditor(String editor) {
        this.editor = editor;
    }
    public int getPages() {
        return pages;
    }
    public void setPages(int pages) {
        this.pages = pages;
    }
}
