package com.myapp.portalnordsyspb.xwiki.controller;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.xwiki.dto.requestDto.PostRequestDto;
import com.myapp.portalnordsyspb.xwiki.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/xwiki/post")
@Tag(name = "Post for WIKI", description = "Description for post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("create")
    public ResponseEntity<PostRequestDto> createPost(@RequestBody PostRequestDto postRequestDto){
        return ResponseEntity.ok(postService.createPost(postRequestDto));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<PostRequestDto> updatePost(@RequestBody PostRequestDto postRequestDto,
                                                     @PathVariable("id") long postId){
        return ResponseEntity.ok(postService.updatePost(postRequestDto, postId));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<MessageDto> deletePost(@PathVariable("id") long id){
        postService.deletePost(id);
        return ResponseEntity.ok(new MessageDto("Post deleted"));
    }
}
