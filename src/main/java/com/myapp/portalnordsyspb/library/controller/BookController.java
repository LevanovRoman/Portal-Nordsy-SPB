package com.myapp.portalnordsyspb.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.exceptions.EmptyFileException;
import com.myapp.portalnordsyspb.library.dto.request.BookRequestDto;
import com.myapp.portalnordsyspb.library.dto.response.BookResponseDto;
import com.myapp.portalnordsyspb.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@Tag(name = "Library", description = "Description for library")
@RequestMapping("/library/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @Operation(summary = "Создание новой книги",
            description = "Creating new book. The response is object BookRequestDto.")
    @PostMapping("/add")
    public ResponseEntity<BookRequestDto> createBook(@RequestPart MultipartFile file,
                                                            @RequestPart String bookData) throws IOException {
        if (file.isEmpty()) {
            throw new EmptyFileException("File is empty! Please send another file.");
        }
        BookRequestDto bookRequestDto = convertFileDataToBookRequestDto(bookData);
        return ResponseEntity.ok(bookService.addBook(bookRequestDto, file));
    }

    @Operation(summary = "Получение всех книг из заданного раздела.")
    @GetMapping("/all-in-section/{sectionId}")
    public ResponseEntity<List<BookResponseDto>> getAllBookBySectionId(@PathVariable long sectionId){
        return ResponseEntity.ok(bookService.getAllBookBySectionId(sectionId));
    }

    @Operation(summary = "Удаление книги по id.")
    @DeleteMapping("delete/{bookId}")
    public ResponseEntity<MessageDto> deleteSection(@PathVariable("bookId") long bookId) throws IOException {
        return ResponseEntity.ok(new MessageDto(bookService.deleteBook(bookId)));
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<BookResponseDto> getBookById(@PathVariable long id){
//        return ResponseEntity.ok(bookService.getBookById(id));
//    }

    private BookRequestDto convertFileDataToBookRequestDto(String fileData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String documentDto = new String(fileData.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        return objectMapper.readValue(documentDto, BookRequestDto.class);
    }
}
