package com.myapp.portalnordsyspb.inspectionCEO.controller;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.inspectionCEO.dto.request.InspectionRequestDto;
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

    @PostMapping("/create")
    public ResponseEntity<MessageDto> createInspection(@RequestBody InspectionRequestDto inspectionRequestDto){
        inspectionService.createInspection(inspectionRequestDto);
        return ResponseEntity.ok(new MessageDto("Inspection created successfully."));
    }

    @GetMapping("/visit-statuses")
    public List<ScoreColor> getVisitStatuses() {
        return Arrays.asList(ScoreColor.values());
    }

    @DeleteMapping("/delete/{inspectionId}")
    public ResponseEntity<MessageDto> deleteInspection(@PathVariable long inspectionId){
        inspectionService.deleteInspection(inspectionId);
        return ResponseEntity.ok(new MessageDto("Inspection deleted successfully."));
    }
}
