package com.myapp.portalnordsyspb.service;

import com.myapp.portalnordsyspb.dto.ResultDto;
import com.myapp.portalnordsyspb.entities.Result;
import com.myapp.portalnordsyspb.repositories.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService{

    private final ResultRepository resultRepository;

    @Override
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    @Override
    public List<ResultDto> getResultList() {
        return List.of();
    }

//    @Override
//    public List<ResultDto> getResultList() {
//        List<Result> resultList = resultRepository.findAll();
//        List<ResultDto> resultDtoList = new ArrayList<>();
//        for (Result result : resultList){
//            ResultDto resultDto = new ResultDto();
//            resultDto.setValue(result.getValue());
//            resultDto.setWeek(result.getWeek().getNumber());
//            resultDto.setCriterion(result.getAreaCriterion().getCriterion().getName());
//            resultDto.setArea(result.getAreaCriterion().getArea().getName());
//            resultDto.setDepartment(result.getAreaCriterion().getArea().getDepartment().getNumber());
//            resultDtoList.add(resultDto);
//        }
//
//        return resultDtoList;
//    }

    @Override
    public List<ResultDto> getResultListByAreaId(Long areaId) {
        List<Result> resultList = resultRepository.findAllByAreaId(areaId);
        List<ResultDto> resultDtoList = new ArrayList<>();
        for (Result result : resultList){
            ResultDto resultDto = new ResultDto();
            resultDto.setValue(result.getValue());
            resultDto.setWeek(result.getWeek().getNumber());
            resultDto.setCriterion(result.getCriterion().getName());
//            resultDto.setArea(result.getArea().getName());
//            resultDto.setDepartment(result.getAreaCriterion().getArea().getDepartment().getNumber());
            resultDtoList.add(resultDto);
        }
        return resultDtoList;
    }
}
