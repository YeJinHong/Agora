package com.ssafy.api.service;

import com.ssafy.api.request.DebateModifyPatchReq;
import com.ssafy.api.request.DebateModifyStatePatchReq;
import com.ssafy.api.request.DebateRegisterPostReq;
import com.ssafy.api.response.DebateRes;
import com.ssafy.entity.rdbms.*;
import com.ssafy.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 토론 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("debateService")
@RequiredArgsConstructor
public class DebateServiceImpl implements DebateService {

    private final UserRepository userRepository;

    private final DebateRepository debateRepository;

    private final DebateResultRepository debateResultRepository;

    private final PerspectiveServiceImpl perspectiveService;

    private final FileManagerService fileManagerService;

    private final FileService fileService;

    @Override
    @Transactional
    public Debate createDebate(DebateRegisterPostReq debateRegisterPostReq, MultipartFile file) throws IOException {
        User owner = userRepository.findByUserEmail(debateRegisterPostReq.getOwnerId())
                .orElseThrow(NoSuchElementException::new);
        Debate debate = makeDebate(debateRegisterPostReq, owner);
        Debate savedDebate = debateRepository.save(debate);

        if (file != null) {
            FileManager fileManager = fileManagerService.getFileManager(savedDebate.getId());
            File newFile = fileService.saveDebateThumbnail(file, fileManager, owner.getUserEmail());
        }

        perspectiveService.createPerspective(debateRegisterPostReq.getPerspectiveNames(), savedDebate);
        return savedDebate;
    }

    @Override
    public Page<DebateRes> searchAll(String keyword, String condition, Pageable pageable) {
        Page<Debate> debates = debateRepository.findDebateBySearchCondition(keyword, condition, pageable);
        return new DebateRes().toDtoList(debates);
    }

    @Override
    public DebateRes search(long debateId) {
        Debate debate = debateRepository.findById(debateId).orElseThrow(NoSuchElementException::new);
        return DebateRes.of(debate);
    }


    @Override
    public void updateDebate(long debateId, DebateModifyPatchReq debateModifyReq) {
        Debate debate = debateRepository.findById(debateId).orElseThrow(NoSuchElementException::new);
        debate.setTitle(debateModifyReq.getTitle());
        debate.setDescription(debateModifyReq.getDescription());
        debate.setCategory(debateModifyReq.getCategory());
        debate.setModeratorOnOff(debateModifyReq.getModeratorOnOff());
        debate.setDebateMode(debateModifyReq.getDebateMode());
        debate.setCallStartTime(debateModifyReq.getCallStartTime());
        debate.setCallEndTime(debateModifyReq.getCallEndTime());
        debate.setDebateModeOption(debateModifyReq.getDebateModeOption());
        debateRepository.save(debate);
    }

    @Override
    public void updateDebateState(long debateId, DebateModifyStatePatchReq debateModifyStateReq) {
        Debate debate = debateRepository.findById(debateId).orElseThrow(NoSuchElementException::new);
        debate.setState(debateModifyStateReq.getState());
        debateRepository.save(debate);
    }

    private Debate makeDebate(DebateRegisterPostReq debateRegisterPostReq, User owner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        Debate debate = new Debate();
        debate.setOwner(owner);
        debate.setCategory(debateRegisterPostReq.getCategory());
        debate.setTitle(debateRegisterPostReq.getTitle());
        debate.setDescription(debateRegisterPostReq.getDescription());
        debate.setModeratorOnOff(debateRegisterPostReq.getModeratorOnOff());
        debate.setDebateMode(debateRegisterPostReq.getDebateMode());
        debate.setThumbnailUrl(debateRegisterPostReq.getThumbnailUrl());
        debate.setState(debateRegisterPostReq.getState());
        debate.setInsertedTime(debateRegisterPostReq.getInsertedTime());
        debate.setCallStartTime(LocalDateTime.parse(debateRegisterPostReq.getCallStartTime(), formatter));
        debate.setCallEndTime(LocalDateTime.parse(debateRegisterPostReq.getCallEndTime(), formatter));
        debate.setDebateModeOption(debateRegisterPostReq.getDebateModeOption());
        return debate;
    }

    @Override
    public void deleteDebate(Long id) {
        Debate debate = debateRepository.findById(id).orElseThrow(NoSuchElementException::new);
        debateRepository.delete(debate);
    }
}
