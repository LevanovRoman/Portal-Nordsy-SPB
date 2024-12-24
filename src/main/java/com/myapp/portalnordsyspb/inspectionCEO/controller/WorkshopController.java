package com.myapp.portalnordsyspb.inspectionCEO.controller;

import com.myapp.portalnordsyspb.inspectionCEO.dto.response.WorkshopResponseDto;
import com.myapp.portalnordsyspb.inspectionCEO.service.WorkshopService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/inspection/workshop")
@Tag(name = "Inspections CEO", description = "Description for Inspections CEO")
@RequiredArgsConstructor
public class WorkshopController {

    private final WorkshopService workshopService;

    @GetMapping("/all")
    public ResponseEntity<List<WorkshopResponseDto>> getAllWorkshops(){
        return ResponseEntity.ok(workshopService.getAllWorkshops());
    }
}
