package com.myapp.portalnordsyspb.news.repository;

import com.myapp.portalnordsyspb.level5S.entity.Result5S;
import com.myapp.portalnordsyspb.news.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    @Query(value = "select news.photo from news", nativeQuery = true)
    List<String> findAllPhoto();

    List<News> findTop5ByOrderByIdDesc();
}
