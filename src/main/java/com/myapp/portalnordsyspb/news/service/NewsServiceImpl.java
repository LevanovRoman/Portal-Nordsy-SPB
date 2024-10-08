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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
//        List<Category> categoryList = newsRequestDto.getCategoryListString()
//                        .stream().map(this::convertStringToCategory)
//                        .toList();
        List<Category> categoryList = newsRequestDto.getCategoryIdList()
                .stream().map(this::convertIdToCategory)
                .toList();
        news.setCategoryList(categoryList);
        news.setPhoto(newsRequestDto.getPhoto());

        // save the news object -> saved News object
        News savedNews = newsRepository.save(news);

        // generate the photoUrl
        String photoUrl = baseUrl + "/api/photo/" + uploadedPhotoName;

       //  map News object to dto and return it
//        List<String> stringListCategory = savedNews.getCategoryList()
//                .stream().map(this::convertCategoryToString)
//                .toList();
        List<Long> stringListCategoryId = savedNews.getCategoryList()
                .stream().map(this::convertCategoryToId)
                .toList();
        NewsRequestDto response = new NewsRequestDto(
                savedNews.getTitle(),
                savedNews.getContent(),
                stringListCategoryId,
                savedNews.getPhoto(),
                photoUrl
        );
        return response;
    }

    @Override
    public NewsResponseDto getNewsById(Long newsId) {
        // check the data in DB and if exists, fetch the data of given ID
        News news = newsRepository.findById(newsId)
                .orElseThrow(()-> new RuntimeException("Photo not found!"));
        // generate photoUrl
        String photoUrl = baseUrl + "/api/photo/" + news.getPhoto();

        // map to NewsResponseDto object and return it
        List<String> stringListCategoryName = news.getCategoryList()
                .stream().map(this::convertCategoryToString)
                .toList();
        NewsResponseDto response = new NewsResponseDto(
                news.getTitle(),
                news.getContent(),
                stringListCategoryName,
                news.getPhoto(),
                photoUrl
        );
        return response;
    }

    @Override
    public List<NewsResponseDto> getAllNews() {
        // fetch all data from DB
        List<News> newsList = newsRepository.findAll();

        List<NewsResponseDto> newsResponseDtoList = new ArrayList<>();

        /* iterate through the list, generate photoUrl for each news object,
         and map to NewsResponseDto object */
        for (News news : newsList){
            String photoUrl = baseUrl + "/api/photo/" + news.getPhoto();
            List<String> stringListCategoryName = news.getCategoryList()
                    .stream().map(this::convertCategoryToString)
                    .toList();
            NewsResponseDto newsResponseDto = new NewsResponseDto(
                    news.getTitle(),
                    news.getContent(),
                    stringListCategoryName,
                    news.getPhoto(),
                    photoUrl
            );
            newsResponseDtoList.add(newsResponseDto);
        }
        return newsResponseDtoList;
    }

//    private Category convertStringToCategory(String categoryString){
//        System.out.println("Category " + categoryString + categoryRepository.findByName(categoryString));
//        return categoryRepository.findByName(categoryString);
//    }
    private Category convertIdToCategory(Long categoryId){
        return categoryRepository.findById(categoryId).get();
    }

    private String convertCategoryToString(Category category){
        return category.getName();
    }
    private Long convertCategoryToId(Category category){
        return category.getId();
    }
}
