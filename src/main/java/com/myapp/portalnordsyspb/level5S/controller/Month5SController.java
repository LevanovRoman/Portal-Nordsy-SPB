package com.myapp.portalnordsyspb.level5S.controller;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.level5S.dto.request.Month5SRequestDto;
import com.myapp.portalnordsyspb.level5S.dto.request.Result5SRequestDto;
import com.myapp.portalnordsyspb.level5S.dto.response.*;
import com.myapp.portalnordsyspb.level5S.service.Department5SService;
import com.myapp.portalnordsyspb.level5S.service.Month5SService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/month5S")
@Tag(name = "Table 5C", description = "Description for table 5C")
@RequiredArgsConstructor
public class Month5SController {

    private final Month5SService month5SService;
    private final Department5SService department5SService;

    @GetMapping("/list")
    public ResponseEntity<List<Month5SiteDto>> getListMonth5SSite(){
        return ResponseEntity.ok(month5SService.getListMonth5Site());
    }

    @PostMapping("/create-month5s")
    public ResponseEntity<MessageDto> createMonth5S(@RequestBody Month5SRequestDto month5SRequestDto){
        month5SService.createMonth(month5SRequestDto);
        return new ResponseEntity<>(new MessageDto("Month created successfully."), HttpStatus.CREATED);
    }

    @PutMapping("/update-month5s/{monthId}")
    public ResponseEntity<MessageDto> updateMonth5S(@RequestBody Month5SRequestDto month5SRequestDto,
                                                          @PathVariable("monthId") long monthId){
        month5SService.updateMonth(month5SRequestDto, monthId);
        return new ResponseEntity<>(new MessageDto("Month updated successfully."), HttpStatus.OK);
    }

    @DeleteMapping("delete-month5s/{monthId}")
    public ResponseEntity<MessageDto> deleteMonth5S(@PathVariable("monthId") long monthId){
        month5SService.deleteMonth(monthId);
        return new ResponseEntity<>(new MessageDto("Month deleted with id = " + monthId), HttpStatus.OK);
    }

    @GetMapping("/areas5s-and-criterions5s")
    public ResponseEntity<Area5SAndCriterion5SDto> getListArea5SDtoAndCriterion5SDto(){
        return ResponseEntity.ok(month5SService.getListArea5SDtoAndCriterion5SDto());
    }

    @GetMapping("/all-months")
    public ResponseEntity<List<Month5SDto>> getAllMonth5SDto(){
        return ResponseEntity.ok(month5SService.getAllMonth5sDto());
    }

    @GetMapping("/month-result/{monthId}")
    public ResponseEntity<List<Result5SForMonthDto>>  getAllResult5SForMonthDto(@PathVariable("monthId") long monthId){
        return ResponseEntity.ok(month5SService.getAllResult5SForMonthDto(monthId));
    }

    @GetMapping("/average-level-for-department")
    public ResponseEntity<Month5SAverageDto> getListLevelDoneForDepartment(){
        return ResponseEntity.ok(department5SService.getListLevelDoneForDepartment());
    }
}
