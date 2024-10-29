package com.myapp.portalnordsyspb.evaluationPU.service;

import com.myapp.portalnordsyspb.evaluationPU.dto.requestDto.ResultRequestDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.requestDto.WeekRequestDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.ResultResponseDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.ResultTableFourWeeksDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.ResultTableLastWeekDto;
import com.myapp.portalnordsyspb.evaluationPU.entity.Area;
import com.myapp.portalnordsyspb.evaluationPU.entity.Criterion;
import com.myapp.portalnordsyspb.evaluationPU.entity.Result;
import com.myapp.portalnordsyspb.evaluationPU.entity.Week;
import com.myapp.portalnordsyspb.evaluationPU.repository.WeekRepository;
import com.myapp.portalnordsyspb.exceptions.CriterionNotFoundException;
import com.myapp.portalnordsyspb.evaluationPU.repository.AreaRepository;
import com.myapp.portalnordsyspb.evaluationPU.repository.ResultRepository;
import com.myapp.portalnordsyspb.exceptions.AreaNotFoundException;
import com.myapp.portalnordsyspb.exceptions.WeekNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService{

    private final ResultRepository resultRepository;
    private final WeekService weekService;
    private final CriterionService criterionService;
    private final AreaRepository areaRepository;
    private final WeekRepository weekRepository;

    @Override
    public List<ResultTableLastWeekDto> getListResultsByAreaIdForLastWeek(Long areaId) {
        long lastWeekId = weekService.getTopByOrderByIdDesc().getId();
        return resultRepository.findAllByAreaIdAndWeekId(areaId, lastWeekId)
                .stream()
                .filter(x -> x.getCriterion().getId() != 1)
                .sorted((o1, o2)-> o1.getCriterion().getId().compareTo(o2.getCriterion().getId()))
                .map(this::convertResultByAreaIdForLastWeek)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResultTableFourWeeksDto> getListResultResultTotalFourWeeks(Long area_id) {
        return weekService.getTop4ByOrderByIdDesc()
                .stream()
                .map(x->convertResultTotalFourWeeksDto(area_id, x))
                .collect(Collectors.toList()).reversed();
    }

//    @Override
//    @Transactional
//    public void addResultsForWeek(List<ResultRequestDto> resultRequestDtoList) {
//        Week weekLast = weekService.createWeek();
//        long weekId = weekLast.getId();
//        List<Result> resultList = resultRequestDtoList.stream()
//                .map(result -> convertResultDtoToResult(result, weekId))
//                .collect(Collectors.toList());
//        resultRepository.saveAll(resultList);
//    }

    @Override
    public void createResultsForWeek(WeekRequestDto weekRequestDto) {
        Week week = new Week();
        week.setWeekName(weekRequestDto.week());
        List<Result> resultList = weekRequestDto.results().stream()
                .map(result -> convertResultDtoToResult(result, week))
                .collect(Collectors.toList());
        resultRepository.saveAll(resultList);
        weekRepository.save(week);
    }

//    @Override
//    public void updateResultsForWeek(List<ResultRequestDto> resultRequestDtoList, long weekId) {
//        for (ResultRequestDto resultDto : resultRequestDtoList){
//            Result result = resultRepository.findByAreaIdAndWeekIdAndCriterionId(
//                    resultDto.areaId(), weekId, resultDto.criterionId());
//            result.setValue(resultDto.value());
//            resultRepository.save(result);
//        }
//    }

    @Override
    public void updateResultsForWeek(WeekRequestDto weekRequestDto, long weekId) {
        Week weekUpdated = weekRepository.findById(weekId).orElseThrow(()->new WeekNotFoundException("Week not found."));
        weekUpdated.setWeekName(weekRequestDto.week());
        for (ResultRequestDto resultDto : weekRequestDto.results()){
            Result result = resultRepository.findByAreaIdAndWeekIdAndCriterionId(
                    resultDto.areaId(), weekId, resultDto.criterionId());
            result.setValue(resultDto.value());
            resultRepository.save(result);
        }
        weekRepository.save(weekUpdated);
    }

    @Override
    public void deleteResultForWeek(long weekId) {
        Week week = weekRepository.findById(weekId)
                .orElseThrow(()-> new WeekNotFoundException("Week with id = " + weekId + " not found"));
        weekRepository.delete(week);
    }

    @Override
    public List<ResultResponseDto> getListResultResponseDtoForWeek(Long weekId) {
        List<Result> resultList = resultRepository.findAllByWeekId(weekId);
        return resultList.stream().map(this::convertResultToResultResponseDto)
                .collect(Collectors.toList());
    }

    private ResultResponseDto convertResultToResultResponseDto(Result result) {
        return new ResultResponseDto(
                result.getArea().getId(),
                result.getCriterion().getId(),
                result.getValue()
        );
    }

    private ResultTableLastWeekDto convertResultByAreaIdForLastWeek(Result result) {
        return new ResultTableLastWeekDto(
                result.getCriterion().getName(),
                result.getValue()
        );
    }

    private Result convertResultDtoToResult(ResultRequestDto resultRequestDto, Week week) {
        Result result = new Result();
        Area area = areaRepository.findById(resultRequestDto.areaId())
                .orElseThrow(() -> new AreaNotFoundException("Цех не найден!"));
        Criterion criterion = criterionService.getCriterionById(resultRequestDto.criterionId());
        result.setArea(area);
        result.setCriterion(criterion);
        result.setWeek(week);
        result.setValue(resultRequestDto.value());
        return result;
    }

    private ResultTableFourWeeksDto convertResultTotalFourWeeksDto(Long areaId, Week week) {
        Optional<Result> result = Optional.ofNullable(resultRepository
                .findByAreaIdAndWeekIdAndCriterionId(areaId, week.getId(), 1L));
        if (result.isEmpty()) {
            result = Optional.of(new Result(null, 0,
                    criterionService.getCriterionById(1L),
                    week,
                    areaRepository.findById(areaId).orElseThrow(() -> new AreaNotFoundException("Area not found."))));
            resultRepository.save(result.get());
        }
        return new ResultTableFourWeeksDto(
                week.getWeekName(),
                 result.get().getValue()
        );
    }
}
