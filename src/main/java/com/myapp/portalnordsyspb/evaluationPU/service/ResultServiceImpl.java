package com.myapp.portalnordsyspb.evaluationPU.service;

import com.myapp.portalnordsyspb.evaluationPU.dto.requestDto.ResultRequestDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.ResultTableFourWeeksDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.ResultTableLastWeekDto;
import com.myapp.portalnordsyspb.evaluationPU.entity.Area;
import com.myapp.portalnordsyspb.evaluationPU.entity.Criterion;
import com.myapp.portalnordsyspb.evaluationPU.entity.Result;
import com.myapp.portalnordsyspb.evaluationPU.entity.Week;
import com.myapp.portalnordsyspb.evaluationPU.exceptions.CriterionNotFoundException;
import com.myapp.portalnordsyspb.evaluationPU.repository.AreaRepository;
import com.myapp.portalnordsyspb.evaluationPU.repository.ResultRepository;
import com.myapp.portalnordsyspb.evaluationPU.exceptions.AreaNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService{

    private final ResultRepository resultRepository;
    private final WeekService weekService;
    private final CriterionService criterionService;
    private final AreaRepository areaRepository;

    @Override
//    @Cacheable(value = "ResultService::getListResultsByAreaIdForLastWeek", key = "#areaId")
    public List<ResultTableLastWeekDto> getListResultsByAreaIdForLastWeek(Long areaId) {
        long lastWeekId = weekService.getTopByOrderByIdDesc().getId();
        return resultRepository.findAllByAreaIdAndWeekId(areaId, lastWeekId)
                .stream()
                .skip(1)
                .map(this::convertResultByAreaIdForLastWeek)
                .collect(Collectors.toList());
    }

    @Override
//    @Cacheable(value = "ResultService::getListResultResultTotalFourWeeks", key = "#area_id")
    public List<ResultTableFourWeeksDto> getListResultResultTotalFourWeeks(Long area_id) {
        return weekService.getTop4ByOrderByIdDesc()
                .stream()
                .map(x->convertResultTotalFourWeeksDto(area_id, x))
                .collect(Collectors.toList()).reversed();
    }

    @Override
    @Transactional
//    @Caching(cacheable = {
//            @Cacheable(value = "ResultService::getListResultsByAreaIdForLastWeek"),
//            @Cacheable(value = "ResultService::getListResultResultTotalFourWeeks")
//    })
    public void addResultsForWeek(List<ResultRequestDto> resultRequestDtoList) {
        Week weekLast = weekService.createWeek();
        long weekId = weekLast.getId();
        List<Result> resultList = resultRequestDtoList.stream()
                .map(result -> convertResultDtoToResult(result, weekId))
                .collect(Collectors.toList());
        resultRepository.saveAll(resultList);
    }

    private ResultTableLastWeekDto convertResultByAreaIdForLastWeek(Result result) {
        return new ResultTableLastWeekDto(
                result.getCriterion().getName(),
                result.getValue()
        );
    }

    private Result convertResultDtoToResult(ResultRequestDto resultRequestDto, Long weekId) {
        Result result = new Result();
        Area area = areaRepository.findById(resultRequestDto.areaId())
                .orElseThrow(() -> new AreaNotFoundException("Цех не найден!"));
        Criterion criterion = criterionService.getCriterionById(resultRequestDto.criterionId())
                .orElseThrow(() -> new CriterionNotFoundException("Критерий не найден!"));
        Week week = weekService.getWeekById(weekId)
                .orElseThrow(() -> new CriterionNotFoundException("Неделя не найдена!"));
        result.setArea(area);
        result.setCriterion(criterion);
        result.setWeek(week);
        result.setValue(resultRequestDto.value());
        return result;
    }

    private ResultTableFourWeeksDto convertResultTotalFourWeeksDto(Long areaId, Week week) {
        return new ResultTableFourWeeksDto(
                week.getNumber(),
                resultRepository
                        .findByAreaIdAndWeekIdAndCriterionId(areaId, week.getId(), 1L)
                        .getValue()
        );
    }

//    private void saveResult(ResultRequestDto resultRequestDto, Long weekId) {
//        Result result = new Result();
//        Optional<Area> area = areaRepository.findById(resultRequestDto.areaId());
//        Optional<Criterion> criterion = criterionRepository.findById(resultRequestDto.criterionId());
//        Optional<Week> week = weekRepository.findById(weekId);
//        result.setArea(area.get());
//        result.setCriterion(criterion.get());
//        result.setWeek(week.get());
//        result.setValue(resultRequestDto.value());
//        resultRepository.save(result);
//    }


//    @Override
//    public List<Result> createResultWeekSet(List<ResultRequestDto> resultRequestDto) {
//        return resultRequestDto.stream()
//                .map(this::convertResultRequestDto)
//                .toList();
//    }

//    private Result convertResultRequestDto(ResultRequestDto resultRequestDto) {
//        Result result = new Result();
//        Optional<Criterion> criterion = criterionRepository.findById(resultRequestDto.criterionId());
//        result.setCriterion(criterion.get());
//
//        return result;
//    }

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

//    private ResultDto convertResultToDto(Result result){
//        ResultDto resultDto = new ResultDto();
//        resultDto.setCriterion(result.getCriterion().getName());
//        resultDto.setWeek(result.getWeek().getNumber());
//        resultDto.setValue(result.getValue());
//        return resultDto;
//    }

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

//    private ResultLastWeekDto convertResultWeekToDto(Result result){
//        ResultLastWeekDto resultWeekDto = new ResultLastWeekDto();
//        resultWeekDto.setCriterion(result.getCriterion().getName());
//        resultWeekDto.setValue(result.getValue());
//        return resultWeekDto;
//    }

//    public List<Result> getResultFor2Week(Long areaId){
//        return resultRepository.findTop2ByOrderByAreaIdDesc(areaId);
//    }
}
