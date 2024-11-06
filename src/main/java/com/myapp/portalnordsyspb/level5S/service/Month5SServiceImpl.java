package com.myapp.portalnordsyspb.level5S.service;

import com.myapp.portalnordsyspb.exceptions.AreaNotFoundException;
import com.myapp.portalnordsyspb.exceptions.CriterionNotFoundException;
import com.myapp.portalnordsyspb.exceptions.MonthNotFoundException;
import com.myapp.portalnordsyspb.level5S.dto.request.Month5SRequestDto;
import com.myapp.portalnordsyspb.level5S.dto.request.Result5SRequestDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Area5SAndCriterion5SDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Month5SDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Month5SiteDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Result5SForMonthDto;
import com.myapp.portalnordsyspb.level5S.entity.*;
import com.myapp.portalnordsyspb.level5S.repository.Area5SRepository;
import com.myapp.portalnordsyspb.level5S.repository.Criterion5SRepository;
import com.myapp.portalnordsyspb.level5S.repository.Month5SRepository;
import com.myapp.portalnordsyspb.level5S.repository.Result5SRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class Month5SServiceImpl implements Month5SService{

    private final Month5SRepository month5SRepository;
    private final Area5SService area5SService;
    private final Criterion5SRepository criterion5SRepository;
    private final Area5SRepository area5SRepository;
    private final Result5SRepository result5SRepository;
    private final Criterion5SService criterion5SService;

    @Override
//    @Cacheable(value = "Month5SService::getListMonth5Site")
    public List<Month5SiteDto> getListMonth5Site() {
        return month5SRepository.findAll()
                .stream()
                .map(this::convertMonth5SToDto)
                .collect(Collectors.toList()).reversed();
    }

    @Override
    public void createMonth(Month5SRequestDto month5SRequestDto) {
        Month5S month5S = new Month5S();
        List<Result5S> result5SList = month5SRequestDto.results()
                .stream()
                .map(x->convertResult5SRequestDtoToResult5S(x, month5S))
                .toList();
        List<Result5S> constResult5SList = area5SRepository.findAll().stream()
                .map(x->convertArea5SToConstResult5S(x, month5S))
                .toList();
        List<Result5S> finalList = Stream.concat(result5SList.stream(), constResult5SList.stream()).toList();
        month5S.setName(month5SRequestDto.monthAndYear());
        month5S.setResultList(finalList);
        month5SRepository.save(month5S);
    }

    @Override
    public void updateMonth(Month5SRequestDto month5SRequestDto, long monthId) {
        Month5S month5S = month5SRepository.findById(monthId)
                .orElseThrow(()-> new MonthNotFoundException("Month not found"));
        for (Result5SRequestDto resultDto : month5SRequestDto.results()){
            Result5S result5S = result5SRepository.findByAreaIdAndMonthIdAndCriterionId(
                    resultDto.areaId(), monthId, resultDto.criterionId());
            result5S.setValue(resultDto.value());
            result5SRepository.save(result5S);
        }
        month5S.setName(month5SRequestDto.monthAndYear());
        month5SRepository.save(month5S);
    }

    private Result5S convertArea5SToConstResult5S(Area5S area5S, Month5S month5S) {
        Result5S result5S = new Result5S();
        Criterion5S criterion5S = criterion5SRepository.findById(3L)
                .orElseThrow(() -> new CriterionNotFoundException("Критерий не найден!"));
        result5S.setArea(area5S);
        result5S.setMonth(month5S);
        result5S.setCriterion(criterion5S);
        result5S.setValue(area5S.getMaxvalue().getValue());
        return result5S;
    }


    private Result5S convertResult5SRequestDtoToResult5S(Result5SRequestDto result5SRequestDto, Month5S month5S) {
        Result5S result5S = new Result5S();
        Area5S area5S = area5SRepository.findById(result5SRequestDto.areaId())
                .orElseThrow(() -> new AreaNotFoundException("Цех не найден!"));
        Criterion5S criterion5S = criterion5SRepository.findById(result5SRequestDto.criterionId())
                .orElseThrow(() -> new CriterionNotFoundException("Критерий не найден!"));
        result5S.setArea(area5S);
        result5S.setMonth(month5S);
        result5S.setCriterion(criterion5S);
        result5S.setValue(result5SRequestDto.value());
        return result5S;
    }

    @Override
    public void deleteMonth(long monthId) {
        Month5S month5S = month5SRepository.findById(monthId)
                .orElseThrow(()->new MonthNotFoundException("Month not found"));
        month5SRepository.delete(month5S);
    }

    @Override
    public Area5SAndCriterion5SDto getListArea5SDtoAndCriterion5SDto() {
        return new Area5SAndCriterion5SDto(
                area5SService.getListArea5SDto(),
                criterion5SService.getListCriterion5SDto()
        );

    }

    @Override
    public List<Month5SDto> getAllMonth5sDto() {
        return month5SRepository.findAll().stream().map(this::convertMonth5STomonth5sDto)
                .toList();
    }

    @Override
    public List<Result5SForMonthDto> getAllResult5SForMonthDto(long monthId) {
        return result5SRepository.findAllByMonthIdAndCriterionIdLess3(monthId)
                .stream()
                .map(this::convertResult5SToResult5SForMonthDto)
                .toList();
    }

    private Result5SForMonthDto convertResult5SToResult5SForMonthDto(Result5S result5S) {
        return new Result5SForMonthDto(
                result5S.getArea().getId(),
                result5S.getCriterion().getId(),
                result5S.getValue()
        );
    }

    private Month5SDto convertMonth5STomonth5sDto(Month5S month5S) {
        return new Month5SDto(month5S.getId(), month5S.getName());
    }

    private Month5SiteDto convertMonth5SToDto(Month5S month5S) {
        return new Month5SiteDto(
                month5S.getName(),
                area5SService.getAllArea5SDto(month5S.getId())
        );
    }
}
