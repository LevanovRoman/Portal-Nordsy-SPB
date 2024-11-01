package com.myapp.portalnordsyspb.library.service;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.library.dto.request.BookRequestDto;
import com.myapp.portalnordsyspb.library.dto.response.BookResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookService {
    BookRequestDto addBook(BookRequestDto bookRequestDto, MultipartFile file) throws IOException;
//    BookResponseDto getBookById(long bookId);
    List<BookResponseDto> getAllBookBySectionId(long sectionId);
    String deleteBook(long bookId) throws IOException;
}
