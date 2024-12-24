package com.myapp.portalnordsyspb.inspectionCEO.service;

import com.myapp.portalnordsyspb.inspectionCEO.dto.response.InspectionResponseDto;
import com.myapp.portalnordsyspb.inspectionCEO.entity.Inspection;
import com.myapp.portalnordsyspb.inspectionCEO.repository.InspectionRepository;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Direction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InspectionServiceImpl implements InspectionService{

    private final InspectionRepository inspectionRepository;

    @Override
    public List<InspectionResponseDto> getInspectionsByWorkshopId(Long workshopId) {
        return inspectionRepository.findTop2ByWorkshopIdOrderByWorkshopId(workshopId)
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


//    @Override
//    public List<InspectionResponseDto> getAllInspections() {
//        return inspectionRepository.findAll()
//                .stream()
//                .map(this::convertInspectionToInspectionResponseDto)
//                .toList();
//    }
//
//    @Override
//    public Inspection getInspectionById(long inspectionId) {
//        return inspectionRepository
//                .findById(inspectionId)
//                .orElseThrow(() -> new ObjectNotFoundException("Inspection not found."));
//    }
//
//    @Override
//    public void createInspection(InspectionRequestDto inspectionRequestDto) {
//        Inspection inspectionNew = new Inspection();
//        saveInspection(inspectionRequestDto, inspectionNew);
//    }
//
//    @Override
//    public void updateInspection(InspectionRequestDto inspectionRequestDto, long inspectionId) {
//        Inspection inspectionUpdate = getInspectionById(inspectionId);
//        saveInspection(inspectionRequestDto, inspectionUpdate);
//    }
//
//    @Override
//    public void deleteInspection(long inspectionId) {
//        Inspection inspectionDelete = getInspectionById(inspectionId);
//        inspectionRepository.delete(inspectionDelete);
//    }
//
//    private void saveInspection(InspectionRequestDto inspectionRequestDto, Inspection inspection){
//        inspection.setDepartment(inspectionRequestDto.department());
//        inspection.setDate(inspectionRequestDto.date());
//        inspection.setTotalScore(inspectionRequestDto.totalScore());
//        inspection.setGeneralScore(inspectionRequestDto.generalScore());
//        inspectionRepository.save(inspection);
//    }
//
//    private InspectionResponseDto convertInspectionToInspectionResponseDto(Inspection inspection) {
//        return new InspectionResponseDto(
//                inspection.getId(),
//                inspection.getDepartment(),
//                inspection.getDate(),
//                inspection.getTotalScore(),
//                inspection.getGeneralScore()
//        );
//    }
}
