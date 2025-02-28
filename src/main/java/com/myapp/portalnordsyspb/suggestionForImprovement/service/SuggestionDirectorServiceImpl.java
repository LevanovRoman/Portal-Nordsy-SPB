package com.myapp.portalnordsyspb.suggestionForImprovement.service;

import com.myapp.portalnordsyspb.suggestionForImprovement.dto.response.StatisticsDirectorsResponseDto;
import com.myapp.portalnordsyspb.suggestionForImprovement.dto.response.StatisticsInvolvedForDiagram;
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

    private final List<String> deparmentsList = List.of("016", "020", "035", "036", "040", "041", "047");

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
            resultList.add(new StatisticsDirectorsResponseDto(
                    director.getPosition(),
                    registered,
                    implemented,
                    (int) ((float) registered / (float) total * 100)
            ));
        }
        return resultList;
    }

    @Override
    public List<StatisticsInvolvedForDiagram> getDataForDiagramInvolvedPerDepartment() {
        return deparmentsList.stream().map(el -> convertDepartmentToStatisticsInvolved(el, deparmentsList.indexOf(el))).toList();
    }

    private StatisticsInvolvedForDiagram convertDepartmentToStatisticsInvolved(String department, int index) {
        int registered = suggestionRepository.findRegisteredValuePerDepartment(department);
        int total = personRepository.countPersonByDepartmentTrim(department);
        int result = (int) ((float) registered / (float) total * 100);
        return new StatisticsInvolvedForDiagram(index, department,
                result);
    }
}
