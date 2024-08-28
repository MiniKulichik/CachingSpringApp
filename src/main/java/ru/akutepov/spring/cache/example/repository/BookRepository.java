package ru.akutepov.spring.cache.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akutepov.spring.cache.example.model.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
