package com.myapp.portalnordsyspb.inspectionCEO.controller;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.inspectionCEO.dto.request.InspectionRequestDto;
import com.myapp.portalnordsyspb.inspectionCEO.dto.response.InspectionResponseDto;
import com.myapp.portalnordsyspb.inspectionCEO.entity.ScoreColor;
import com.myapp.portalnordsyspb.inspectionCEO.service.InspectionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/inspection")
@Tag(name = "Inspections CEO", description = "Description for Inspections CEO")
@RequiredArgsConstructor
public class InspectionController {

    private final InspectionService inspectionService;

//    @GetMapping("/all")
//    public ResponseEntity<List<InspectionResponseDto>> getAllInspections(){
//        return ResponseEntity.ok(inspectionService.getAllInspections());
//    }
//
    @PostMapping("/create")
    public ResponseEntity<MessageDto> createInspection(@RequestBody InspectionRequestDto inspectionRequestDto){
        inspectionService.createInspection(inspectionRequestDto);
        return ResponseEntity.ok(new MessageDto("Inspection created successfully."));
    }

    @GetMapping("/visit-statuses")
    public List<ScoreColor> getVisitStatuses() {
        return Arrays.asList(ScoreColor.values());
    }
//
//    @PutMapping("/update/{inspectionId}")
//    public ResponseEntity<MessageDto> updateInspection(@RequestBody InspectionRequestDto inspectionRequestDto,
//                                                       @PathVariable long inspectionId){
//        inspectionService.updateInspection(inspectionRequestDto, inspectionId);
//        return ResponseEntity.ok(new MessageDto("Inspection updated successfully."));
//    }
//
    @DeleteMapping("/delete/{inspectionId}")
    public ResponseEntity<MessageDto> deleteInspection(@PathVariable long inspectionId){
        inspectionService.deleteInspection(inspectionId);
        return ResponseEntity.ok(new MessageDto("Inspection deleted successfully."));
    }
}
