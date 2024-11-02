package com.myapp.portalnordsyspb.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.exceptions.EmptyFileException;
import com.myapp.portalnordsyspb.library.dto.request.SectionRequestDto;
import com.myapp.portalnordsyspb.library.dto.response.SectionResponseDto;
import com.myapp.portalnordsyspb.library.service.SectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@Tag(name = "Library", description = "Description for library")
@RequestMapping("/api/library/section")
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    @Operation(summary = "Создание нового раздела",
            description = "Creating new section. The response is object SectionRequestDto.")
    @PostMapping("/add")
    public ResponseEntity<SectionRequestDto> createSection(@RequestPart MultipartFile file,
                                                        @RequestPart String sectionData) throws IOException {
        if (file.isEmpty()) {
            throw new EmptyFileException("File is empty! Please send another file.");
        }
        SectionRequestDto sectionRequestDto = convertFileDataToSectionRequestDto(sectionData);
        return ResponseEntity.ok(sectionService.addSection(sectionRequestDto, file));
    }

    @Operation(summary = "Получение раздела по id.")
    @GetMapping("/{id}")
    public ResponseEntity<SectionResponseDto> getSectionById(@PathVariable long id){
        return ResponseEntity.ok(sectionService.getSectionById(id));
    }

    @Operation(summary = "Получение всех разделов.")
    @GetMapping("/all")
    public ResponseEntity<List<SectionResponseDto>> getAllSections() {
        return ResponseEntity.ok(sectionService.getAllSections());
    }

    @Operation(summary = "Изменение раздела по id.")
    @PutMapping("update/{sectionId}")
    public ResponseEntity<SectionRequestDto> updateSection(@PathVariable("sectionId") long sectionId,
                                                     @RequestPart MultipartFile file,
                                                     @RequestPart String sectionData) throws IOException {
        if (file.isEmpty()) file = null;
        SectionRequestDto sectionRequestDto = convertFileDataToSectionRequestDto(sectionData);
        return ResponseEntity.ok(sectionService.updateSection(sectionId, sectionRequestDto, file));
    }

    @Operation(summary = "Удаление раздела по id.")
    @DeleteMapping("delete/{sectionId}")
    public ResponseEntity<MessageDto> deleteSection(@PathVariable("sectionId") long sectionId) throws IOException {
        return ResponseEntity.ok(new MessageDto(sectionService.deleteSection(sectionId)));
    }

    private SectionRequestDto convertFileDataToSectionRequestDto(String fileData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String documentDto = new String(fileData.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        return objectMapper.readValue(documentDto, SectionRequestDto.class);
    }
}
