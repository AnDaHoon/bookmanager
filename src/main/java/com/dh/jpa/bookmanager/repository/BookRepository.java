package com.dh.jpa.bookmanager.repository;

import com.dh.jpa.bookmanager.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;




public interface BookRepository extends JpaRepository<Book, Long> {
}
