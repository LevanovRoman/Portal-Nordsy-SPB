package com.myapp.portalnordsyspb.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.portalnordsyspb.exceptions.EmptyFileException;
import com.myapp.portalnordsyspb.library.dto.request.BookRequestDto;
import com.myapp.portalnordsyspb.library.dto.response.BookResponseDto;
import com.myapp.portalnordsyspb.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/add-document")
    public ResponseEntity<BookRequestDto> createBook(@RequestPart MultipartFile file,
                                                            @RequestPart String fileData) throws IOException {
        if (file.isEmpty()) {
            throw new EmptyFileException("File is empty! Please send another file.");
        }
        BookRequestDto bookRequestDto = convertFileDataToBookRequestDto(fileData);
        return ResponseEntity.ok(bookService.addBook(bookRequestDto, file));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable long id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    private BookRequestDto convertFileDataToBookRequestDto(String fileData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String documentDto = new String(fileData.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        return objectMapper.readValue(documentDto, BookRequestDto.class);
    }
}
