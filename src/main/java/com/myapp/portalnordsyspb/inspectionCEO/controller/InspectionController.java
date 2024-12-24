package com.myapp.portalnordsyspb.inspectionCEO.controller;

import com.myapp.portalnordsyspb.inspectionCEO.dto.response.InspectionResponseDto;
import com.myapp.portalnordsyspb.inspectionCEO.service.InspectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/inspection")
@RequiredArgsConstructor
public class InspectionController {

    private final InspectionService inspectionService;

    @GetMapping("/all")
    public ResponseEntity<List<InspectionResponseDto>> getAllInspections(){
        return ResponseEntity.ok(inspectionService.getAllInspections());
    }
}
