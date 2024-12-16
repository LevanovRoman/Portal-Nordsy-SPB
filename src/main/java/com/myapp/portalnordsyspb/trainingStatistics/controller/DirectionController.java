package com.myapp.portalnordsyspb.trainingStatistics.controller;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.DirectionRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.InstructorRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.DirectionOnlyResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.InstructorResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.service.DirectionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/training/direction")
@Tag(name = "Training Statistics", description = "Description for Training Statistics")
@RequiredArgsConstructor
public class DirectionController {

    private final DirectionService directionService;

    @GetMapping("/all")
    public ResponseEntity<List<DirectionOnlyResponseDto>> getAllDirections(){
        return ResponseEntity.ok(directionService.getAllDirections());
    }

    @PostMapping("/create")
    public ResponseEntity<MessageDto> createDirection(@RequestBody DirectionRequestDto directionRequestDto){
        directionService.createDirection(directionRequestDto);
        return ResponseEntity.ok(new MessageDto("Direction created successfully."));
    }

    @PutMapping("/update/{directionId}")
    public ResponseEntity<MessageDto> updateDirection(@RequestBody DirectionRequestDto directionRequestDto,
                                                       @PathVariable("directionId") long directionId){
        directionService.updateDirection(directionRequestDto, directionId);
        return ResponseEntity.ok(new MessageDto("Direction updated successfully."));
    }

    @DeleteMapping("/delete/{directionId}")
    public ResponseEntity<MessageDto> deleteDirection(@PathVariable("directionId") long directionId){
        directionService.deleteDirection(directionId);
        return ResponseEntity.ok(new MessageDto("Direction deleted successfully."));
    }
}
