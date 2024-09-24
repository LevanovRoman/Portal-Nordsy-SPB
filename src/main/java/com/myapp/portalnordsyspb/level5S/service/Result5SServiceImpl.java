package com.myapp.portalnordsyspb.level5S.service;

import com.myapp.portalnordsyspb.level5S.dto.Result5STableDto;
import com.myapp.portalnordsyspb.level5S.entity.Result5S;
import com.myapp.portalnordsyspb.level5S.repository.Result5SRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Result5SServiceImpl implements Result5SService{

    private final Result5SRepository result5SRepository;

    @Override
    public List<Result5STableDto> getListResultsByAreaIdForLastWeek(Long area_id) {
        System.out.println(result5SRepository.findAllByAreaId(area_id).stream().toList());
        return result5SRepository.findAllByAreaId(area_id)
                .stream()
                .map(this::convertResult5SByAreaId)
                .toList();
    }

    private Result5STableDto convertResult5SByAreaId(Result5S result5S) {
        return new Result5STableDto(
                result5S.getMonth().getName(),
                result5S.getCriterion().getName(),
                result5S.getValue()
        );
    }
}
