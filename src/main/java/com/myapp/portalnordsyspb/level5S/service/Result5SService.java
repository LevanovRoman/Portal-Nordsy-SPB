package com.myapp.portalnordsyspb.level5S.service;

import com.myapp.portalnordsyspb.level5S.dto.Result5STableDto;
import com.myapp.portalnordsyspb.level5S.dto.test.Result5SiteDto;

import java.util.List;

public interface Result5SService {
    List<Result5STableDto> getListResultsByAreaIdForLastWeek(Long area_id);

//    List<Result5SiteDto> getListResult5SiteDtoByMonthId(Long month_id, Long area_id);

    Result5SiteDto getResult5SiteDtoByMonthIdAndAreaId(Long monthId, Long id);
}
