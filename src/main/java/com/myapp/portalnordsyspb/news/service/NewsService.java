package com.myapp.portalnordsyspb.news.service;

import com.myapp.portalnordsyspb.news.dto.request.NewsRequestDto;
import com.myapp.portalnordsyspb.news.dto.response.NewsResponseDto;
import com.myapp.portalnordsyspb.news.dto.response.PhotoNamesResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface NewsService {

    NewsRequestDto addNews(NewsRequestDto newsRequestDto, MultipartFile file) throws IOException;

    NewsRequestDto updateNews(Long newsId, NewsRequestDto newsRequestDto, MultipartFile file) throws IOException;

    NewsResponseDto getNewsById(Long newsId);

    List<NewsResponseDto> getAllNews();

    String deleteNews(Long newsId) throws IOException;

    PhotoNamesResponseDto getPhotoNames();
}
