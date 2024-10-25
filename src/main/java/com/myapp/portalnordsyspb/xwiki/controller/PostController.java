package com.myapp.portalnordsyspb.xwiki.controller;

import com.myapp.portalnordsyspb.xwiki.dto.responseDto.ResponseDto;
import com.myapp.portalnordsyspb.xwiki.dto.requestDto.PostRequestDto;
import com.myapp.portalnordsyspb.xwiki.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/xwiki/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("create")
    public ResponseEntity<PostRequestDto> createPost(@RequestBody PostRequestDto postRequestDto){
        return new ResponseEntity<>(postService.createPost(postRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<PostRequestDto> updatePost(@RequestBody PostRequestDto postRequestDto,
                                                     @PathVariable("id") long postId){
        return new ResponseEntity<>(postService.updatePost(postRequestDto, postId), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseDto> deletePost(@PathVariable("id") long id){
        postService.deletePost(id);
        return new ResponseEntity<>(new ResponseDto("Post deleted"), HttpStatus.OK);
    }
}
