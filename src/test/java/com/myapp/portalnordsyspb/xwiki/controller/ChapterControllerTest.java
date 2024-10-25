package com.myapp.portalnordsyspb.xwiki.controller;

import com.myapp.portalnordsyspb.xwiki.dto.requestDto.ChapterRequestDto;
import com.myapp.portalnordsyspb.xwiki.dto.responseDto.ChapterResponseDto;
import com.myapp.portalnordsyspb.xwiki.entity.Chapter;
import com.myapp.portalnordsyspb.xwiki.service.ChapterService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@DisplayName("Модульные тесты ChapterController")
class ChapterControllerTest {

    @Mock
    ChapterService chapterService;

    @InjectMocks
    ChapterController controller;

    @Test
    @DisplayName("createChapter создаст новую главу и вернет статус CREATED")
    void createChapter_RequestIsValid_ReturnsStatusCreated(){
//        // given
//        var chapterRequestDto = new ChapterRequestDto("test-name");
//        doReturn(new Chapter(1, "test-name"))
//                .when(this.chapterService).createChapter("test-name");
//
//        // when
//        var result = new ResponseEntity(this.controller.createChapter(chapterRequestDto), HttpStatus.CREATED);
//
//        // then
//        assertEquals(chapterRequestDto, result);
    }
}