package com.ssafy.api.service;

import com.ssafy.entity.rdbms.User;
import com.ssafy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final UserRepository userRepository;

    @Value("${file.path}")
    private String filePath;


    @Override
    public void saveProfileImg(String userEmail, MultipartFile file) throws IOException{

        User user = userRepository.findByUserEmail(userEmail).orElseThrow(NoSuchElementException::new);
        //user에서 fileManager 뽑아오기


        //파일 확장자 뽑아오기
        String fileExtension = "." + StringUtils.getFilenameExtension(file.getOriginalFilename());

        //UUID에 파일 확장자 붙이기
        String fileName = UUID.randomUUID().toString() + fileExtension;

        Path path = Paths.get(filePath + File.separator + fileName);
        file.transferTo(path);


    }
}
