package com.myapp.portalnordsyspb.service;

import com.myapp.portalnordsyspb.xwiki.dto.requestDto.ChapterRequestDto;
import com.myapp.portalnordsyspb.xwiki.dto.responseDto.ChapterResponseDto;
import com.myapp.portalnordsyspb.xwiki.entity.Chapter;
import com.myapp.portalnordsyspb.xwiki.repository.ChapterRepository;
import com.myapp.portalnordsyspb.xwiki.service.ChapterServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChapterServiceTest {

    @Mock
    private ChapterRepository chapterRepository;

    @InjectMocks
    private ChapterServiceImpl chapterService;

    @Test
    public void ChapterService_CreateChapter_ReturnsChapterDto(){
        Chapter chapter = Chapter.builder().name("test-name").build();

        ChapterRequestDto chapterRequestDto = new ChapterRequestDto(
                "test-name"
        );

        when(chapterRepository.save(Mockito.any(Chapter.class))).thenReturn(chapter);

        ChapterRequestDto savedChapter = chapterService.createChapter(chapterRequestDto);

        Assertions.assertThat(savedChapter).isNotNull();
    }

//    @Test
//    public void ChapterService_GetAllChaptersWithPosts_ReturnsListChapterResponseDto(){
//        List<Chapter> chapters = Mockito.mock(List.class);
//
//        when(chapterRepository.findAll(Mockito.any(List.class))).thenReturn(chapters);
//
//        List<ChapterResponseDto> saveChapter = chapterService.getAllChaptersWithPosts();
//
//        Assertions.assertThat(saveChapter).isNotNull();
//
//    }
}
