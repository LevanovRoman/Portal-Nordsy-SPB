package com.myapp.portalnordsyspb.service;

import com.myapp.portalnordsyspb.dto.ResultDto;
import com.myapp.portalnordsyspb.dto.ResultWeekDto;
import com.myapp.portalnordsyspb.entities.Result;
import com.myapp.portalnordsyspb.repositories.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService{

    private final ResultRepository resultRepository;

    @Override
    public List<ResultDto> getListResultsByAreaId(Long areaId) {
        return resultRepository.findAllByAreaId(areaId)
                .stream()
                .map(this::convertResultToDto)
                .toList();
    }

    @Override
    public List<ResultWeekDto> getListResultsByAreaIdAndWeekNumber(Long areaId, int weekNumber) {
        return resultRepository.findAllByAreaIdAndWeekNumber(areaId, weekNumber)
                .stream()
                .map(this::convertResultWeekToDto)
                .toList();
    }

    private ResultDto convertResultToDto(Result result){
        ResultDto resultDto = new ResultDto();
        resultDto.setCriterion(result.getCriterion().getName());
        resultDto.setWeek(result.getWeek().getNumber());
        resultDto.setValue(result.getValue());
        return resultDto;
    }

    private ResultWeekDto convertResultWeekToDto(Result result){
        ResultWeekDto resultWeekDto = new ResultWeekDto();
        resultWeekDto.setCriterion(result.getCriterion().getName());
        resultWeekDto.setValue(result.getValue());
        return resultWeekDto;
    }

//    public List<Result> getResultFor2Week(Long areaId){
//        return resultRepository.findTop2ByOrderByAreaIdDesc(areaId);
//    }
}
