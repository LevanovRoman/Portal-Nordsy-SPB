package com.myapp.portalnordsyspb.news.service;

import com.myapp.portalnordsyspb.news.dto.request.NewsRequestDto;
import com.myapp.portalnordsyspb.news.dto.response.NewsResponseDto;
import com.myapp.portalnordsyspb.news.entity.Category;
import com.myapp.portalnordsyspb.news.entity.News;
import com.myapp.portalnordsyspb.news.repository.CategoryRepository;
import com.myapp.portalnordsyspb.news.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService{

    private final NewsRepository newsRepository;

    private final PhotoService photoService;

    private final CategoryRepository categoryRepository;

    @Value("${project.photo}")
    private String path;

    @Value("${base.url}")
    private String baseUrl;

    @Override
    public NewsRequestDto addNews(NewsRequestDto newsRequestDto, MultipartFile file) throws IOException {
        // upload the file
        String uploadedPhotoName = photoService.uploadPhoto(path, file);

        // set the value of field 'photo' as filename
        newsRequestDto.setPhoto(uploadedPhotoName);

        // map dto to News object
        News news = new News();
        news.setTitle(newsRequestDto.getTitle());
        news.setContent(newsRequestDto.getContent());
        List<Category> categoryList = newsRequestDto.getCategoryListString()
                        .stream().map(this::convertStringToCategory)
                        .toList();
        news.setCategoryList(categoryList);
        news.setPhoto(newsRequestDto.getPhoto());

        // save the news object -> saved News object
        News savedNews = newsRepository.save(news);

        // generate the photoUrl
        String photoUrl = baseUrl + "/api/photo/" + uploadedPhotoName;

       //  map News object to dto and return it
        List<String> stringListCategory = savedNews.getCategoryList()
                .stream().map(this::convertCategoryToString)
                .toList();
        NewsRequestDto response = new NewsRequestDto(
                savedNews.getTitle(),
                savedNews.getContent(),
                stringListCategory,
                savedNews.getPhoto(),
                photoUrl
        );
        return response;
    }

    @Override
    public NewsResponseDto getNewsById(Long newsId) {
        return null;
    }

    @Override
    public List<NewsResponseDto> getAllNews() {
        return List.of();
    }

    private Category convertStringToCategory(String categoryString){
        return categoryRepository.findByName(categoryString);
    }

    private String convertCategoryToString(Category category){
        return category.getName();
    }
}
