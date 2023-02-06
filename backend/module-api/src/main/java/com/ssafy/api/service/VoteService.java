package com.ssafy.api.service;

import com.ssafy.api.request.VoteRegisterPostReq;
import com.ssafy.entity.rdbms.Vote;

import java.util.List;

/**
 *	청중 투표 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface VoteService {
    Vote createVote(VoteRegisterPostReq voteRegisterPostReq, String userId);
    void deleteVote(Long id);
    List<Vote> getVoteByDebateId(Long debateId);
}
