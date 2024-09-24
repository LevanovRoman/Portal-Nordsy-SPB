package com.myapp.portalnordsyspb.evaluationPU.service;

import com.myapp.portalnordsyspb.evaluationPU.entity.Criterion;
import com.myapp.portalnordsyspb.evaluationPU.repository.CriterionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CriterionServiceImpl implements CriterionService{

    private final CriterionRepository criterionRepository;

    @Override
    public Optional<Criterion> getCriterionById(long criterion_id) {
        return criterionRepository.findById(criterion_id);
    }
}
