package com.myapp.portalnordsyspb.news.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.exceptions.EmptyFileException;
import com.myapp.portalnordsyspb.news.dto.request.NewsRequestDto;
import com.myapp.portalnordsyspb.news.dto.response.NewsResponseDto;
import com.myapp.portalnordsyspb.news.dto.response.PhotoNamesResponseDto;
import com.myapp.portalnordsyspb.news.service.NewsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("api/news/")
@Tag(name = "News", description = "Description for news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @PostMapping("add-news")
    public ResponseEntity<NewsRequestDto> createNews(@RequestPart MultipartFile file,
                                                     @RequestPart String newsRequestDto)
            throws IOException {
        if (file.isEmpty()) {
            throw new EmptyFileException("File is empty! Please send another file.");
        }
        NewsRequestDto dto = convertToNewsRequestDto(newsRequestDto);
        return new ResponseEntity<>(newsService.addNews(dto, file), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponseDto> getOneNews(@PathVariable long id) {
        return ResponseEntity.ok(newsService.getNewsById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<NewsResponseDto>> getAllNews() {
        return ResponseEntity.ok(newsService.getAllNews());
    }

    @PutMapping("update/{newsId}")
    public ResponseEntity<NewsRequestDto> updateNews(@PathVariable("newsId") long newsId,
                                                     @RequestPart MultipartFile file,
                                                     @RequestPart String newsRequestDtoObj) throws IOException {
        if (file.isEmpty()) file = null;
        NewsRequestDto newsRequestDto = convertToNewsRequestDto(newsRequestDtoObj);
        return ResponseEntity.ok(newsService.updateNews(newsId, newsRequestDto, file));
    }

    @DeleteMapping("delete/{newsId}")
    public ResponseEntity<MessageDto> deleteNews(@PathVariable("newsId") long newsId) throws IOException {
        return ResponseEntity.ok(new MessageDto(newsService.deleteNews(newsId)));
    }

    @GetMapping("/photo-names")
    public ResponseEntity<PhotoNamesResponseDto> getPhotoNames(){
        return ResponseEntity.ok(newsService.getAllPhotoNames());
    }

    private NewsRequestDto convertToNewsRequestDto(String newsDtoObj) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(newsDtoObj);
        String newsDtoObj2 = new String(newsDtoObj.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        System.out.println(newsDtoObj2);
        return objectMapper.readValue(newsDtoObj2, NewsRequestDto.class);

    }
}