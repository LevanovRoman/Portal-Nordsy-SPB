package com.myapp.portalnordsyspb.document.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.portalnordsyspb.document.dto.request.DocumentRequestDto;
import com.myapp.portalnordsyspb.document.dto.response.DocumentResponseDto;
import com.myapp.portalnordsyspb.document.service.DocumentService;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.exceptions.EmptyFileException;
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
@Tag(name = "Documents", description = "Description for documents")
@RequestMapping("/api/documents/document")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @Operation(summary = "Создание нового документа",
            description = "Creating new document. The response is object DocumentRequestDto.")
    @PostMapping("/add")
    public ResponseEntity<DocumentRequestDto> createDocument(@RequestPart MultipartFile file,
                                                             @RequestPart String documentData) throws IOException{
        if (file.isEmpty()) {
            throw new EmptyFileException("File is empty! Please send another file.");
        }
        DocumentRequestDto documentRequestDto = convertDocumentDataToDocumentRequestDto(documentData);
        return ResponseEntity.ok(documentService.addDocument(documentRequestDto, file));
    }

    @Operation(summary = "Получение всех документов из заданной категории.")
    @GetMapping("/all-in-category/{categoryId}")
    public ResponseEntity<List<DocumentResponseDto>> getAllDocumentsByCategoryId(@PathVariable("categoryId") long categoryId){
        return ResponseEntity.ok(documentService.getAllDocumentsByCategoryId(categoryId));
    }

    @Operation(summary = "Удаление документа по id.")
    @DeleteMapping("delete/{documentId}")
    public ResponseEntity<MessageDto> deleteSection(@PathVariable("documentId") long documentId) throws IOException {
        return ResponseEntity.ok(new MessageDto(documentService.deleteDocument(documentId)));
    }

    private DocumentRequestDto convertDocumentDataToDocumentRequestDto(String documentData)
            throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String documentDto = new String(documentData.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        return objectMapper.readValue(documentDto, DocumentRequestDto.class);
    }
}
