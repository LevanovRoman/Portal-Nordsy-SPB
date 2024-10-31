package com.myapp.portalnordsyspb.statisticsPPU;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyController {
    @Autowired
    private ApiService apiService;

    @GetMapping("/external-data")
    public List<MyDataDto> getExternalData() {
        List<MyDataDto> test = apiService.getDataFromExternalApi();
        System.out.println(test.getFirst().Zaregistrirovano());
        return test;
    }
}
