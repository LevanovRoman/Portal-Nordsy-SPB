package com.myapp.portalnordsyspb.getDataFromDb;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("getdata")
@RequiredArgsConstructor
public class TempController {

    private final TempService tempService;

    @GetMapping
    public String getDataFromDb() throws SQLException {
        tempService.getDataFromDb();
        return "ok";
    }
}
