package com.myapp.portalnordsyspb.xwiki.service;

import com.myapp.portalnordsyspb.exceptions.ChapterNotFoundException;
import com.myapp.portalnordsyspb.exceptions.PostNotFoundException;
import com.myapp.portalnordsyspb.xwiki.dto.requestDto.PostRequestDto;
import com.myapp.portalnordsyspb.xwiki.dto.responseDto.PostResponseDto;
import com.myapp.portalnordsyspb.xwiki.entity.Post;
import com.myapp.portalnordsyspb.xwiki.repository.ChapterRepository;
import com.myapp.portalnordsyspb.xwiki.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final ChapterRepository chapterRepository;


    @Override
    public List<PostResponseDto> getListPostResponseDtoByChapterId(Long chapter_id) {
        return postRepository.findAllByChapterId(chapter_id)
                .stream()
                .map(this::convertAllPostsToPostsResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public Post getPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(()->new PostNotFoundException("Пост с id = " + postId + " не найден"));
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
    public PostRequestDto createPost(PostRequestDto postRequestDto) {
        Post post = new Post();
        return savePost(post, postRequestDto);
    }

    @Override
    public PostRequestDto updatePost(PostRequestDto postRequestDto, Long postId) {
        Post post = getPostById(postId);
        return savePost(post, postRequestDto);
    }

    @Override
    public void deletePost(long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()->new PostNotFoundException("Пост с id = " + id + " не найден"));
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
