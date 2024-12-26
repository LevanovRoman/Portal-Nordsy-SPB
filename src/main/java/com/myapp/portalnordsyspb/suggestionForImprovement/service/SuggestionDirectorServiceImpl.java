package com.myapp.portalnordsyspb.suggestionForImprovement.service;

import com.myapp.portalnordsyspb.suggestionForImprovement.dto.response.SuggestionDirectorResponseDto;
import com.myapp.portalnordsyspb.suggestionForImprovement.entity.SuggestionDirector;
import com.myapp.portalnordsyspb.suggestionForImprovement.repository.SuggestionDirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SuggestionDirectorServiceImpl implements SuggestionDirectorService{

    private final SuggestionDirectorRepository suggestionDirectorRepository;


    @Override
    public List<SuggestionDirectorResponseDto> getAllSuggestionDirectors() {
        return suggestionDirectorRepository.findAll()
                .stream().map(this::convertSuggestionDirectorToSuggestionDirectorResponseDto)
                .toList();
    }

    private SuggestionDirectorResponseDto convertSuggestionDirectorToSuggestionDirectorResponseDto(
            SuggestionDirector director) {
        return new SuggestionDirectorResponseDto(
                director.getPosition(),
                director.getRegistered(),
                director.getImplemented(),
                director.getInclusion()
        );
    }
}
