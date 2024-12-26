package com.myapp.portalnordsyspb.suggestionForImprovement.repository;

import com.myapp.portalnordsyspb.suggestionForImprovement.entity.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {

    @Query(value = "SELECT COUNT(*) FROM suggestion WHERE category ILIKE :category", nativeQuery = true)
    int findCategoryValue(@Param("category") String category);

    @Query(value = "SELECT COUNT(*) FROM suggestion WHERE suggestion.number_and_date_registration IS NOT NULL\n" +
            "                                  AND suggestion.number_and_date_registration <> ''", nativeQuery = true)
    int findRegisteredValueForStatistics();

    @Query(value = "SELECT COUNT(*) FROM suggestion WHERE agreed ILIKE 'Согласован'", nativeQuery = true)
    int findAgreedValueForStatistics();

    @Query(value = "SELECT COUNT(*) FROM suggestion WHERE implemented ILIKE '1'", nativeQuery = true)
    int findImplementedValueForStatistics();

    @Query(value = "SELECT COUNT(*) FROM suggestion WHERE suggestion.number_and_date_registration IS NOT NULL\n" +
            " AND suggestion.number_and_date_registration <> '' AND department LIKE :department", nativeQuery = true)
    int findRegisteredValuePerDepartment(@Param("department") String department);

    @Query(value = "SELECT COUNT(*) FROM suggestion WHERE implemented ILIKE '1' AND department LIKE :department", nativeQuery = true)
    int findImplementedValuePerDepartment(@Param("department") String department);
}
