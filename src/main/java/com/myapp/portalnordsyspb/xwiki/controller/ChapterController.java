package com.myapp.portalnordsyspb.xwiki.controller;

import com.myapp.portalnordsyspb.xwiki.dto.requestDto.ChapterRequestDto;
import com.myapp.portalnordsyspb.xwiki.dto.responseDto.ChapterResponseDto;
import com.myapp.portalnordsyspb.xwiki.service.ChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/xwiki/chapter")
@RequiredArgsConstructor
public class ChapterController {

    private final ChapterService chapterService;

    @GetMapping("all")
    public ResponseEntity<List<ChapterResponseDto>> getAllChaptersWithPosts(){
        return ResponseEntity.ok(chapterService.getAllChaptersWithPosts());
    }

    @PostMapping("create")
    public ResponseEntity<ChapterRequestDto> createChapter(@RequestBody ChapterRequestDto chapterResponseDto) {
        return new ResponseEntity<>(chapterService.createChapter(chapterResponseDto), HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ChapterRequestDto> updateChapter(@RequestBody ChapterRequestDto chapterResponseDto,
                                                           @PathVariable("id") long chapterId){
        ChapterRequestDto response = chapterService.updateChapter(chapterResponseDto, chapterId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
