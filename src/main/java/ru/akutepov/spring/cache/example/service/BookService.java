package ru.akutepov.spring.cache.example.service;

import ru.akutepov.spring.cache.example.model.Book;

import java.util.List;
import java.util.Optional;


public interface BookService {

    Optional<Book> getBookById(long id);

    Book saveBook(Book book);

    List<Book> getAllBooks();

    void deleteBook(long id);

    void clearCache();
}
