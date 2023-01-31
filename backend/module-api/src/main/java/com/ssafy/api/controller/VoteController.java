package com.ssafy.api.controller;

import com.ssafy.api.request.DebateRegisterPostReq;
import com.ssafy.api.request.VoteRegisterPostReq;
import com.ssafy.api.service.VoteService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.entity.rdbms.Debate;
import com.ssafy.entity.rdbms.Vote;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 청중 투표 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "투표 API", tags = {"Debate"})
@RestController
@RequestMapping("/api/v1/debates/")
@RequiredArgsConstructor
public class VoteController {

     private final VoteService voteService;
    @PostMapping()
    @ApiOperation(value = "청중 투표 생성")
    public ResponseEntity<? extends BaseResponseBody> register(
            @RequestBody @ApiParam(value="청중 투표 정보", required = true) VoteRegisterPostReq voteRegisterPostReq) {

        Vote vote = voteService.createVote(voteRegisterPostReq);
        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
    }


}
