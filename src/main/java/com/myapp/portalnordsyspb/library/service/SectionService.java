package com.myapp.portalnordsyspb.library.service;

import com.myapp.portalnordsyspb.library.dto.request.SectionRequestDto;
import com.myapp.portalnordsyspb.library.dto.response.SectionResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface SectionService {
    SectionRequestDto addSection(SectionRequestDto sectionRequestDto, MultipartFile file) throws IOException;
    SectionResponseDto getSectionById(long sectionId);
}
