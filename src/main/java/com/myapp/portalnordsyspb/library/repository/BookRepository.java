package com.myapp.portalnordsyspb.library.repository;

import com.myapp.portalnordsyspb.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
