package com.myapp.portalnordsyspb.library.service;

import com.myapp.portalnordsyspb.library.dto.request.SectionRequestDto;
import com.myapp.portalnordsyspb.library.dto.response.SectionResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class SectionServiceImpl implements SectionService{
    @Override
    public SectionRequestDto addSection(SectionRequestDto sectionRequestDto, MultipartFile file) throws IOException {
        return null;
    }

    @Override
    public SectionResponseDto getSectionById(long sectionId) {
        return null;
    }
}
