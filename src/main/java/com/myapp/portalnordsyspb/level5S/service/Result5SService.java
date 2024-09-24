package com.myapp.portalnordsyspb.level5S.service;

import com.myapp.portalnordsyspb.level5S.dto.Result5STableDto;

import java.util.List;

public interface Result5SService {
    List<Result5STableDto> getListResultsByAreaIdForLastWeek(Long area_id);
}
