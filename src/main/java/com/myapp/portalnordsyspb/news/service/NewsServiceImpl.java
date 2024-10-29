package com.myapp.portalnordsyspb.news.service;

import com.myapp.portalnordsyspb.exceptions.FileExistsException;
import com.myapp.portalnordsyspb.exceptions.PhotoNotFoundException;
import com.myapp.portalnordsyspb.news.dto.request.NewsRequestDto;
import com.myapp.portalnordsyspb.news.dto.response.NewsResponseDto;
import com.myapp.portalnordsyspb.news.dto.response.PhotoNamesResponseDto;
import com.myapp.portalnordsyspb.news.entity.News;
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
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService{

    private final NewsRepository newsRepository;

    private final PhotoService photoService;

//    @Value("${project.photo}")
//    private String path;

//    @Value("${base.url}")
    private final String baseUrl = "http://172.16.15.77:8080";
    private final String path ="/home/photos/";
//    private final String baseUrl = "http://172.16.15.77:8080";


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
        news.setHashtagList(newsRequestDto.getHashtags());
        news.setPhoto(newsRequestDto.getPhoto());

        // save the news object -> saved News object
        News savedNews = newsRepository.save(news);

        // generate the photoUrl
        String photoUrl = baseUrl + "/api/photo/" + uploadedPhotoName;
//        String photoUrl = "http://172.16.15.77:8080" + "/home/astra/photo/" + uploadedPhotoName;

       //  map News object to dto and return it
        NewsRequestDto response = new NewsRequestDto(
                savedNews.getTitle(),
                savedNews.getContent(),
                savedNews.getHashtagList(),
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
        news.setHashtagList(newsRequestDto.getHashtags());
        news.setPhoto(newsRequestDto.getPhoto());

        // 5.save the news object -> return saved news object
        News updatedNews = newsRepository.save(news);

        // 6.generate photoUrl for it
        String photoUrl = baseUrl + "/api/photo/" + fileName;

        // 7. map to NewsRequestDto and return it
        NewsRequestDto response = new NewsRequestDto(
                updatedNews.getTitle(),
                updatedNews.getContent(),
                updatedNews.getHashtagList(),
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
        NewsResponseDto response = new NewsResponseDto(
                news.getId(),
                news.getTitle(),
                news.getContent(),
                news.getHashtagList(),
                news.getPhoto(),
                photoUrl
        );
        return response;
    }

    @Override
    public List<NewsResponseDto> getAllNews() {
        // fetch all data from DB
        List<News> newsList = newsRepository.findAll();

//        List<NewsResponseDto> newsResponseDtoList = new ArrayList<>();
//
//        /* iterate through the list, generate photoUrl for each news object,
//         and map to NewsResponseDto object */
//        for (News news : newsList){
//            String photoUrl = baseUrl + "/api/photo/" + news.getPhoto();
//            NewsResponseDto newsResponseDto = new NewsResponseDto(
//                    news.getId(),
//                    news.getTitle(),
//                    news.getContent(),
//                    news.getHashtagList(),
//                    news.getPhoto(),
//                    photoUrl
//            );
//            newsResponseDtoList.add(newsResponseDto);
//        }
//        return newsResponseDtoList;
        return fetchNews(newsList);
    }

    @Override
    public List<NewsResponseDto> getLastFiveNews() {
        // fetch last five news from DB
        List<News> newsList = newsRepository.findTop5ByOrderByIdDesc();
        return fetchNews(newsList);
    }

    private List<NewsResponseDto> fetchNews(List<News> newsList){
        List<NewsResponseDto> newsResponseDtoList = new ArrayList<>();

        /* iterate through the list, generate photoUrl for each news object,
         and map to NewsResponseDto object */
        for (News news : newsList){
            String photoUrl = baseUrl + "/api/photo/" + news.getPhoto();
            NewsResponseDto newsResponseDto = new NewsResponseDto(
                    news.getId(),
                    news.getTitle(),
                    news.getContent(),
                    news.getHashtagList(),
                    news.getPhoto(),
                    photoUrl
            );
            newsResponseDtoList.add(newsResponseDto);
        }
        return newsResponseDtoList
                .stream()
                .sorted(Comparator.comparing(NewsResponseDto::getId)).toList().reversed();
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

    @Override
    public PhotoNamesResponseDto getAllPhotoNames(){
        return new PhotoNamesResponseDto(newsRepository.findAllPhoto());
    }

}
