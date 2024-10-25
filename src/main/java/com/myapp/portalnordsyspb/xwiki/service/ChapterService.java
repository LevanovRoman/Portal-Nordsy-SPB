package com.myapp.portalnordsyspb.xwiki.service;

import com.myapp.portalnordsyspb.xwiki.dto.requestDto.ChapterRequestDto;
import com.myapp.portalnordsyspb.xwiki.dto.responseDto.ChapterResponseDto;
import com.myapp.portalnordsyspb.xwiki.entity.Chapter;

import java.util.List;

public interface ChapterService {

    List<ChapterResponseDto> getAllChaptersWithPosts();
    Chapter getChapterById(Long chapterId);
    ChapterRequestDto createChapter(ChapterRequestDto chapterResponseDto);
    ChapterRequestDto updateChapter(ChapterRequestDto chapterResponseDto, long chapterId);
    void deleteChapter(long id);
}
