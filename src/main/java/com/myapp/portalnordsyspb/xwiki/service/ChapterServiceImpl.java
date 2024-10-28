package com.myapp.portalnordsyspb.xwiki.service;

import com.myapp.portalnordsyspb.exceptions.ChapterNotFoundException;
import com.myapp.portalnordsyspb.xwiki.dto.requestDto.ChapterRequestDto;
import com.myapp.portalnordsyspb.xwiki.dto.responseDto.ChapterResponseDto;
import com.myapp.portalnordsyspb.xwiki.entity.Chapter;
import com.myapp.portalnordsyspb.xwiki.repository.ChapterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService{

    private final ChapterRepository chapterRepository;
    private final PostService postService;

    // собираем полный json с главами и постами
    @Override
    public List<ChapterResponseDto> getAllChaptersWithPosts() {
        return chapterRepository.findAll()
                .stream()
                .map(this::convertAllChaptersToChaptersResponseDto)
                .collect(Collectors.toList());
    }

    // получаем главу по id
    @Override
    public Chapter getChapterById(Long chapterId) {
        return chapterRepository.findById(chapterId)
                .orElseThrow(()->new ChapterNotFoundException("Глава с id = " + chapterId + " не найдена"));
    }

    // создаем новую главу
    @Override
    public ChapterRequestDto createChapter(ChapterRequestDto chapterRequestDto) {
        Chapter chapter = new Chapter();
        chapter.setName(chapterRequestDto.name());
        Chapter newChapter = chapterRepository.save(chapter);
        return new ChapterRequestDto(
                newChapter.getName()
        );
    }

    // редактируем главу
    @Override
    public ChapterRequestDto updateChapter(ChapterRequestDto chapterRequestDto, long chapterId) {
        Chapter chapter = getChapterById(chapterId);
        chapter.setName(chapterRequestDto.name());
        Chapter updatedChapter = chapterRepository.save(chapter);
        return new ChapterRequestDto(updatedChapter.getName());
    }

    @Override
    public void deleteChapter(long id) {
        Chapter chapter = getChapterById(id);
        chapterRepository.delete(chapter);
    }

    private ChapterResponseDto convertAllChaptersToChaptersResponseDto(Chapter chapter) {
        return new ChapterResponseDto(
                chapter.getId(),
                chapter.getName(),
                postService.getListPostResponseDtoByChapterId(chapter.getId())
        );
    }
}
