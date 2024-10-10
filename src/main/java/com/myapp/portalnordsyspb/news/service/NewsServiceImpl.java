package com.myapp.portalnordsyspb.news.service;

import com.myapp.portalnordsyspb.exceptions.FileExistsException;
import com.myapp.portalnordsyspb.exceptions.PhotoNotFoundException;
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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        if(Files.exists(Paths.get(path + File.separator + file.getOriginalFilename()))){
            throw new FileExistsException("File already exists!");
        }
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
    public NewsRequestDto updateNews(Long newsId, NewsRequestDto newsRequestDto, MultipartFile file) throws IOException {
        // 1.check if news object exists with given newsId
        News nw = newsRepository.findById(newsId)
                .orElseThrow(()-> new PhotoNotFoundException("Photo not found!"));

        // 2.if file is null, do nothing
        // if file is not null, then delete existing file associated with record
        // and upload new file
        String fileName = nw.getPhoto();
        if (file != null){
            Files.deleteIfExists(Paths.get(path + File.separator + fileName));
            fileName = photoService.uploadPhoto(path, file);
        }

        // 3.set NewsRequestDto`s photo value
        newsRequestDto.setPhoto(fileName);

        // 4.map it to News object
        News news = new News();
        news.setId(nw.getId());
        news.setTitle(newsRequestDto.getTitle());
        news.setContent(newsRequestDto.getContent());
        List<Category> categoryList = newsRequestDto.getCategoryIdList()
                .stream().map(this::convertIdToCategory)
                .toList();
        news.setCategoryList(categoryList);
        news.setPhoto(newsRequestDto.getPhoto());

        // 5.save the news object -> return saved news object
        News updatedNews = newsRepository.save(news);

        // 6.generate photoUrl for it
        String photoUrl = baseUrl + "/api/photo/" + fileName;

        // 7. map to NewsRequestDto and return it
        List<Long> stringListCategoryId = updatedNews.getCategoryList()
                .stream().map(this::convertCategoryToId)
                .toList();
        NewsRequestDto response = new NewsRequestDto(
                updatedNews.getTitle(),
                updatedNews.getContent(),
                stringListCategoryId,
                updatedNews.getPhoto(),
                photoUrl
        );
        return response;
    }

    @Override
    public NewsResponseDto getNewsById(Long newsId) {
        // check the data in DB and if exists, fetch the data of given ID
        News news = newsRepository.findById(newsId)
                .orElseThrow(()-> new PhotoNotFoundException("Photo not found!"));
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

    @Override
    public String deleteNews(Long newsId) throws IOException {
        // 1. check if news object exists in DB
        News nw = newsRepository.findById(newsId)
                .orElseThrow(()-> new PhotoNotFoundException("Photo not found!"));
        Long id = nw.getId();

        // 2. delete file associated with this object
        Files.deleteIfExists(Paths.get(path + File.separator + nw.getPhoto()));

        // 3. delete the news object
        newsRepository.delete(nw);

        return "News deleted with id = " + id;

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
