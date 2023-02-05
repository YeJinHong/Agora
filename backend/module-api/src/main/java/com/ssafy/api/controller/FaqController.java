package com.ssafy.api.controller;

import com.ssafy.api.request.DebateRegisterPostReq;
import com.ssafy.api.request.FaqPostReq;
import com.ssafy.api.service.FaqService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.entity.rdbms.Debate;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequiredArgsConstructor
public class FaqController {

    private final FaqService faqService;

    @PostMapping()
    @ApiOperation(value = "질문 생성")
    public ResponseEntity<? extends BaseResponseBody> registPost(
            @RequestBody @ApiParam(value="회원가입 정보", required = true) FaqPostReq faqPostReq) {


        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
    }

    @PostMapping()
    @ApiOperation(value = "답변 생성")
    public ResponseEntity<? extends BaseResponseBody> registerComment(
            @RequestBody @ApiParam(value="회원가입 정보", required = true) FaqPostReq faqPostReq) {


        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
    }
    @PatchMapping("/{id}")
    @ApiOperation(value = "질문 수정")
    public ResponseEntity<BaseResponseBody> updatePost(@ApiIgnore Authentication authentication,
                                                         @PathVariable Long id) {


        return ResponseEntity.status(200).body(BaseResponseBody.of(200,"Success"));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "질문 삭제")
    public ResponseEntity<BaseResponseBody> deletePost(@ApiIgnore Authentication authentication,
                                                         @PathVariable Long id) {


        return ResponseEntity.status(200).body(BaseResponseBody.of(200,"Success"));
    }


}
