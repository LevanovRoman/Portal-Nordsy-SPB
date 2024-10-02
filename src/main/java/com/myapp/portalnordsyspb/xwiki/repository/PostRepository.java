package com.myapp.portalnordsyspb.xwiki.repository;

import com.myapp.portalnordsyspb.xwiki.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByChapterId(Long chapterId);
}
