package com.myapp.portalnordsyspb.service;

import com.myapp.portalnordsyspb.dto.ResultDto;
import com.myapp.portalnordsyspb.entities.Result;

import java.util.List;

public interface ResultService {

    List<Result> getAllResults();

    List<ResultDto> getResultList();
}
