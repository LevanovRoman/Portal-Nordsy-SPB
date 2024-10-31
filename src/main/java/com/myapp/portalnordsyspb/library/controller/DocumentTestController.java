package com.myapp.portalnordsyspb.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.exceptions.EmptyFileException;
import com.myapp.portalnordsyspb.library.dto.request.DocumentRequestDto;
import com.myapp.portalnordsyspb.library.dto.response.DocumentResponseDto;
import com.myapp.portalnordsyspb.library.service.DocumentService;
import com.myapp.portalnordsyspb.library.service.DocumentTestService;
import com.myapp.portalnordsyspb.news.dto.request.NewsRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class DocumentTestController {

    private final DocumentTestService documentService;

    @PostMapping("/add-document")
    public ResponseEntity<DocumentRequestDto> createDocumentPdf(@RequestPart MultipartFile file,
                                                                   @RequestPart String fileData) throws IOException {
        if (file.isEmpty()) {
            throw new EmptyFileException("File is empty! Please send another file.");
        }
        DocumentRequestDto documentRequestDto = convertFileDataToDocumentRequestDto(fileData);
        return ResponseEntity.ok(documentService.addDocument(documentRequestDto, file));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentResponseDto> getDocumentById(@PathVariable long id){
        return ResponseEntity.ok(documentService.getDocumentById(id));
    }

    private DocumentRequestDto convertFileDataToDocumentRequestDto(String fileData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String documentDto = new String(fileData.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        return objectMapper.readValue(documentDto, DocumentRequestDto.class);
    }
}
