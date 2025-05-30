package com.myapp.portalnordsyspb.inspectionCEO.service;

import com.myapp.portalnordsyspb.exceptions.ObjectNotFoundException;
import com.myapp.portalnordsyspb.inspectionCEO.dto.request.InspectionRequestDto;
import com.myapp.portalnordsyspb.inspectionCEO.dto.response.InspectionResponseDto;
import com.myapp.portalnordsyspb.inspectionCEO.entity.Inspection;
import com.myapp.portalnordsyspb.inspectionCEO.entity.Workshop;
import com.myapp.portalnordsyspb.inspectionCEO.repository.InspectionRepository;
import com.myapp.portalnordsyspb.inspectionCEO.repository.WorkshopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InspectionServiceImpl implements InspectionService{

    private final InspectionRepository inspectionRepository;
    private final WorkshopRepository workshopRepository;

    @Override
    public List<InspectionResponseDto> getInspectionsByWorkshopId(Long workshopId) {
        return inspectionRepository.findTop2ByWorkshopIdOrderByIdDesc(workshopId)
                .stream()
                .sorted(Comparator.comparing(Inspection::getId))
                .map(this::convertInspectionToInspectionResponseDto).toList();
    }

    private InspectionResponseDto convertInspectionToInspectionResponseDto(Inspection inspection) {
        return new InspectionResponseDto(
                inspection.getId(),
                inspection.getDate(),
                inspection.getTotalScore(),
                inspection.getGeneralScore()
        );
    }

    @Override
    public Inspection getInspectionById(long inspectionId) {
        return inspectionRepository
                .findById(inspectionId)
                .orElseThrow(() -> new ObjectNotFoundException("Inspection not found."));
    }

    @Override
    public void createInspection(InspectionRequestDto inspectionRequestDto) {
        Inspection inspection = new Inspection();
        Workshop workshop = workshopRepository.findById(inspectionRequestDto.id())
                        .orElseThrow(() -> new ObjectNotFoundException("Workshop not found."));
        inspection.setWorkshop(workshop);
        inspection.setDate(inspectionRequestDto.date());
        inspection.setTotalScore(inspectionRequestDto.totalScore());
        inspection.setGeneralScore(inspectionRequestDto.generalScore());
        inspectionRepository.save(inspection);
    }

    @Override
    public void deleteInspection(long inspectionId) {
        Inspection inspectionDelete = getInspectionById(inspectionId);
        inspectionRepository.delete(inspectionDelete);
    }
}
