package com.myapp.portalnordsyspb.level5S.service;

import com.myapp.portalnordsyspb.level5S.dto.response.Result5SiteDto;
import com.myapp.portalnordsyspb.level5S.repository.Result5SRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Result5SServiceImpl implements Result5SService{

    private final Result5SRepository result5SRepository;

    @Override
    public Result5SiteDto getResult5SiteDtoByMonthIdAndAreaId(Long month_id, Long area_id) {
        return new Result5SiteDto(
                result5SRepository.findByAreaIdAndMonthIdAndCriterionId(area_id, month_id, 1L).getValue(),
                result5SRepository.findByAreaIdAndMonthIdAndCriterionId(area_id, month_id, 2L).getValue(),
                result5SRepository.findByAreaIdAndMonthIdAndCriterionId(area_id, month_id, 3L).getValue()
        );
    }
}
