package com.myapp.portalnordsyspb.service;

import com.myapp.portalnordsyspb.entities.Criterion;

import java.util.Optional;

public interface CriterionService {

    Optional<Criterion> getCriterionById(long criterion_id);
}
