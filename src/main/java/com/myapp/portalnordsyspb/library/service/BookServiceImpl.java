package com.myapp.portalnordsyspb.library.service;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.exceptions.BookNotFoundException;
import com.myapp.portalnordsyspb.exceptions.FileExistsException;
import com.myapp.portalnordsyspb.exceptions.SectionNotFoundException;
import com.myapp.portalnordsyspb.file.service.FileService;
import com.myapp.portalnordsyspb.library.dto.request.BookRequestDto;
import com.myapp.portalnordsyspb.library.dto.response.BookResponseDto;
import com.myapp.portalnordsyspb.library.entity.Book;
import com.myapp.portalnordsyspb.library.entity.Section;
import com.myapp.portalnordsyspb.library.repository.BookRepository;
import com.myapp.portalnordsyspb.library.repository.SectionRepository;
import com.myapp.portalnordsyspb.news.service.NewsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final FileService fileService;
    private final BookRepository bookRepository;
    private final SectionRepository sectionRepository;


    private final String baseUrl = "http://172.16.15.77:8080";
    private final NewsServiceImpl newsServiceImpl;
    //    private final String path ="/home/photos/";
    @Value("${project.filePathDocker.Photo}")
    private String path;

    @Override
    public BookRequestDto addBook(BookRequestDto bookRequestDto, MultipartFile file) throws IOException {
        // upload the file
        if(Files.exists(Paths.get(path + File.separator + file.getOriginalFilename()))){
            throw new FileExistsException("File already exists!");
        }
        String uploadedFileName = fileService.uploadFile(path, file);
        // set the value of field 'fileName' as file name
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
        BookRequestDto response = BookRequestDto
                .builder()
                .title(savedBook.getTitle())
                .sectionId(savedBook.getId())
                .fileName(savedBook.getFileName())
                .fileUrl(fileUrl)
                .build();
        return response;
    }

//    @Override
//    public BookResponseDto getBookById(long bookId) {
//        // check the data in DB and if exists, fetch the data of given ID
//        Book book = bookRepository.findById(bookId)
//                .orElseThrow(()-> new BookNotFoundException("Book not found."));
//        // generate the fileUrl
//        String fileUrl = baseUrl + "/api/file/" + book.getFileName();
//        // map to DocumentResponseDto object and return it
//        BookResponseDto responseDto = BookResponseDto.builder()
//                .id(book.getId())
//                .title(book.getTitle())
//                .sectionId(book.getId())
//                .fileName(book.getFileName())
//                .fileUrl(fileUrl)
//                .build();
//        return responseDto;
//    }

    @Override
    public List<BookResponseDto> getAllBookBySectionId(long sectionId) {
        List<Book> bookList = bookRepository.findAllBySectionId(sectionId);
        List<BookResponseDto> bookResponseDtoList = new ArrayList<>();
        // iterate through the list, generate fileUrl for each Book object and map to BookResponseDto object
        for (Book book : bookList){
            String fileUrl = baseUrl + "/api/file/" + book.getFileName();
            BookResponseDto bookResponseDto = new BookResponseDto(
                    book.getTitle(),
                    fileUrl
            );
            bookResponseDtoList.add(bookResponseDto);
        }
        return bookResponseDtoList;
    }

    @Override
    public String deleteBook(long bookId) throws IOException {
        // check the data in DB and if exists, fetch the data of given ID
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()-> new BookNotFoundException("Book not found."));
        // 2. delete file associated with this object
        Files.deleteIfExists(Paths.get(path + File.separator + book.getFileName()));
        // 3. delete the Section object
        bookRepository.delete(book);
        return "Book deleted with id = " + book.getId();
    }


}
