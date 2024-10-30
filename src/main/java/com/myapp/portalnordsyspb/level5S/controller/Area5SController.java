package com.myapp.portalnordsyspb.level5S.controller;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.level5S.dto.request.Area5SRequestDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Maxvalue5SResponseDto;
import com.myapp.portalnordsyspb.level5S.service.Area5SService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/level-5s")
@Tag(name = "Area & Department 5C", description = "Description for it")
@RequiredArgsConstructor
public class Area5SController {

    private final Area5SService area5SService;

    @PostMapping("/create-area5s")
    @Operation(summary = "Создание нового участка",
            description = "Creating new  area. The response is a string about success.")
    public ResponseEntity<MessageDto> createArea5S(@RequestBody Area5SRequestDto area5SRequestDto){
        area5SService.createArea5S(area5SRequestDto);
        return ResponseEntity.ok(new MessageDto("Area created successfully"));
    }

    @PutMapping("/update-area5s/{areaId}")
    @Operation(summary = "Редактирование участка",
            description = "Updating area by specifying its id. The response is a string about success.")
    public ResponseEntity<MessageDto> updateArea5S(@RequestBody Area5SRequestDto area5SRequestDto,
                                                 @PathVariable("areaId") long areaId){
        area5SService.updateArea5S(area5SRequestDto, areaId);
        return ResponseEntity.ok(new MessageDto("Area updated successfully"));
    }

    @DeleteMapping("/delete-area5s/{areaId}")
    @Operation(summary = "Удаление участка",
            description = "Deleting area by specifying its id. The response is a string about success.")
    public ResponseEntity<MessageDto> deleteArea5S(@PathVariable("areaId") long areaId){
        area5SService.deleteArea5S(areaId);
        return ResponseEntity.ok(new MessageDto("Area deleted successfully"));
    }

    @GetMapping("/all-maxvalue")
    @Operation(summary = "Получение всех максимальных значений",
            description = "Getting all max values. The response is a list with objects with id and value of each maxvalue.")
    public ResponseEntity<List<Maxvalue5SResponseDto>> getAllMaxvalue(){
        return ResponseEntity.ok(area5SService.getAllMaxvalue());
    }

}
