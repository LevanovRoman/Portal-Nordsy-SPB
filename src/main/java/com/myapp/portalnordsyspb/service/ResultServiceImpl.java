package com.myapp.portalnordsyspb.service;

import com.myapp.portalnordsyspb.dto.ResultDto;
import com.myapp.portalnordsyspb.dto.ResultLastWeekDto;
import com.myapp.portalnordsyspb.dto.ResultTotalFourWeeksDto;
import com.myapp.portalnordsyspb.entities.Result;
import com.myapp.portalnordsyspb.entities.Week;
import com.myapp.portalnordsyspb.repositories.ResultRepository;
import com.myapp.portalnordsyspb.repositories.WeekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService{

    private final ResultRepository resultRepository;
    private final WeekRepository weekRepository;

    @Override
    public List<ResultDto> getListResultsByAreaId(Long areaId) {
        return resultRepository.findAllByAreaId(areaId)
                .stream()
                .map(this::convertResultToDto)
                .toList();
    }

    @Override
    public List<ResultLastWeekDto> getListResultsByAreaIdForLastWeek(Long areaId) {
        long lastWeekId = weekRepository.findTopByOrderByIdDesc().getId();
        return resultRepository.findAllByAreaIdAndWeekId(areaId, lastWeekId)
                .stream()
                .skip(1)
                .map(this::convertResultByAreaIdForLastWeek)
                .toList();
    }

    private ResultLastWeekDto convertResultByAreaIdForLastWeek(Result result) {
        ResultLastWeekDto resultLastWeekDto = new ResultLastWeekDto();
        resultLastWeekDto.setCriterion(result.getCriterion().getName());
        resultLastWeekDto.setValue(result.getValue());
        return resultLastWeekDto;
    }

    @Override
    public List<ResultTotalFourWeeksDto> getListResultResultTotalFourWeeks(Long areaId) {
//        List<Week> weekListLastFour = weekRepository.findTop4ByOrderByIdDesc();
        return weekRepository.findTop4ByOrderById()
                .stream()
                .map(x->convertResultTotalFourWeeksDto(areaId, x))
                .toList();
    }

    private ResultTotalFourWeeksDto convertResultTotalFourWeeksDto(Long areaId, Week week) {
        ResultTotalFourWeeksDto resultTotalFourWeeksDto = new ResultTotalFourWeeksDto();
        resultTotalFourWeeksDto.setWeek(week.getNumber());
        resultTotalFourWeeksDto.setValue(resultRepository
                .findByAreaIdAndWeekIdAndCriterionId(areaId, week.getId(), 1L)
                .getValue());
        return resultTotalFourWeeksDto;
    }

//    @Override
//    public List<ResultDto2> getListResultsByAreaId2(Long areaId) {
//        return resultRepository.findAllByAreaId(areaId)
//                .stream()
//                .map(x->convertResultToDto2(x, areaId))
//                .toList();
//    }

//    @Override
//    public List<ResultDto2> getListResultsByAreaId2(Long areaId) {
//        List<Result> allResultsByAreaId = resultRepository.findAllByAreaId(areaId);
//        List<ResultDto2> resultDto2List = new ArrayList<>();
//        for (Result result : allResultsByAreaId){
//            ResultDto2 resultDto2 = new ResultDto2();
//            resultDto2.setCriterion(result.getCriterion().getName());
//            List<Result> resultList = resultRepository.findAllByAreaIdAndCriterionId(areaId, result.getCriterion().getId());
//            List<WeekDto> weekDtoList = new ArrayList<>();
//            for (Result result2 : resultList){
//                WeekDto weekDto = new WeekDto();
//                weekDto.setNumber(result2.getWeek().getNumber());
//                weekDto.setValue(result2.getValue());
//                weekDtoList.add(weekDto);
//            }
//            resultDto2.setWeekDtoList(weekDtoList);
//            resultDto2List.add(resultDto2);
//        }
//        return resultDto2List;
//    }

//    @Override
//    public List<ResultLastWeekDto> getListResultsByAreaIdAndWeekNumber(Long areaId, int weekNumber) {
//        return resultRepository.findAllByAreaIdAndWeekNumber(areaId, weekNumber)
//                .stream()
//                .map(this::convertResultWeekToDto)
//                .toList();
//    }

    private ResultDto convertResultToDto(Result result){
        ResultDto resultDto = new ResultDto();
        resultDto.setCriterion(result.getCriterion().getName());
        resultDto.setWeek(result.getWeek().getNumber());
        resultDto.setValue(result.getValue());
        return resultDto;
    }

//    private ResultDto2 convertResultToDto2(Result result, long areaId){
//        ResultDto2 resultDto2 = new ResultDto2();
//        resultDto2.setCriterion(result.getCriterion().getName());
//        resultDto2.setWeekList(getListWeekDto(areaId));
//        return resultDto2;
//    }

//    private List<WeekDto> getListWeekDto(Long areaId){
//        return resultRepository.findAllByAreaId(areaId)
//                .stream()
//                .map(this::convertWeekToDto)
//                .toList();
//
//    }
//
//    private WeekDto convertWeekToDto(Result result) {
//        WeekDto weekDto = new WeekDto();
//        weekDto.setNumber(result.getWeek().getNumber());
//        weekDto.setValue(result.getValue());
//        return weekDto;
//    }

    private ResultLastWeekDto convertResultWeekToDto(Result result){
        ResultLastWeekDto resultWeekDto = new ResultLastWeekDto();
        resultWeekDto.setCriterion(result.getCriterion().getName());
        resultWeekDto.setValue(result.getValue());
        return resultWeekDto;
    }

//    public List<Result> getResultFor2Week(Long areaId){
//        return resultRepository.findTop2ByOrderByAreaIdDesc(areaId);
//    }
}
