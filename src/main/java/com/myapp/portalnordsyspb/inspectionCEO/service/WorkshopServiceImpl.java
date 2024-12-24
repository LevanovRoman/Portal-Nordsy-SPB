package com.myapp.portalnordsyspb.inspectionCEO.service;

import com.myapp.portalnordsyspb.inspectionCEO.dto.response.WorkshopResponseDto;
import com.myapp.portalnordsyspb.inspectionCEO.entity.Workshop;
import com.myapp.portalnordsyspb.inspectionCEO.repository.WorkshopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkshopServiceImpl implements WorkshopService{

    private final WorkshopRepository workshopRepository;
    private final InspectionService inspectionService;


    @Override
    public List<WorkshopResponseDto> getAllWorkshops() {
        return workshopRepository.findAll()
                .stream().map(this::convertWorkshopToWorkshopResponseDto).toList();
    }

    private WorkshopResponseDto convertWorkshopToWorkshopResponseDto(Workshop workshop) {
        return new WorkshopResponseDto(
                workshop.getId(),
                workshop.getNumber(),
                inspectionService.getInspectionsByWorkshopId(workshop.getId())
        );
    }


}
