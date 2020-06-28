package com.epam.bookservice.service;

import com.epam.bookservice.entity.Book;
import com.epam.bookservice.repository.BookRepository;
import exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book updateBook(Integer id, Book book){
        Optional<Book> retrievedBook = bookRepository.findById(id);
        if(!retrievedBook.isPresent()){
            throw new BookNotFoundException(id);
        }
        Book bookToSave = retrievedBook.get();
        bookToSave.setAuthor(book.getAuthor());
        bookToSave.setTitle(book.getTitle());
        return bookRepository.save(bookToSave);
    }

    public Book createBook(Book book){
        return bookRepository.save(book);
    }

    public void deleteBookById(Integer id) {
        bookRepository.deleteById(id);
    }

}
