package com.ssafy.api.service;

import com.ssafy.entity.rdbms.FileManager;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserFileManagerService {
   Long getFileManager(String userEmail) throws IOException;
}
