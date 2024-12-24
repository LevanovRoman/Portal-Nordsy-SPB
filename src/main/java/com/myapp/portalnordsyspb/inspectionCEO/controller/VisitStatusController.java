package com.myapp.portalnordsyspb.inspectionCEO.controller;

import com.myapp.portalnordsyspb.inspectionCEO.entity.ScoreColor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class VisitStatusController {

    @GetMapping("/api/visit-statuses")
    public List<ScoreColor> getVisitStatuses() {
        return Arrays.asList(ScoreColor.values());
    }


}

