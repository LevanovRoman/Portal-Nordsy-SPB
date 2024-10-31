package com.myapp.portalnordsyspb.library.service;

import com.myapp.portalnordsyspb.library.dto.request.DocumentRequestDto;
import com.myapp.portalnordsyspb.library.dto.response.DocumentResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DocumentTestService {
    DocumentRequestDto addDocument(DocumentRequestDto documentRequestDto, MultipartFile file) throws IOException;
    DocumentResponseDto getDocumentById(long documentId);
}
