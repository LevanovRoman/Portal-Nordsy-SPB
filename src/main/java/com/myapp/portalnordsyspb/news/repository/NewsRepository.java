package com.myapp.portalnordsyspb.news.repository;

import com.myapp.portalnordsyspb.news.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
