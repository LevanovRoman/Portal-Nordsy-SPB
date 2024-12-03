package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.trainingStatistics.dto.response.InstructorResponseDto;

import java.util.List;

public interface InstructorService {

//    List<InstructorResponseDto> getAllInstructorResponseDto();

    List<InstructorResponseDto> getAllByDirectionId(Long id);
}
