package com.myapp.portalnordsyspb.library.service;

import com.myapp.portalnordsyspb.library.dto.request.BookRequestDto;
import com.myapp.portalnordsyspb.library.dto.response.BookResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BookService {
    BookRequestDto addBook(BookRequestDto bookRequestDto, MultipartFile file) throws IOException;
    BookResponseDto getBookById(long bookId);
}
