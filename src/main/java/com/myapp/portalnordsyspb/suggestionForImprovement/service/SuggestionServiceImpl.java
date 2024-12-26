package com.myapp.portalnordsyspb.suggestionForImprovement.service;

import com.myapp.portalnordsyspb.suggestionForImprovement.dto.response.StatisticsResponseDto;
import com.myapp.portalnordsyspb.suggestionForImprovement.dto.response.SuggestionAllResponseDto;
import com.myapp.portalnordsyspb.suggestionForImprovement.entity.Suggestion;
import com.myapp.portalnordsyspb.suggestionForImprovement.repository.SuggestionRepository;
import com.myapp.portalnordsyspb.trainingStatistics.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SuggestionServiceImpl implements SuggestionService{

    private final SuggestionRepository suggestionRepository;
    private final PersonRepository personRepository;

    @Override
    public List<SuggestionAllResponseDto> getAllSuggestion() {
        return suggestionRepository.findAll()
                .stream().map(this::convertSuggestionToSuggestionAllResponseDto).toList();
    }

    @Override
    public StatisticsResponseDto getStatistics() {
        int totalQuantityPersons = (int) personRepository.count();
        int registered = suggestionRepository.findRegisteredValueForStatistics();
        return new StatisticsResponseDto(
                registered,
                suggestionRepository.findAgreedValueForStatistics(),
                suggestionRepository.findImplementedValueForStatistics(),
                registered / totalQuantityPersons * 100,
                suggestionRepository.findCategoryValue("Бытовые улучшения"),
                suggestionRepository.findCategoryValue("Эргономика"),
                suggestionRepository.findCategoryValue("Информационные / IT-процессы"),
                suggestionRepository.findCategoryValue("Охрана труда"),
                suggestionRepository.findCategoryValue("Производственные процессы и технология")
        );
    }

    private SuggestionAllResponseDto convertSuggestionToSuggestionAllResponseDto(Suggestion suggestion) {
        return new SuggestionAllResponseDto(
                suggestion.getId(),
                suggestion.getTitle(),
                suggestion.getNumberAndDateRegistration(),
                suggestion.getDepartment(),
                suggestion.getAuthor(),
                ! suggestion.getNumberAndDateRegistration().isEmpty(),
                suggestion.getAgreed().equals("Согласован"),
                suggestion.getImplemented().equals("1"),
                trimDate(suggestion.getDateImplementation())
        );
    }

    private String trimDate(String date){
        int index = date.indexOf('T');
        if (index != -1) {
            // Обрезать строку до первого символа 'Т'
            return date.substring(0, index);
        } else {
            return "";
//            throw new ObjectNotFoundException("Символ 'Т' не найден в строке.");
        }
    }
}
