package com.epam.bookservice.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "title is required")
    @NotEmpty(message = "title cannot be empty string")
    @Column(name = "title", nullable = false)
    private String title;
    @NotNull(message = "author is required")
    @NotEmpty(message = "author name cannot be empty string")
    @Column(name = "author", nullable = false)
    private String author;
}
