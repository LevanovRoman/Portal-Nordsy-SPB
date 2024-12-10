package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.exceptions.ObjectNotFoundException;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.UnitDetailsRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.PersonResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.UnitDetailsResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Person;
import com.myapp.portalnordsyspb.trainingStatistics.entity.UnitDetails;
import com.myapp.portalnordsyspb.trainingStatistics.repository.PersonRepository;
import com.myapp.portalnordsyspb.trainingStatistics.repository.UnitDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitDetailsServiceImpl implements UnitDetailsService{

    private final UnitDetailsRepository unitDetailsRepository;
    private final PersonRepository personRepository;
    private final UnitService unitService;

    @Override
    public UnitDetailsResponseDto getUnitDetailsByUnitId(long unitId) {
        UnitDetails unitDetails = unitDetailsRepository.findByUnitId(unitId)
                .orElseThrow(() -> new ObjectNotFoundException("Unit_Details not found."));
        List<PersonResponseDto> persons = unitDetails.getPersons()
                .stream().map(this::convertStringToPersonResponseDto)
                .toList();
        return new UnitDetailsResponseDto(
                unitDetails.getUnit().getPeriod().getNumber(),
                unitDetails.getDate(),
                unitDetails.getUnit().getValue(),
                unitDetails.getUnit().getDirection().getName(),
                persons);
    }

    private PersonResponseDto convertStringToPersonResponseDto(String personString) {
        String[] result = personString.split(",");
        return new PersonResponseDto(result[0], result[1], result[2]);
    }

    @Override
    public void createUnitDetails(UnitDetailsRequestDto unitDetailsRequestDto, long unitId) {
        UnitDetails unitDetails = new UnitDetails();
        List<String> tabNumberList = unitDetailsRequestDto.tabNumberList();
        List<String> personList = new ArrayList<>();
        for (String tabNumber : tabNumberList){
            Person person = personRepository.findByTabNumber(tabNumber)
                    .orElseThrow(() -> new ObjectNotFoundException("Person not found."));
            String[] details = {person.getTabNumber(), person.getFullName(), person.getAppointName()};
            String personString = String.join(",", details);
            personList.add(personString);
        }
        unitDetails.setDate(unitDetailsRequestDto.date());
        unitDetails.setUnit(unitService.getUnitById(unitId));
        unitDetails.setPersons(personList);
        unitDetailsRepository.save(unitDetails);
    }

    @Override
    public void updateUnitDetails(UnitDetailsRequestDto unitDetailsRequestDto, long unitDetailsId) {

    }

    @Override
    public void deleteUnitDetails(long unitDetailsId) {

    }
}
