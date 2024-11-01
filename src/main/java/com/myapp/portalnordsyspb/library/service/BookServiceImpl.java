package com.myapp.portalnordsyspb.library.service;

import com.myapp.portalnordsyspb.exceptions.FileExistsException;
import com.myapp.portalnordsyspb.exceptions.SectionNotFoundException;
import com.myapp.portalnordsyspb.file.service.FileService;
import com.myapp.portalnordsyspb.library.dto.request.BookRequestDto;
import com.myapp.portalnordsyspb.library.dto.response.BookResponseDto;
import com.myapp.portalnordsyspb.library.entity.Book;
import com.myapp.portalnordsyspb.library.entity.Section;
import com.myapp.portalnordsyspb.library.repository.BookRepository;
import com.myapp.portalnordsyspb.library.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final FileService fileService;
    private final BookRepository bookRepository;
    private final SectionRepository sectionRepository;


    private final String baseUrl = "http://172.16.15.77:8080";
    private final String path ="/home/photos/";

    @Override
    public BookRequestDto addBook(BookRequestDto bookRequestDto, MultipartFile file) throws IOException {
        // upload the file
        if(Files.exists(Paths.get(path + File.separator + file.getOriginalFilename()))){
            throw new FileExistsException("File already exists!");
        }
        String uploadedFileName = fileService.uploadFile(path, file);
        // set the value of field 'fileName' as filename
        bookRequestDto.setFileName(uploadedFileName);
        // map dto to Document object
        Book book = new Book();
        book.setTitle(bookRequestDto.getTitle());
        book.setSection(sectionRepository.findById(bookRequestDto.getSectionId())
                .orElseThrow(()->new SectionNotFoundException("Section not found")));
        book.setFileName(bookRequestDto.getFileName());
        // save the document object -> savedDocument object
        Book savedBook = bookRepository.save(book);
        // generate the fileUrl
        String fileUrl = baseUrl + "/api/file/" + uploadedFileName;
        //  map Document object to dto and return it
        BookRequestDto response = BookRequestDto.builder()
                .title(savedBook.getTitle())
                .sectionId(savedBook.getId())
                .fileName(savedBook.getFileName())
                .fileUrl(fileUrl)
                .build();
        return response;
    }

    @Override
    public BookResponseDto getBookById(long documentId) {
        // check the data in DB and if exists, fetch the data of given ID
        Book book = bookRepository.findById(documentId)
                .orElseThrow(()-> new FileExistsException("Document not found."));
        // generate the fileUrl
        String fileUrl = baseUrl + "/api/file/" + book.getFileName();
        // map to DocumentResponseDto object and return it
        BookResponseDto responseDto = BookResponseDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .sectionId(book.getId())
                .fileName(book.getFileName())
                .fileUrl(fileUrl)
                .build();
        return responseDto;
    }


}
