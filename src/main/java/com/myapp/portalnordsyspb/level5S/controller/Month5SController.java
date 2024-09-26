package com.myapp.portalnordsyspb.level5S.controller;

import com.myapp.portalnordsyspb.level5S.dto.Department5STableDto;
import com.myapp.portalnordsyspb.level5S.dto.test.Month5SiteDto;
import com.myapp.portalnordsyspb.level5S.repository.Month5SRepository;
import com.myapp.portalnordsyspb.level5S.service.Month5SService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
