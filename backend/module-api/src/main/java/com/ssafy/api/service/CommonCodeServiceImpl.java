package com.ssafy.api.service;

import com.ssafy.entity.rdbms.CommonCode;
import com.ssafy.repository.CommonCodeRepository;
import com.ssafy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service("commonCodeService")
@RequiredArgsConstructor
public class CommonCodeServiceImpl implements CommonCodeService {

    private final CommonCodeRepository commonCodeRepository;
    @Value("${common-code.evaluation-question-parent-id}")
    private Long id;
    @Override
    public CommonCode getQuestionList() {
        return commonCodeRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }


}
