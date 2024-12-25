package com.myapp.portalnordsyspb.suggestionForImprovement.repository;

import com.myapp.portalnordsyspb.suggestionForImprovement.entity.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
}
