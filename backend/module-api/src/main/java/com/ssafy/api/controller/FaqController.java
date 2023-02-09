package com.ssafy.api.controller;

import com.ssafy.api.request.FaqPostReq;
import com.ssafy.api.request.FaqUpdateReq;
import com.ssafy.api.response.FaqPostRes;
import com.ssafy.api.service.FaqService;
import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/faq")
public class FaqController {

    private final FaqService faqService;

    @GetMapping("/list/{category}")
    @ApiOperation(value = "질문 리스트 조회")
    public ResponseEntity<?> selectFaqList( //0부터 시작 EX) localhost:8082/api/v1/faq/list/음식/?page=0&size=10
                                            // -> page 0(첫번쨰), size 10 (10개)
            @ApiParam(value="페이지 정보", required = true) Pageable pageable, @PathVariable String category) {

        Page<FaqPostRes> faqPostResList = faqService.selectAllFaq(pageable, category);

        return new ResponseEntity<Page<FaqPostRes>>(faqPostResList,HttpStatus.OK);
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
            @RequestBody @ApiParam(value="수정된 질문 정보", required = true) FaqUpdateReq faqUpdateReq, @PathVariable Long id) {
        try {
            faqService.updateFaqPost(faqUpdateReq, id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "질문이 존재하지 않습니다"));
        }catch (Exception e){
            return ResponseEntity.status(201).body(BaseResponseBody.of(404, "알수없는 에러가 발생하였습니다."));
        }
        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "수정이 성공적으로 이루어 졌습니다."));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "질문 삭제")
    public ResponseEntity<BaseResponseBody> deletePost(@PathVariable Long id) {

        try{
            faqService.deleteFaq(id);
        }catch (Exception e){
            return ResponseEntity.status(404).body(BaseResponseBody.of(404,"알수없는 에러가 발생하였습니다."));
        }

        return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공적으로 삭제 되었습니다."));
    }


}
