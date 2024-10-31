package com.myapp.portalnordsyspb.library.controller;

import com.myapp.portalnordsyspb.library.service.DocumentService;
import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;
//
//    @Value("${project.document}")
//    private String path;
//
//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadFileHandler(@RequestPart MultipartFile file) throws IOException {
//        String uploadedFileName = documentService.uploadDocument(path, file);
//        return ResponseEntity.ok("File uploaded : " + uploadedFileName);
//    }
//
//    @GetMapping(value = "/{fileName}")
//    public void serveFileHandler(@PathVariable String fileName, HttpServletResponse response) throws IOException {
//        InputStream resourceFile = documentService.getResourceDocument(path, fileName);
//        response.setContentType(String.valueOf(MediaType.APPLICATION_PDF));
//        StreamUtils.copy(resourceFile, response.getOutputStream());
//    }

    @GetMapping(value="printing/",produces= MediaType.APPLICATION_PDF_VALUE)
    public  @ResponseBody byte[]  print(@RequestParam("filterParam") String filterParam) {

        try {
            FileInputStream fis= new FileInputStream(new File("doc\\1.pdf"));
            byte[] targetArray = new byte[fis.available()];
            fis.read(targetArray);
            return targetArray;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity<InputStreamResource> downloadPDFFile()
            throws IOException {

        ClassPathResource pdfFile = new ClassPathResource("/doc/1.pdf");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(pdfFile.contentLength())
                .contentType(
                        MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(pdfFile.getInputStream()));
    }

    @GetMapping("/test")
    public String gettest(){
        return "ok";
    }

//    private static final String DIRECTORY = "C:/test";
    private static final String DIRECTORY = "/home/photos/";
//    private static final String DIRECTORY = "/home/astra/documents";
    private static final String DEFAULT_FILE_NAME = "test22.pdf";

    private final String baseUrl = "http://172.16.15.77:8080";
    private final String path ="/home/photos/";

    @Autowired
    private ServletContext servletContext;

    // http://localhost:8080/download2?fileName=abc.zip
    // Using ResponseEntity<ByteArrayResource>
    @GetMapping("/download2")
    public ResponseEntity<ByteArrayResource> downloadFile2(
            @RequestParam(defaultValue = DEFAULT_FILE_NAME) String fileName) throws IOException {

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
        System.out.println("fileName: " + fileName);
        System.out.println("mediaType: " + mediaType);

        Path path = Paths.get(DIRECTORY + "/" + DEFAULT_FILE_NAME);
        byte[] data = Files.readAllBytes(path);
        ByteArrayResource resource = new ByteArrayResource(data);

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                // Content-Type
                .contentType(mediaType) //
                // Content-Lengh
                .contentLength(data.length) //
                .body(resource);
    }

    @RequestMapping("/download1")
    public ResponseEntity<InputStreamResource> downloadFile1(
            @RequestParam(defaultValue = DEFAULT_FILE_NAME) String fileName) throws IOException {

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
        System.out.println("fileName: " + fileName);
        System.out.println("mediaType: " + mediaType);

        File file = new File(DIRECTORY + "/" + fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(mediaType)
                // Content-Length
                .contentLength(file.length()) //
                .body(resource);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFilePDFHandler(@RequestParam("file") MultipartFile file) throws IOException {
        String uploadedFileName = documentService.uploadDocument(file);
        return ResponseEntity.ok("Файл загружен : " + uploadedFileName);
    }


}
