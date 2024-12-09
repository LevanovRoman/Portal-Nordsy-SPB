package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.exceptions.ObjectNotFoundException;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.UnitDetailsRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.UnitDetailsResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.entity.UnitDetails;
import com.myapp.portalnordsyspb.trainingStatistics.repository.UnitDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UnitDetailsServiceImpl implements UnitDetailsService{

    private final UnitDetailsRepository unitDetailsRepository;

    @Override
    public UnitDetailsResponseDto getUnitDetailsByUnitId(long unitId) {
        UnitDetails unitDetails = unitDetailsRepository.findByUnitId(unitId)
                .orElseThrow(() -> new ObjectNotFoundException("Unit_Details not found."));
        return new UnitDetailsResponseDto(
                unitDetails.getUnit().getPeriod().getNumber(),
                unitDetails.getDate(),
                unitDetails.getUnit().getValue(),
                unitDetails.getUnit().getDirection().getName(),
                unitDetails.getTabNumber(),
                unitDetails.getFullName(),
                unitDetails.getPosition());
    }

    @Override
    public void createUnitDetails(UnitDetailsRequestDto unitDetailsRequestDto) {

    }

    @Override
    public void updateUnitDetails(UnitDetailsRequestDto unitDetailsRequestDto, long unitDetailsId) {

    }

    @Override
    public void deleteUnitDetails(long unitDetailsId) {

    }
}
