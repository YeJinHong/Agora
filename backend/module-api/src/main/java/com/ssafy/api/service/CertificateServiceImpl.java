package com.ssafy.api.service;

import com.ssafy.common.util.PdfGenerator;
import com.ssafy.common.util.ThymeleafParser;
import com.ssafy.entity.rdbms.User;
import com.ssafy.entity.rdbms.UserDebate;
import com.ssafy.repository.UserDebateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {
    @Value("${file.path-certificate}")
    private String directoryPath;

    private final UserDebateRepository userDebateRepository;

    @Override
    public Resource issueCertification(User user) {
        String templateFileName = "certificate.html";

        List<UserDebate> histories = userDebateRepository.findAllByUserId(user.getId());

        Map<String, Object> variables = new HashMap<>();
        variables.put("name", user.getName());
        variables.put("email", user.getUserEmail());
        variables.put("totalTime", 0);
        variables.put("history", histories.stream().map(x -> {
            List<Object> list = new ArrayList<>();
            Map<String, Object> history = new HashMap<>();
            history.put("date", x.getDebate().getCallStartTime());
            history.put("debateId", x.getDebate().getId());
            history.put("title", x.getDebate().getTitle());
            history.put("activeTime", Duration.between(x.getDebate().getCallEndTime(), x.getDebate().getCallStartTime()).toMinutes());
            list.add(history);
            return list;
        }));

//        List<Map<String, Object>> history = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            Map<String, Object> obj = new HashMap<>();
//            obj.put("date", LocalDateTime.now());
//            obj.put("debateId", i);
//            obj.put("title", "title" + i);
//            obj.put("activeTime", 10);
//
//            history.add(obj);
//        }
//        variables.put("history", history);

        try {
            String html = ThymeleafParser.parseHtmlFileToString("certificate.html", variables);
            PdfGenerator.generateFromHtml(directoryPath, html);
            Path filePath = Paths.get(directoryPath + "/static/temp").resolve("토론_활동_증명서.pdf").normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException(templateFileName + " 파일을 찾을 수 없습니다.");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(templateFileName + " 파일을 찾을 수 없습니다.", e);
        }
    }
}
