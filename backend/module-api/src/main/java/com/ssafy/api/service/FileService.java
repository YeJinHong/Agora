package com.ssafy.api.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    void saveProfileImg(String userEmail, MultipartFile file) throws IOException;
}
