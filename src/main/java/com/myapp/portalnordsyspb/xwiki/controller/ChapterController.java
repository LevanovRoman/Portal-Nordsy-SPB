package com.myapp.portalnordsyspb.xwiki.controller;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.xwiki.dto.requestDto.ChapterRequestDto;
import com.myapp.portalnordsyspb.xwiki.dto.responseDto.ChapterResponseDto;
import com.myapp.portalnordsyspb.xwiki.service.ChapterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
        return ResponseEntity.ok(chapterService.createChapter(chapterRequestDto));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ChapterRequestDto> updateChapter(@RequestBody ChapterRequestDto chapterResponseDto,
                                                           @PathVariable("id") long chapterId){
        return ResponseEntity.ok(chapterService.updateChapter(chapterResponseDto, chapterId));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<MessageDto> deleteChapter(@PathVariable("id") long id){
        chapterService.deleteChapter(id);
        return ResponseEntity.ok(new MessageDto("Chapter deleted"));
    }
}
