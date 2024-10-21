package com.myapp.portalnordsyspb.level5S.service;

import com.myapp.portalnordsyspb.level5S.dto.response.Criterion5SDto;
import com.myapp.portalnordsyspb.level5S.entity.Criterion5S;
import com.myapp.portalnordsyspb.level5S.repository.Criterion5SRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class criterion5SServiceImpl implements Criterion5SService {

    private final Criterion5SRepository criterion5SRepository;

    @Override
    public List<Criterion5SDto> getListCriterion5SDto() {
        return criterion5SRepository.findAll()
                .stream().map(this::convertCriterion5SToCriterion5SDto)
                .collect(Collectors.toList());
    }

    private Criterion5SDto convertCriterion5SToCriterion5SDto(Criterion5S criterion5S) {
        return new Criterion5SDto(criterion5S.getId(), criterion5S.getName());
    }
}
