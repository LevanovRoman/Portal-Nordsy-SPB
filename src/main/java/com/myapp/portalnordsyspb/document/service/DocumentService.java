package com.myapp.portalnordsyspb.document.service;

import com.myapp.portalnordsyspb.document.dto.request.DocumentRequestDto;
import com.myapp.portalnordsyspb.document.dto.response.DocumentResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocumentService {
    DocumentRequestDto addDocument(DocumentRequestDto documentRequestDto, MultipartFile file) throws IOException;
    List<DocumentResponseDto> getAllDocumentsByCategoryId(long categoryId);
    String deleteDocument(long documentId) throws IOException;
}
