package com.myapp.portalnordsyspb.service;

import com.myapp.portalnordsyspb.entities.Criterion;
import com.myapp.portalnordsyspb.repositories.CriterionRepository;
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
