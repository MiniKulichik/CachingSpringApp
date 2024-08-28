package ru.akutepov.spring.cache.example.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.akutepov.spring.cache.example.model.Book;
import ru.akutepov.spring.cache.example.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Cacheable(value = "books", key = "#id")
    public Optional<Book> getBookById(long id) {
        simulateWaitTime();
        return bookRepository.findById(id);
    }

    @Override
    public Book saveBook(Book book) {
        Optional<Book> existingBook = bookRepository.findById(book.getId());
        if (existingBook.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "book with this ID already exists.");
        }
        return bookRepository.save(book);
    }

    @Override
    @Cacheable(value = "books")
    public List<Book> getAllBooks() {
        simulateWaitTime();
        return bookRepository.findAll();
    }

    @Override
    @CacheEvict(value = "books", key = "#id")
    public void deleteBook(long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            bookRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "book not found.");
        }
    }

    @Override
    @CacheEvict(value = "books", allEntries = true)
    public void clearCache() {
        // clears all cache entries
    }

    private void simulateWaitTime() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
