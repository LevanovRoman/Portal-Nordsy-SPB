package com.myapp.portalnordsyspb.repository;

import com.myapp.portalnordsyspb.xwiki.entity.Chapter;
import com.myapp.portalnordsyspb.xwiki.repository.ChapterRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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

}
