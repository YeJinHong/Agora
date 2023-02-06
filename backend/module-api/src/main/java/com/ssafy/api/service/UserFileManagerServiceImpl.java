package com.ssafy.api.service;

import com.ssafy.entity.rdbms.FileManager;
import com.ssafy.entity.rdbms.User;
import com.ssafy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserFileManagerServiceImpl implements UserFileManagerService{

    private final UserRepository userRepository;

    @Transactional
    public Long getFileManager(String userEmail) throws IOException {

        User user = userRepository.findByUserEmail(userEmail).orElseThrow(NoSuchElementException::new);

        //user에서 fileManager 뽑아오기
        if (user.getFileManager() == null) {

            FileManager fileManager = FileManager.builder()
                    .registTime(Date.from(Instant.now()))
                    .user(user)
                    .files(new ArrayList<>())
                    .build();
            user.createFileManager(fileManager);
            userRepository.save(user);
        }
        FileManager fileManager = user.getFileManager();

        return fileManager.getId();
    }
}
