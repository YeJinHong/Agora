package com.ssafy.api.controller;

import com.ssafy.api.request.DebateRegisterPostReq;
import com.ssafy.api.request.FaqCommentReq;
import com.ssafy.api.request.FaqPostReq;
import com.ssafy.api.response.FaqPostRes;
import com.ssafy.api.service.FaqService;
import com.ssafy.common.auth.CustomUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.entity.rdbms.Debate;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
public class FaqController {

    private final FaqService faqService;

    @GetMapping("/{category}")
    @ApiOperation(value = "질문 리스트 조회")
    public ResponseEntity<? extends BaseResponseBody> selectFaqList(
            @ApiParam(value="페이지 정보", required = true) Pageable pageable, @PathVariable String category) {


        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "질문 세부 조회")
    public ResponseEntity<?> selectFaq(
            @RequestBody @ApiParam(value="질문 ID", required = true) @PathVariable Long id) {

        FaqPostRes faqPostRes = faqService.selectFaqById(id);

        return new ResponseEntity<FaqPostRes>(faqPostRes, HttpStatus.OK);
    }
    @PostMapping("/post")
    @ApiOperation(value = "질문 생성")
    public ResponseEntity<? extends BaseResponseBody> registPost(
            @RequestBody @ApiParam(value="질문 정보", required = true) FaqPostReq faqPostReq) {
        try {
            faqService.createFaq(faqPostReq);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401,"회원 인증에 실패하였습니다."));
        }catch (Exception e){
            return ResponseEntity.status(404).body(BaseResponseBody.of(404,"알수없는 에러가 발생하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200,"Success"));
    }
    @PatchMapping({"/{id}"})
    @ApiOperation(value = "질문 수정")
    public ResponseEntity<? extends BaseResponseBody> updatePost(
            @RequestBody @ApiParam(value="수정된 질문 정보", required = true) FaqPostReq faqPostReq) {
        try {
            faqService.updateFaqPost(faqPostReq);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "질문이 존재하지 않습니다"));
        }catch (Exception e){
            return ResponseEntity.status(201).body(BaseResponseBody.of(404, "알수없는 에러가 발생하였습니다."));
        }
        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "수정이 성공적으로 이루어 졌습니다."));
    }
    @PostMapping("/comment")
    @ApiOperation(value = "답변 생성 및 수정")
    public ResponseEntity<BaseResponseBody> registerComment(@ApiIgnore Authentication authentication,
                                                            @RequestBody FaqCommentReq faqCommentReq) {
        try {
            faqService.updateComment(faqCommentReq);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401,"존재하지 않은 게시물 입니다."));
        }catch (Exception e){
            return ResponseEntity.status(404).body(BaseResponseBody.of(404,"알수없는 에러가 발생하였습니다."));
        }

        return ResponseEntity.status(200).body(BaseResponseBody.of(200,"Success"));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "질문 삭제")
    public ResponseEntity<BaseResponseBody> deletePost(@ApiIgnore Authentication authentication,
                                                         @PathVariable Long id) {

        CustomUserDetails userDetails = (CustomUserDetails)authentication.getPrincipal();
        try{
            faqService.deleteFaq(userDetails.getUsername(), id);
        }catch (RuntimeException e){
            return ResponseEntity.status(401).body(BaseResponseBody.of(401,e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.status(404).body(BaseResponseBody.of(404,"알수없는 에러가 발생하였습니다."));
        }

        return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공적으로 삭제 되었습니다."));
    }


}
