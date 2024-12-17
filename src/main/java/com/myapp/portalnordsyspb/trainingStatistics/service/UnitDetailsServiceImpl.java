package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.exceptions.ObjectNotFoundException;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.UnitDetailsRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.PersonResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.UnitDetailsResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Person;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Unit;
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
//    private final PersonRepository personRepository;
//    private final UnitService unitService;

    @Override
    public UnitDetailsResponseDto getUnitDetailsByUnitId(long unitId) {
        UnitDetails unitDetails = unitDetailsRepository.findByUnitId(unitId)
                .orElseThrow(() -> new ObjectNotFoundException("Unit_Details not found."));
        List<PersonResponseDto> persons = unitDetails.getPersons()
                .stream().map(this::convertStringToPersonResponseDto)
                .toList();
        return new UnitDetailsResponseDto(
                unitDetails.getUnit().getPeriod().getNumber(),
                unitDetails.getUnit().isCompleted(),
                unitDetails.getDate(),
                unitDetails.getUnit().getValues(),
                unitDetails.getUnit().getDirection().getName(),
                persons);
    }

    private PersonResponseDto convertStringToPersonResponseDto(String personString) {
        String[] result = personString.split(",");
        return new PersonResponseDto(result[0], result[1], result[2]);
    }

//    @Override
//    public void createUnitDetails(UnitDetailsRequestDto unitDetailsRequestDto, long unitId) {
//        UnitDetails unitDetails = new UnitDetails();
//        Unit unit = unitService.getUnitById(unitId);
//        saveUnitDetails(unitDetails, unitDetailsRequestDto, unit);
//    }
//
//    @Override
//    public void updateUnitDetails(UnitDetailsRequestDto unitDetailsRequestDto, long unitDetailsId) {
//        UnitDetails unitDetails = getUnitDetailsByUnitDetailsId(unitDetailsId);
//        Unit unit = unitService.getUnitById(unitDetails.getUnit().getId());
//        saveUnitDetails(unitDetails, unitDetailsRequestDto, unit);
//    }
//
//    @Override
//    public void deleteUnitDetails(long unitDetailsId) {
//        unitDetailsRepository.delete(getUnitDetailsByUnitDetailsId(unitDetailsId));
//    }

//    private void saveUnitDetails(UnitDetails unitDetails, UnitDetailsRequestDto unitDetailsRequestDto, Unit unit){
//        unitDetails.setDate(unitDetailsRequestDto.date());
//        unitDetails.setUnit(unit);
//        unitDetails.setPersons(getPersonsList(unitDetailsRequestDto.tabNumberList()));
//        unitDetailsRepository.save(unitDetails);
//    }

    private UnitDetails getUnitDetailsByUnitDetailsId(long unitDetailsId){
        return unitDetailsRepository.findById(unitDetailsId)
                .orElseThrow(() -> new ObjectNotFoundException("UnitDetails not found."));
    }

//    private List<String> getPersonsList(List<String> tabNumberList){
//        List<String> personList = new ArrayList<>();
//        for (String tabNumber : tabNumberList){
//            Person person = personRepository.findByTabNumber(tabNumber)
//                    .orElseThrow(() -> new ObjectNotFoundException("Person not found."));
//            String[] details = {person.getTabNumber(), person.getFullName(), person.getAppointName()};
//            String personString = String.join(",", details);
//            personList.add(personString);
//        }
//        return personList;
//    }
}
