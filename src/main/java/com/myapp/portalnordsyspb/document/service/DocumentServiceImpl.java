package com.myapp.portalnordsyspb.document.service;

import com.myapp.portalnordsyspb.document.dto.request.DocumentRequestDto;
import com.myapp.portalnordsyspb.document.dto.response.DocumentResponseDto;
import com.myapp.portalnordsyspb.document.entity.Document;
import com.myapp.portalnordsyspb.document.repository.CategoryRepository;
import com.myapp.portalnordsyspb.document.repository.DocumentRepository;
import com.myapp.portalnordsyspb.exceptions.DocumentNotFoundException;
import com.myapp.portalnordsyspb.exceptions.FileExistsException;
import com.myapp.portalnordsyspb.file.service.FileService;
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
public class DocumentServiceImpl implements DocumentService{

    private final DocumentRepository documentRepository;
    private final FileService fileService;
    private final CategoryRepository categoryRepository;

    @Value("${project.hostAddress}")
    private String baseUrl;
    @Value("${project.filePathDocker.Document}")
    private String path;

    @Override
    public DocumentRequestDto addDocument(DocumentRequestDto documentRequestDto, MultipartFile file) throws IOException {
        // upload the file
        if(Files.exists(Paths.get(path + File.separator + file.getOriginalFilename()))){
            throw new FileExistsException("File already exists!");
        }
        String uploadedFileName = fileService.uploadFile(path, file);
        // set the value of field 'fileName' as file name
        documentRequestDto.setFileName(uploadedFileName);
        // map dto to Document object
        Document document = Document
                .builder()
                .title(documentRequestDto.getTitle())
                .category(categoryRepository.findById(documentRequestDto.getCategoryId())
                        .orElseThrow(()-> new DocumentNotFoundException("Document not found.")))
                .fileName(documentRequestDto.getFileName())
                .build();
        // save the document object -> savedDocument object
        Document savedDocument = documentRepository.save(document);
        // generate the fileUrl
        String fileUrl = baseUrl + "/api/file/document/" + uploadedFileName;
        //  map Document object to dto and return it
        DocumentRequestDto response = DocumentRequestDto
                .builder()
                .title(savedDocument.getTitle())
                .categoryId(savedDocument.getCategory().getId())
                .fileName(savedDocument.getFileName())
                .fileUrl(fileUrl)
                .build();
        return response;
    }

    @Override
    public List<DocumentResponseDto> getAllDocumentsByCategoryId(long categoryId) {
        List<Document> documentList = documentRepository.findAllByCategoryId(categoryId);
        List<DocumentResponseDto> documentResponseDtoList = new ArrayList<>();
        for (Document document : documentList){
            String fileUrl = baseUrl + "/api/file/document/" + document.getFileName();
            DocumentResponseDto documentResponseDto = DocumentResponseDto
                    .builder()
                    .id(document.getId())
                    .title(document.getTitle())
                    .fileUrl(fileUrl)
                    .build();
            documentResponseDtoList.add(documentResponseDto);
        }
        return documentResponseDtoList;
    }

    @Override
    public String deleteDocument(long documentId) throws IOException {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(()-> new DocumentNotFoundException("Document not found."));
        Files.deleteIfExists(Paths.get(path + File.separator + document.getFileName()));
        documentRepository.delete(document);
        return "Document deleted with id = " + document.getId();
    }
}
