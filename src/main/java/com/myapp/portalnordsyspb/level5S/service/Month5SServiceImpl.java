package com.myapp.portalnordsyspb.level5S.service;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.exceptions.AreaNotFoundException;
import com.myapp.portalnordsyspb.exceptions.CriterionNotFoundException;
import com.myapp.portalnordsyspb.exceptions.MonthNotFoundException;
import com.myapp.portalnordsyspb.exceptions.PhotoNotFoundException;
import com.myapp.portalnordsyspb.level5S.dto.request.Month5SRequestDto;
import com.myapp.portalnordsyspb.level5S.dto.request.Result5SRequestDto;
import com.myapp.portalnordsyspb.level5S.dto.response.Month5SiteDto;
import com.myapp.portalnordsyspb.level5S.entity.Area5S;
import com.myapp.portalnordsyspb.level5S.entity.Criterion5S;
import com.myapp.portalnordsyspb.level5S.entity.Month5S;
import com.myapp.portalnordsyspb.level5S.entity.Result5S;
import com.myapp.portalnordsyspb.level5S.repository.Area5SRepository;
import com.myapp.portalnordsyspb.level5S.repository.Criterion5SRepository;
import com.myapp.portalnordsyspb.level5S.repository.Month5SRepository;
import com.myapp.portalnordsyspb.level5S.repository.Result5SRepository;
import com.myapp.portalnordsyspb.news.entity.News;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Month5SServiceImpl implements Month5SService{

    private final Month5SRepository month5SRepository;
    private final Area5SService area5SService;
    private final Criterion5SRepository criterion5SRepository;
    private final Area5SRepository area5SRepository;
    private final Result5SRepository result5SRepository;

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
        month5S.setName(month5SRequestDto.name());
        month5S.setResultList(result5SList);
        month5SRepository.save(month5S);
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
    public void updateMonth(List<Result5SRequestDto> result5SRequestDtoList, long monthId) {
        for (Result5SRequestDto resultDto : result5SRequestDtoList){
            Result5S result5S = result5SRepository.findByAreaIdAndMonthIdAndCriterionId(
                resultDto.areaId(), monthId, resultDto.criterionId());
            result5S.setValue(resultDto.value());
            result5SRepository.save(result5S);
        }
    }

    @Override
    public void deleteMonth(long monthId) {
        Month5S month5S = month5SRepository.findById(monthId)
                .orElseThrow(()->new MonthNotFoundException("Month not found"));
        month5SRepository.delete(month5S);
    }

    private Month5SiteDto convertMonth5SToDto(Month5S month5S) {
        return new Month5SiteDto(
                month5S.getName(),
                area5SService.getAllArea5SDto(month5S.getId())
        );
    }
}
