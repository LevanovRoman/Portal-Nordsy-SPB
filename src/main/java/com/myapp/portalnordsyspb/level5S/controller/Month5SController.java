package com.myapp.portalnordsyspb.level5S.controller;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.level5S.dto.request.Month5SRequestDto;
import com.myapp.portalnordsyspb.level5S.dto.request.Result5SRequestDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Area5SAndCriterion5SDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Month5SiteDto;
import com.myapp.portalnordsyspb.level5S.service.Month5SService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/month5S")
@RequiredArgsConstructor
public class Month5SController {

    private final Month5SService month5SService;

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
    public ResponseEntity<MessageDto> updateMonth5S(@RequestBody List<Result5SRequestDto> result5SRequestDtoList,
                                                          @PathVariable("monthId") long monthId){
        month5SService.updateMonth(result5SRequestDtoList, monthId);
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
}
