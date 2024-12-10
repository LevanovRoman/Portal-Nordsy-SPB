package com.myapp.portalnordsyspb.trainingStatistics.controller;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.InstructorRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.InstructorResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.service.InstructorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/training/instructor")
@Tag(name = "Training Statistics", description = "Description for Training Statistics")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping("/all")
    public ResponseEntity<List<InstructorResponseDto>> getAllInstructor(){
        return ResponseEntity.ok(instructorService.getAllInstructor());
    }

    @PostMapping("/create")
    public ResponseEntity<MessageDto> createInstructor(@RequestBody InstructorRequestDto instructorRequestDto){
        instructorService.createInstructor(instructorRequestDto);
        return ResponseEntity.ok(new MessageDto("Instructor created successfully."));
    }

    @PutMapping("/update/{instructorId}")
    public ResponseEntity<MessageDto> updateInstructor(@RequestBody InstructorRequestDto instructorRequestDto,
                                                   @PathVariable("instructorId") long instructorId){
        instructorService.updateInstructor(instructorRequestDto, instructorId);
        return ResponseEntity.ok(new MessageDto("Instructor updated successfully."));
    }

    @DeleteMapping("/delete/{instructorId}")
    public ResponseEntity<MessageDto> deleteInstructor(@PathVariable("instructorId") long instructorId){
        instructorService.deleteInstructor(instructorId);
        return ResponseEntity.ok(new MessageDto("Instructor deleted successfully."));
    }
}
