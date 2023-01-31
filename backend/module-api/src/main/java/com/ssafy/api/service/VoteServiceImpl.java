package com.ssafy.api.service;

import com.ssafy.api.request.VoteRegisterPostReq;
import com.ssafy.api.response.VoteRes;
import com.ssafy.entity.rdbms.Vote;

public class VoteServiceImpl implements VoteService{
    @Override
    public Vote createVote(VoteRegisterPostReq voteRegisterPostReq) {
        return null;
    }

    @Override
    public void deleteVote(Long id) {

    }

    @Override
    public VoteRes getVoteByDebateId(Long debateId) {
        return null;
    }
}
