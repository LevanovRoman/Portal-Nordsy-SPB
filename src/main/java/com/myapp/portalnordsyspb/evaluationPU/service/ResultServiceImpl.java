package com.myapp.portalnordsyspb.evaluationPU.service;

import com.myapp.portalnordsyspb.evaluationPU.dto.requestDto.ResultRequestDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.ResultResponseDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.ResultTableFourWeeksDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.ResultTableLastWeekDto;
import com.myapp.portalnordsyspb.evaluationPU.entity.Area;
import com.myapp.portalnordsyspb.evaluationPU.entity.Criterion;
import com.myapp.portalnordsyspb.evaluationPU.entity.Result;
import com.myapp.portalnordsyspb.evaluationPU.entity.Week;
import com.myapp.portalnordsyspb.exceptions.CriterionNotFoundException;
import com.myapp.portalnordsyspb.evaluationPU.repository.AreaRepository;
import com.myapp.portalnordsyspb.evaluationPU.repository.ResultRepository;
import com.myapp.portalnordsyspb.exceptions.AreaNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService{

    private final ResultRepository resultRepository;
    private final WeekService weekService;
    private final CriterionService criterionService;
    private final AreaRepository areaRepository;
//    Logger log = LoggerFactory.getLogger(PortalNordsySpbApplication.class);

    @Override
//    @Cacheable(value = "ResultService::getListResultsByAreaIdForLastWeek")
    public List<ResultTableLastWeekDto> getListResultsByAreaIdForLastWeek(Long areaId) {
//        log.debug("Response {}", areaId);
        long lastWeekId = weekService.getTopByOrderByIdDesc().getId();
//        log.debug("Request {}", areaId);
        return resultRepository.findAllByAreaIdAndWeekId(areaId, lastWeekId)
                .stream()
                .filter(x -> x.getCriterion().getId() != 1)
                .sorted((o1, o2)-> o1.getCriterion().getId().compareTo(o2.getCriterion().getId()))
                .map(this::convertResultByAreaIdForLastWeek)
                .collect(Collectors.toList());
    }

    @Override
//    @Cacheable(value = "ResultService::getListResultResultTotalFourWeeks")
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
    @CacheEvict(value = "ResultService::getListResultsByAreaIdForLastWeek", allEntries = true)
    public void addResultsForWeek(List<ResultRequestDto> resultRequestDtoList) {
        Week weekLast = weekService.createWeek();
        long weekId = weekLast.getId();
        List<Result> resultList = resultRequestDtoList.stream()
                .map(result -> convertResultDtoToResult(result, weekId))
                .collect(Collectors.toList());
        resultRepository.saveAll(resultList);
    }

    @Override
    public void updateResultsForWeek(List<ResultRequestDto> resultRequestDtoList, long weekId) {
        for (ResultRequestDto resultDto : resultRequestDtoList){
            Result result = resultRepository.findByAreaIdAndWeekIdAndCriterionId(
                    resultDto.areaId(), weekId, resultDto.criterionId());
            result.setValue(resultDto.value());
            resultRepository.save(result);
        }
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
}
