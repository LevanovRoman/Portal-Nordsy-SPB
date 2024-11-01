package com.myapp.portalnordsyspb.library.service;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.library.dto.request.SectionRequestDto;
import com.myapp.portalnordsyspb.library.dto.response.SectionResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SectionService {
    SectionRequestDto addSection(SectionRequestDto sectionRequestDto, MultipartFile file) throws IOException;
    SectionRequestDto updateSection(long sectionId,SectionRequestDto sectionRequestDto, MultipartFile file) throws IOException;
    SectionResponseDto getSectionById(long sectionId);
    List<SectionResponseDto> getAllSections();
    String deleteSection(long sectionId) throws IOException;
}
