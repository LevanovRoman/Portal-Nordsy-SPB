package com.myapp.portalnordsyspb.repository;

import com.myapp.portalnordsyspb.xwiki.entity.Chapter;
import com.myapp.portalnordsyspb.xwiki.repository.ChapterRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ChapterRepositoryTest {

    @Autowired
    private ChapterRepository chapterRepository;

    @Test
    public void chapterRepository_SaveAll_ReturnsSavedChapter(){
        // Arrange
        Chapter chapter = Chapter.builder().name("test-name").build();
        // Act
        Chapter savedChapter = chapterRepository.save(chapter);
        // Assert
        Assertions.assertThat(savedChapter).isNotNull();
        Assertions.assertThat(savedChapter.getId()).isGreaterThan(0);
    }

    @Test
    public void chapterRepository_GetAll_ReturnsMoreThanOneChapter(){
        Chapter chapter = Chapter.builder().name("test-name").build();
        Chapter chapter2 = Chapter.builder().name("test-name2").build();

        chapterRepository.save(chapter);
        chapterRepository.save(chapter2);

        List<Chapter> chapterList = chapterRepository.findAll();

        Assertions.assertThat(chapterList).isNotNull();
        Assertions.assertThat(chapterList.size()).isEqualTo(2);
    }

    @Test
    public void chapterRepository_FindById_ReturnChapter(){
        Chapter chapter = Chapter.builder().name("test-name").build();

        chapterRepository.save(chapter);
        Chapter chapterReturn = chapterRepository.findById(chapter.getId()).get();

        Assertions.assertThat(chapterReturn).isNotNull();
    }

    @Test
    public void chapterRepository_UpdateChapter_ReturnChapterNotNull(){
        Chapter chapter = Chapter.builder().name("test-name").build();

        chapterRepository.save(chapter);

        Chapter chapterSave = chapterRepository.findById(chapter.getId()).get();
        chapterSave.setName("update-name");

        Chapter updatedChapter = chapterRepository.save(chapterSave);

        Assertions.assertThat(updatedChapter.getName()).isNotNull();
    }

    @Test
    public void chapterRepository_DeleteChapter_ReturnChapterIsEmpty(){
        Chapter chapter = Chapter.builder().name("test-name").build();

        chapterRepository.save(chapter);

        chapterRepository.delete(chapterRepository.findById(chapter.getId()).get());

        Optional<Chapter> chapterReturn = chapterRepository.findById(chapter.getId());

        Assertions.assertThat(chapterReturn).isEmpty();
    }
}