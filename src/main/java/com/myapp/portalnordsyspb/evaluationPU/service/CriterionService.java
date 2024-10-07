package com.myapp.portalnordsyspb.evaluationPU.service;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.CriterionDto;
import com.myapp.portalnordsyspb.evaluationPU.entity.Criterion;

import java.util.List;
import java.util.Optional;

public interface CriterionService {

    Optional<Criterion> getCriterionById(long criterion_id);

    List<CriterionDto> getCriterionDto();
}
