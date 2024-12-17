package com.myapp.portalnordsyspb.trainingStatistics.controller;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.exceptions.ObjectNotFoundException;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.DirectionRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.DirectionOnlyResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Unit;
import com.myapp.portalnordsyspb.trainingStatistics.repository.UnitRepository;
import com.myapp.portalnordsyspb.trainingStatistics.service.DirectionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/training/direction")
@Tag(name = "Training Statistics", description = "Description for Training Statistics")
@RequiredArgsConstructor
public class DirectionController {

    private final DirectionService directionService;
    private final UnitRepository unitRepository;

    @GetMapping("/all")
    public ResponseEntity<List<DirectionOnlyResponseDto>> getAllDirections(){
        Optional<Unit> result = unitRepository.findFilterByTubNumber(1L, 1L, 1L, "10103");
        System.out.println(result.isPresent());
        System.out.println(result.get().getDirection().getName());
        System.out.println(result.get().getValues().toString());

        Optional<Unit> result2 = unitRepository.findFilterByUnitValue(1L, 1L, 1L, 40);
        System.out.println("UnitValue  " + result2.isPresent());
        System.out.println("UnitValue  " + result2.orElseThrow(()->new ObjectNotFoundException("NOT")).getDirection().getName());
        System.out.println("UnitValue  " + result2.orElseThrow(()->new ObjectNotFoundException("NOTVAL")).getValues().toString());

        Optional<Unit> result3 = unitRepository.findFilterByTabNumberAndUnitValue(1L, 1L, 1L, 40, "10103");
        System.out.println("UnitValue33  " + result3.isPresent());
        System.out.println("UnitValue33  " + result3.orElseThrow(()->new ObjectNotFoundException("NOT33")).getDirection().getName());
        System.out.println("UnitValue33  " + result3.orElseThrow(()->new ObjectNotFoundException("NOTVAL33")).getValues().toString());

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
