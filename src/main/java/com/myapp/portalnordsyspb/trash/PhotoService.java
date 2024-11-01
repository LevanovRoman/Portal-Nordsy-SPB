package com.myapp.portalnordsyspb.trash;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface PhotoService {

    String uploadPhoto(String path, MultipartFile file) throws IOException;

    InputStream getResourcePhoto(String path, String name) throws FileNotFoundException;
}
