package com.myapp.portalnordsyspb.xwiki.controller;

import com.myapp.portalnordsyspb.xwiki.dto.requestDto.ChapterRequestDto;
import com.myapp.portalnordsyspb.xwiki.dto.responseDto.ChapterResponseDto;
import com.myapp.portalnordsyspb.xwiki.dto.responseDto.ResponseDto;
import com.myapp.portalnordsyspb.xwiki.service.ChapterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/xwiki/chapter")
@Tag(name = "Chapter for WIKI", description = "Description for chapter")
@RequiredArgsConstructor
public class ChapterController {

    private final ChapterService chapterService;

    @GetMapping("all")
    public ResponseEntity<List<ChapterResponseDto>> getAllChaptersWithPosts(){
        return ResponseEntity.ok(chapterService.getAllChaptersWithPosts());
    }

    @PostMapping("create")
    public ResponseEntity<ChapterRequestDto> createChapter(@RequestBody ChapterRequestDto chapterRequestDto) {
        return new ResponseEntity<>(chapterService.createChapter(chapterRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ChapterRequestDto> updateChapter(@RequestBody ChapterRequestDto chapterResponseDto,
                                                           @PathVariable("id") long chapterId){
        ChapterRequestDto response = chapterService.updateChapter(chapterResponseDto, chapterId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseDto> deleteChapter(@PathVariable("id") long id){
        chapterService.deleteChapter(id);
        return new ResponseEntity<>(new ResponseDto("Chapter deleted"), HttpStatus.OK);
    }
}
