package com.myapp.portalnordsyspb.xwiki.service;

import com.myapp.portalnordsyspb.exceptions.ChapterNotFoundException;
import com.myapp.portalnordsyspb.exceptions.PostNotFoundException;
import com.myapp.portalnordsyspb.xwiki.dto.requestDto.PostRequestDto;
import com.myapp.portalnordsyspb.xwiki.dto.responseDto.PostResponseDto;
import com.myapp.portalnordsyspb.xwiki.entity.Post;
import com.myapp.portalnordsyspb.xwiki.repository.ChapterRepository;
import com.myapp.portalnordsyspb.xwiki.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final ChapterRepository chapterRepository;


    @Override
    @Cacheable("posts")
    public List<PostResponseDto> getListPostResponseDtoByChapterId(Long chapter_id) {
        return postRepository.findAllByChapterId(chapter_id)
                .stream()
                .map(this::convertAllPostsToPostsResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(()->new PostNotFoundException("Пост с id = " + id + " не найден"));
    }

    @Override
    public PostRequestDto savePost(Post post, PostRequestDto postRequestDto) {
        post.setTitle(postRequestDto.title());
        post.setDescription(postRequestDto.description());
        post.setChapter(chapterRepository.findById(postRequestDto.chapterId())
                .orElseThrow(()->new ChapterNotFoundException("Глава с id = " + postRequestDto.chapterId() + " не найдена")));

        Post newPost = postRepository.save(post);

        return mapPostToPostRequestDto(newPost);
    }

    @Override
    @CacheEvict(value = "posts")
    public PostRequestDto createPost(PostRequestDto postRequestDto) {
        Post post = new Post();
        return savePost(post, postRequestDto);
    }

    @Override
    @CacheEvict(value = "posts")
    public PostRequestDto updatePost(PostRequestDto postRequestDto, Long id) {
        Post post = getPostById(id);
        return savePost(post, postRequestDto);
    }

    @Override
    @CacheEvict(value = "posts")
    public void deletePost(long id) {
        Post post = getPostById(id);
        postRepository.delete(post);
    }

    private PostRequestDto mapPostToPostRequestDto(Post post){
        return new PostRequestDto(
                post.getTitle(),
                post.getDescription(),
                post.getChapter().getId()
        );
    }

    private PostResponseDto convertAllPostsToPostsResponseDto(Post post) {
        return new PostResponseDto(
                post.getId(),
                post.getTitle(),
                post.getDescription()
        );
    }
}
