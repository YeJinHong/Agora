package com.ssafy.api.service;

import com.ssafy.api.request.VoteRegisterPostReq;
import com.ssafy.entity.rdbms.Vote;
import com.ssafy.repository.DebateRepository;
import com.ssafy.repository.PerspectiveRepository;
import com.ssafy.repository.UserRepository;
import com.ssafy.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * 청중 투표 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("voteService")
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService{

    private final UserRepository userRepository;
    private final VoteRepository voteRepository;
    private final DebateRepository debateRepository;
    private final PerspectiveRepository perspectiveRepository;

    @Override
    public Vote createVote(VoteRegisterPostReq voteRegisterPostReq) {
        Vote vote = new Vote();
        vote.setDebate(debateRepository.findById(voteRegisterPostReq.getDebateId()).orElseThrow(NoSuchElementException::new));
        vote.setUser(userRepository.findById(voteRegisterPostReq.getUserId()).orElseThrow(NoSuchElementException::new));
        vote.setMvpUser(userRepository.findById(voteRegisterPostReq.getMvpId()).orElseThrow(NoSuchElementException::new));
        vote.setPerspective(perspectiveRepository.findById(voteRegisterPostReq.getPerspectiveId()).orElseThrow(NoSuchElementException::new));
        return voteRepository.save(vote);
    }

    @Override
    public void deleteVote(Long id) {
        Vote vote = voteRepository.findById(id).orElseThrow(NoSuchElementException::new);
        voteRepository.delete(vote);
    }

    @Override
    public List<Vote> getVoteByDebateId(Long debateId) {
        return Optional.ofNullable(voteRepository.findByDebateId(debateId)).orElse(Collections.emptyList());
    }
}
