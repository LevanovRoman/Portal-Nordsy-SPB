package com.myapp.portalnordsyspb.level5S.service;

import com.myapp.portalnordsyspb.level5S.dto.Result5STableDto;
import com.myapp.portalnordsyspb.level5S.entity.Result5S;
import com.myapp.portalnordsyspb.level5S.repository.Result5SRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Result5SServiceImpl implements Result5SService{

    private final Result5SRepository result5SRepository;

    @Override
//    @Cacheable(value = "myCache", key = "#area_id")
    public List<Result5STableDto> getListResultsByAreaIdForLastWeek(Long area_id) {
        System.out.println(result5SRepository.findAllByAreaId(area_id).stream().toList());
        return result5SRepository.findAllByAreaId(area_id)
                .stream()
                .map(this::convertResult5SByAreaId)
                .collect(Collectors.toList());
    }

    private Result5STableDto convertResult5SByAreaId(Result5S result5S) {
        return new Result5STableDto(
                result5S.getMonth().getName(),
                result5S.getCriterion().getName(),
                result5S.getValue()
        );
    }
}
