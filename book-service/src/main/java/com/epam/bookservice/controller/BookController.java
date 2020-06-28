package com.epam.bookservice.controller;

import com.epam.bookservice.entity.Book;
import com.epam.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@Valid @RequestBody Book book){
        Book createdBook = bookService.createBook(book);
        URI location = ServletUriComponentsBuilder
                      .fromCurrentRequest()
                      .path("/{id}")
                      .buildAndExpand(createdBook.getId())
                      .toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @Valid @RequestBody Book book){
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }
    @DeleteMapping("{id}")
    public ResponseEntity deleteBook(@PathVariable Integer id){
        bookService.deleteBookById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
