package com.myapp.portalnordsyspb.evaluationPU.controller;

import com.myapp.portalnordsyspb.evaluationPU.dto.requestDto.AreaRequestDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.evaluationPU.service.AreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/area")
@Tag(name = "Area & Department PU", description = "Description for area PU")
@RequiredArgsConstructor
public class AreaController {

    private final AreaService areaService;

    @PostMapping("/create-area")
    @Operation(summary = "Создание нового участка",
            description = "Creating new  area. The response is a string about success.")
    public ResponseEntity<MessageDto> createArea(@RequestBody AreaRequestDto areaRequestDto){
        areaService.createArea(areaRequestDto);
        return ResponseEntity.ok(new MessageDto("Area created successfully"));
    }

    @PutMapping("/update-area/{areaId}")
    @Operation(summary = "Редактирование участка",
            description = "Updating area by specifying its id. The response is a string about success.")
    public ResponseEntity<MessageDto> updateArea(@RequestBody AreaRequestDto areaRequestDto,
                                                 @PathVariable("areaId") long areaId){
        areaService.updateArea(areaRequestDto, areaId);
        return ResponseEntity.ok(new MessageDto("Area updated successfully"));
    }

    @DeleteMapping("/delete-area/{areaId}")
    @Operation(summary = "Удаление участка",
            description = "Deleting area by specifying its id. The response is a string about success.")
    public ResponseEntity<MessageDto> deleteArea(@PathVariable("areaId") long areaId){
        areaService.deleteArea(areaId);
        return ResponseEntity.ok(new MessageDto("Area deleted successfully"));
    }
}
