package com.myapp.portalnordsyspb.xwiki.service;

import com.myapp.portalnordsyspb.xwiki.dto.requestDto.PostRequestDto;
import com.myapp.portalnordsyspb.xwiki.dto.responseDto.PostResponseDto;
import com.myapp.portalnordsyspb.xwiki.entity.Post;

import java.util.List;

public interface PostService {

    List<PostResponseDto> getListPostResponseDtoByChapterId(Long chapter_id);
    Post getPostById(Long postId);
    PostRequestDto createPost(PostRequestDto postRequestDto);
    PostRequestDto savePost(Post post, PostRequestDto postRequestDto);
    PostRequestDto updatePost(PostRequestDto postRequestDto, Long postId);
    void deletePost(long id);
}
