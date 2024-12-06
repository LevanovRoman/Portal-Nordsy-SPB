package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.trainingStatistics.dto.request.InstructorRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.InstructorResponseDto;

import java.util.List;

public interface InstructorService {

    List<InstructorResponseDto> getAllByDirectionId(Long id);

    List<InstructorResponseDto> getAllInstructor();

    void createInstructor(InstructorRequestDto instructorRequestDto);

    void updateInstructor(InstructorRequestDto instructorRequestDto, long instructorId);

    void deleteInstructor(long instructorId);
}
