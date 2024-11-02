package com.myapp.portalnordsyspb.library.service;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.exceptions.FileExistsException;
import com.myapp.portalnordsyspb.exceptions.SectionNotFoundException;
import com.myapp.portalnordsyspb.file.service.FileService;
import com.myapp.portalnordsyspb.library.dto.request.SectionRequestDto;
import com.myapp.portalnordsyspb.library.dto.response.SectionResponseDto;
import com.myapp.portalnordsyspb.library.entity.Section;
import com.myapp.portalnordsyspb.library.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService{

    @Value("${project.hostAddress}")
    private String baseUrl;

    @Value("${project.filePathDocker.Library}")
    private String path;

    private final FileService fileService;
    private final SectionRepository sectionRepository;

    @Override
    public SectionRequestDto addSection(SectionRequestDto sectionRequestDto, MultipartFile file) throws IOException {
        // upload the file
        if(Files.exists(Paths.get(path + File.separator + file.getOriginalFilename()))){
            throw new FileExistsException("File already exists!");
        }
        String uploadedFileName = fileService.uploadFile(path, file);
        // set the value of field 'label' as file name
        sectionRequestDto.setLabel(uploadedFileName);
        // map dto to Section object
//        Section section = new Section();
//        section.setName(sectionRequestDto.getName());
//        section.setLabel(sectionRequestDto.getLabel());
//        // save the section object -> savedSection object
//        Section savedSection = sectionRepository.save(section);
//        // generate the fileUrl
//        String fileUrl = baseUrl + "/api/file/" + uploadedFileName;
//        //  map Section object to dto and return it
//        SectionRequestDto response = SectionRequestDto
//                .builder()
//                .name(savedSection.getName())
//                .label(savedSection.getLabel())
//                .labelUrl(fileUrl)
//                .build();
//        return response;
        return saveSection(sectionRequestDto);
    }

    @Override
    public SectionRequestDto updateSection(long sectionId, SectionRequestDto sectionRequestDto, MultipartFile file) throws IOException {
        // check the data in DB and if exists, fetch the data of given ID
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(()->new SectionNotFoundException("Section not found."));
        // 2.if file is null, do nothing
        // if file is not null, then delete existing file associated with record and upload new file
        String fileName = section.getLabel();
        if (file != null){
            Files.deleteIfExists(Paths.get(path + File.separator + fileName));
            fileName = fileService.uploadFile(path, file);
        }
        // 3.set NewsRequestDto`s photo value
        sectionRequestDto.setLabel(fileName);
        return saveSection(sectionRequestDto);

    }

    private SectionRequestDto saveSection(SectionRequestDto sectionRequestDto){
        Section section = new Section();
        section.setName(sectionRequestDto.getName());
        section.setLabel(sectionRequestDto.getLabel());
        // save the section object -> savedSection object
        Section savedSection = sectionRepository.save(section);
        // generate the fileUrl
        String fileUrl = baseUrl + "/api/file/library/" + sectionRequestDto.getLabel();
        //  map Section object to dto and return it
        SectionRequestDto response = SectionRequestDto
                .builder()
                .name(savedSection.getName())
                .label(savedSection.getLabel())
                .labelUrl(fileUrl)
                .build();
        return response;
    }

    @Override
    public SectionResponseDto getSectionById(long sectionId) {
        // check the data in DB and if exists, fetch the data of given ID
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(()->new SectionNotFoundException("Section not found."));
        // generate the fileUrl
        String fileUrl = baseUrl + "/api/file/library/" + section.getLabel();
        // map to DocumentResponseDto object and return it
        SectionResponseDto response = SectionResponseDto
                .builder()
                .id(section.getId())
                .name(section.getName())
                .label(section.getLabel())
                .labelUrl(fileUrl)
                .build();
        return response;
    }

    @Override
    public List<SectionResponseDto> getAllSections() {
        List<Section> sectionList = sectionRepository.findAll();
        List<SectionResponseDto> sectionResponseDtoList = new ArrayList<>();
        // iterate through the list, generate labelUrl for each Section object and map to SectionResponseDto object
        for (Section section : sectionList){
            String labelUrl = baseUrl + "/api/file/library/" + section.getLabel();
            SectionResponseDto sectionResponseDto = new SectionResponseDto(
                    section.getId(),
                    section.getName(),
                    section.getLabel(),
                    labelUrl
            );
            sectionResponseDtoList.add(sectionResponseDto);
        }
        return sectionResponseDtoList;
    }

    @Override
    public String deleteSection(long sectionId) throws IOException {
        // check the data in DB and if exists, fetch the data of given ID
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(()->new SectionNotFoundException("Section not found."));
        // 2. delete file associated with this object
        Files.deleteIfExists(Paths.get(path + File.separator + section.getLabel()));
        // 3. delete the Section object
        sectionRepository.delete(section);
        return "Section deleted with id = " + section.getId();
    }
}
