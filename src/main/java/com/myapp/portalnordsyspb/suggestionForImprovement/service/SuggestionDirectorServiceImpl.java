package com.myapp.portalnordsyspb.suggestionForImprovement.service;

import com.myapp.portalnordsyspb.suggestionForImprovement.dto.response.StatisticsDirectorsResponseDto;
import com.myapp.portalnordsyspb.suggestionForImprovement.entity.SuggestionDirector;
import com.myapp.portalnordsyspb.suggestionForImprovement.repository.SuggestionDirectorRepository;
import com.myapp.portalnordsyspb.suggestionForImprovement.repository.SuggestionRepository;
import com.myapp.portalnordsyspb.trainingStatistics.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SuggestionDirectorServiceImpl implements SuggestionDirectorService{

    private final SuggestionDirectorRepository suggestionDirectorRepository;
    private final PersonRepository personRepository;
    private final SuggestionRepository suggestionRepository;

    @Override
    public List<StatisticsDirectorsResponseDto> getAllDirectors() {
        List<StatisticsDirectorsResponseDto> resultList = new ArrayList<>();
        List<SuggestionDirector> allDirectors = suggestionDirectorRepository.findAll();
        for (SuggestionDirector director : allDirectors){
            List<String> departments = director.getDepartmentList();
            int registered = 0;
            int implemented = 0;
            int total = 0;

            for (String department : departments){
                registered += suggestionRepository.findRegisteredValuePerDepartment(department);
                implemented += suggestionRepository.findImplementedValuePerDepartment(department);
                total += personRepository.countPersonByDepartmentTrim(department);
            }
            System.out.println(director.getId());
            System.out.println(registered);
            System.out.println(total);
            System.out.println((int) ((float) registered / (float) total * 100));
            resultList.add(new StatisticsDirectorsResponseDto(
                    director.getPosition(),
                    registered,
                    implemented,
                    (int) ((float) registered / (float) total * 100)
            ));
        }
        return resultList;
    }
}
