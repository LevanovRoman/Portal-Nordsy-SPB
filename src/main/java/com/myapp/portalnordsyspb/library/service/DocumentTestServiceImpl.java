package com.myapp.portalnordsyspb.library.service;

import com.myapp.portalnordsyspb.exceptions.FileExistsException;
import com.myapp.portalnordsyspb.file.service.FileService;
import com.myapp.portalnordsyspb.library.dto.request.DocumentRequestDto;
import com.myapp.portalnordsyspb.library.dto.response.DocumentResponseDto;
import com.myapp.portalnordsyspb.library.entity.Document;
import com.myapp.portalnordsyspb.library.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class DocumentTestServiceImpl implements DocumentTestService{

    private final FileService fileService;
    private final DocumentRepository documentRepository;


    private final String baseUrl = "http://172.16.15.77:8080";
    private final String path ="/home/photos/";

    @Override
    public DocumentRequestDto addDocument(DocumentRequestDto documentRequestDto, MultipartFile file) throws IOException {
        // upload the file
        if(Files.exists(Paths.get(path + File.separator + file.getOriginalFilename()))){
            throw new FileExistsException("File already exists!");
        }
        String uploadedDocumentName = fileService.uploadFile(path, file);
        // set the value of field 'fileName' as filename
        documentRequestDto.setFileName(uploadedDocumentName);
        // map dto to Document object
        Document document = new Document();
        document.setTitle(documentRequestDto.getTitle());
        document.setSection(documentRequestDto.getSection());
        document.setFileName(documentRequestDto.getFileName());
        // save the document object -> savedDocument object
        Document savedDocument = documentRepository.save(document);
        // generate the fileUrl
        String fileUrl = baseUrl + "/api/file/" + uploadedDocumentName;
        //  map Document object to dto and return it
        DocumentRequestDto response = DocumentRequestDto.builder()
                .title(savedDocument.getTitle())
                .section(savedDocument.getSection())
                .fileName(savedDocument.getFileName())
                .fileUrl(fileUrl)
                .build();
        return response;
    }

    @Override
    public DocumentResponseDto getDocumentById(long documentId) {
        // check the data in DB and if exists, fetch the data of given ID
        Document document = documentRepository.findById(documentId)
                .orElseThrow(()-> new FileExistsException("Document not found."));
        // generate the fileUrl
        String fileUrl = baseUrl + "/api/file/" + document.getFileName();
        // map to DocumentResponseDto object and return it
        DocumentResponseDto responseDto = DocumentResponseDto.builder()
                .id(document.getId())
                .title(document.getTitle())
                .section(document.getSection())
                .fileName(document.getFileName())
                .fileUrl(fileUrl)
                .build();
        return responseDto;
    }


}
