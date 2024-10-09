package com.myapp.portalnordsyspb.news.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.portalnordsyspb.news.dto.request.NewsRequestDto;
import com.myapp.portalnordsyspb.news.dto.response.NewsResponseDto;
import com.myapp.portalnordsyspb.news.entity.Category;
import com.myapp.portalnordsyspb.news.repository.CategoryRepository;
import com.myapp.portalnordsyspb.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/news/")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    private final CategoryRepository categoryRepository;

    @PostMapping("/create")
    public ResponseEntity<NewsRequestDto> createNews(@RequestPart MultipartFile file,
                                                     @RequestPart String newsRequestDto)
            throws IOException {
        NewsRequestDto dto = convertToNewsRequestDto(newsRequestDto);
        System.out.println("NEWSCONTROLLER");
        return new ResponseEntity<>(newsService.addNews(dto, file), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponseDto> getOneNews(@PathVariable long id){
        return ResponseEntity.ok(newsService.getNewsById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<NewsResponseDto>> getAllNews(){
        return ResponseEntity.ok(newsService.getAllNews());
    }

    @PutMapping("/update/{newsId}")
    public ResponseEntity<NewsRequestDto> updateNews(@PathVariable long newsId,
                                                      @RequestPart MultipartFile file,
                                                      @RequestPart String newsDtoObj
                                                      ) throws IOException {
        if (file.isEmpty()) file = null;
        NewsRequestDto newsRequestDto = convertToNewsRequestDto(newsDtoObj);
        return ResponseEntity.ok(newsService.updateNews(newsId, newsRequestDto, file));
    }

    @DeleteMapping("/delete/{newsId}")
    public ResponseEntity<String> deleteNews(@PathVariable long newsId) throws IOException {
        return ResponseEntity.ok(newsService.deleteNews(newsId));
    }

    private NewsRequestDto convertToNewsRequestDto(String newsDtoObj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(newsDtoObj, NewsRequestDto.class);

    }

    @GetMapping("/all-categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Optional<Category>> getCategory(@PathVariable long id){
        return ResponseEntity.ok(categoryRepository.findById(id));
    }
}
