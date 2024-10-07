package com.myapp.portalnordsyspb.news.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.portalnordsyspb.news.dto.request.NewsRequestDto;
import com.myapp.portalnordsyspb.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/news/")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @PostMapping("add-news")
    public ResponseEntity<NewsRequestDto> createNews(@RequestPart MultipartFile file,
                                                     @RequestPart String newsRequestDto)
            throws IOException {
        NewsRequestDto dto = convertToNewsRequestDto(newsRequestDto);
        return new ResponseEntity<>(newsService.addNews(dto, file), HttpStatus.CREATED);
    }

    private NewsRequestDto convertToNewsRequestDto(String newsDtoObj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(newsDtoObj, NewsRequestDto.class);

    }
}
