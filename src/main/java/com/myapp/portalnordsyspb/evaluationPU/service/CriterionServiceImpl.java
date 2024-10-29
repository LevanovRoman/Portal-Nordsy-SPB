package com.myapp.portalnordsyspb.evaluationPU.service;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.CriterionDto;
import com.myapp.portalnordsyspb.evaluationPU.entity.Criterion;
import com.myapp.portalnordsyspb.evaluationPU.repository.CriterionRepository;
import com.myapp.portalnordsyspb.exceptions.CriterionNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CriterionServiceImpl implements CriterionService{

    private final CriterionRepository criterionRepository;

    @Override
    public Criterion getCriterionById(long criterion_id) {
        return criterionRepository
                .findById(criterion_id)
                .orElseThrow(()-> new CriterionNotFoundException("Criterion not found."));
    }

    @Override
    public List<CriterionDto> getCriterionDto() {
        return criterionRepository.findAll()
                .stream().map(this::convertCriterionToCriterionDto)
                .collect(Collectors.toList());
    }

    private CriterionDto convertCriterionToCriterionDto(Criterion criterion) {
        return new CriterionDto(
                criterion.getId(),
                criterion.getName()
        );
    }
}
