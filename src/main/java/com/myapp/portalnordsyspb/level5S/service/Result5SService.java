package com.myapp.portalnordsyspb.level5S.service;

import com.myapp.portalnordsyspb.level5S.dto.response.Result5SiteDto;

public interface Result5SService {

    Result5SiteDto getResult5SiteDtoByMonthIdAndAreaId(Long monthId, Long id);
}
