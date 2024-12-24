package com.myapp.portalnordsyspb.inspectionCEO.service;

import com.myapp.portalnordsyspb.inspectionCEO.dto.response.InspectionResponseDto;
import com.myapp.portalnordsyspb.inspectionCEO.entity.Inspection;
import com.myapp.portalnordsyspb.inspectionCEO.repository.InspectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InspectionServiceImpl implements InspectionService{

    private final InspectionRepository inspectionRepository;

    @Override
    public List<InspectionResponseDto> getAllInspections() {
        return inspectionRepository.findAll()
                .stream()
                .map(this::convertInspectionToInspectionResponseDto)
                .toList();
    }

    private InspectionResponseDto convertInspectionToInspectionResponseDto(Inspection inspection) {
        return new InspectionResponseDto(
                inspection.getDepartment(),
                inspection.getDate(),
                inspection.getTotalScore(),
                inspection.getGeneralScore()
        );
    }
}
