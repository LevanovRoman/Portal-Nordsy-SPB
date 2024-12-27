package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.exceptions.ObjectNotFoundException;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.DiagramResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.PersonResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.UnitDetailsResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Unit;
import com.myapp.portalnordsyspb.trainingStatistics.entity.UnitDetails;
import com.myapp.portalnordsyspb.trainingStatistics.repository.UnitDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnitDetailsServiceImpl implements UnitDetailsService{

    private final UnitDetailsRepository unitDetailsRepository;
    private final UnitService unitService;

    @Override
    public UnitDetailsResponseDto getUnitDetailsByUnitId(long unitId) {
        Optional<UnitDetails> unitDetails = unitDetailsRepository.findByUnitId(unitId);
        if (unitDetails.isEmpty()){
            Unit unit = unitService.getUnitById(unitId);
            return new UnitDetailsResponseDto(
                    unit.getPeriod().getNumber(),
                    false,
                    LocalDate.now(),
                    unit.getValues(),
                    unit.getDirection().getName(),
                    new ArrayList<>(),
                    new ArrayList<>());
        } else {
            UnitDetails unitDetailsExist = unitDetails.get();
            List<PersonResponseDto> persons = unitDetailsExist.getPersons()
                    .stream().map(this::convertStringToPersonResponseDto)
                    .toList();
            List<String> tubNumbersList = persons.stream().map(PersonResponseDto::tabNumber).toList();
            return new UnitDetailsResponseDto(
                    unitDetailsExist.getUnit().getPeriod().getNumber(),
                    unitDetailsExist.getUnit().isCompleted(),
                    unitDetailsExist.getDate(),
                    unitDetailsExist.getUnit().getValues(),
                    unitDetailsExist.getUnit().getDirection().getName(),
                    persons,
                    tubNumbersList);
        }

    }

    @Override
    public List<DiagramResponseDto> getDataForDiagram(int year) {
        List<DiagramResponseDto> resultList = new ArrayList<>();
        int counter = 0;
        for (int i = 1; i <=12 ; i++) {
            int quantity = getQuantityPersonsPerMonth(i, year);
            counter += quantity;
            resultList.add(new DiagramResponseDto(
                    i,
                    i,
                    quantity,
                    counter
            ));
        }
        return resultList;
    }

    private int getQuantityPersonsPerMonth(int monthNumber, int year) {
        return unitDetailsRepository.countPersonsForMonth(monthNumber, year);
    }

    private PersonResponseDto convertStringToPersonResponseDto(String personString) {
        String[] result = personString.split(",");
        return new PersonResponseDto(result[0], result[1], result[2]);
    }

    private UnitDetails getUnitDetailsByUnitDetailsId(long unitDetailsId){
        return unitDetailsRepository.findById(unitDetailsId)
                .orElseThrow(() -> new ObjectNotFoundException("UnitDetails not found."));
    }
}
