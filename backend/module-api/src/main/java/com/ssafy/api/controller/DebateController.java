package com.ssafy.api.controller;

import com.ssafy.api.request.DebateModifyPatchReq;
import com.ssafy.api.request.DebateModifyStatePatchReq;
import com.ssafy.api.request.DebateRegisterPostReq;
import com.ssafy.api.response.DebateRes;
import com.ssafy.api.service.DebateService;
import com.ssafy.api.service.FileManagerService;
import com.ssafy.api.service.FileService;
import com.ssafy.common.auth.CustomUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.model.response.BaseResponseDataBody;
import com.ssafy.entity.rdbms.Debate;
import com.ssafy.entity.rdbms.FileManager;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * 토론 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "토론 API", tags = {"Debate"})
@RestController
@RequestMapping("/api/v1/debates")
@RequiredArgsConstructor
public class DebateController {

    private final FileManagerService fileManagerService;

    private final FileService fileService;

    private final DebateService debateService;

    @PostMapping()
    @ApiOperation(value = "토론 생성")
    public ResponseEntity<? extends BaseResponseBody> register(
            DebateRegisterPostReq debateRegisterPostReq, MultipartFile file, @ApiIgnore Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        if (userDetails == null) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "접근 권한이 없습니다."));
        }
        try{
            Debate debate = debateService.createDebate(debateRegisterPostReq, file);
        }catch (IOException e) {
            return ResponseEntity.status(511).body(BaseResponseBody.of(511, "잘못된 파일입니다."));
        }

        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
    }

    @GetMapping()
    @ApiOperation(value = "토론 조회")
    public ResponseEntity<BaseResponseDataBody<Page<DebateRes>>> searchAll(@RequestParam(required = false) String keyword,
                                                                           @RequestParam(required = false) String condition,
                                                                           @PageableDefault(sort = "title", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<DebateRes> debates = debateService.searchAll(keyword, condition, pageable);
        BaseResponseDataBody<Page<DebateRes>> response = BaseResponseDataBody.of("Success", 200, debates);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{debateId}")
    @ApiOperation(value = "토론 아이디 조회")
    public ResponseEntity<BaseResponseDataBody<DebateRes>> search(@PathVariable long debateId) {
        DebateRes debate = debateService.search(debateId);
        BaseResponseDataBody<DebateRes> response = BaseResponseDataBody.of("Success", 200, debate);
        return ResponseEntity.status(201).body(response);
    }

    @PatchMapping("/{debateId}")
    @ApiOperation(value = "토론 수정")
    public ResponseEntity<?> modifyDebate(@PathVariable long debateId, @RequestBody DebateModifyPatchReq debateModifyReq, @ApiIgnore Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String userEmail = userDetails.getUsername();

        DebateRes debateRes = debateService.search(debateId);
        String checkUserEmail = debateRes.getOwnerEmail();

        if(!checkUserEmail.equals(userEmail)){
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "접근 권한이 없습니다."));
        }
        try {
            debateService.updateDebate(debateId, debateModifyReq);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "토론이 존재하지 않습니다"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(BaseResponseBody.of(500, "서버 오류 발생"));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @PatchMapping("/state/{debateId}")
    @ApiOperation(value = "토론 상태 수정")
    public ResponseEntity<?> modifyDebateState(@PathVariable long debateId, @RequestBody DebateModifyStatePatchReq debateModifyReq) {
        try {
            debateService.updateDebateState(debateId, debateModifyReq);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "토론이 존재하지 않습니다"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(BaseResponseBody.of(500, "서버 오류 발생"));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @PatchMapping("/thumbnail/{debateId}")
    @ApiOperation(value = "토론 썸네일 수정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 509, message = "토론 없음"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 511, message = "파일 입출력 오류")
    })
    public ResponseEntity<?> modifyThumbnail(@PathVariable long debateId, MultipartFile file, @ApiIgnore Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String userEmail = userDetails.getUsername();

        DebateRes debateRes = debateService.search(debateId);
        String checkUserEmail = debateRes.getOwnerEmail();

        if(!checkUserEmail.equals(userEmail)){
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "접근 권한이 없습니다."));
        }
        try {
            FileManager fileManager = fileManagerService.getFileManager(debateId);
            fileService.saveDebateThumbnail(file, fileManager, userEmail);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(509).body(BaseResponseBody.of(509, "해당하는 토론이 존재하지 않습니다"));
        } catch (IOException e) {
            return ResponseEntity.status(511).body(BaseResponseBody.of(511, "잘못된 파일 입니다"));
        }
        return ResponseEntity.status(201).body(BaseResponseBody.of(200, "썸네일 업로드 성공"));
    }

    @PatchMapping("/files/{debateId}")
    @ApiOperation(value = "토론 파일 등록")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 509, message = "토론 없음"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 511, message = "파일 입출력 오류")
    })
    public ResponseEntity<?> uploadDebateFile(@PathVariable long debateId, MultipartFile file, String role, @ApiIgnore Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String userEmail = userDetails.getUsername();

        DebateRes debateRes = debateService.search(debateId);
        String checkUserEmail = debateRes.getOwnerEmail();

        if(!checkUserEmail.equals(userEmail)){
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "접근 권한이 없습니다."));
        }
        try {
            FileManager fileManager = fileManagerService.getFileManager(debateId);
            fileService.saveDebateThumbnail(file, fileManager, userEmail);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(509).body(BaseResponseBody.of(509, "해당하는 토론이 존재하지 않습니다"));
        } catch (IOException e) {
            return ResponseEntity.status(511).body(BaseResponseBody.of(511, "잘못된 파일 입니다"));
        }
        return ResponseEntity.status(201).body(BaseResponseBody.of(200, "썸네일 업로드 성공"));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "토론 삭제")
    public ResponseEntity<BaseResponseBody> deleteDebate(@ApiIgnore Authentication authentication,
                                                         @PathVariable Long debateId) {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String userEmail = userDetails.getUsername();

        DebateRes debateRes = debateService.search(debateId);
        String checkUserEmail = debateRes.getOwnerEmail();

        if(!checkUserEmail.equals(userEmail)){
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "접근 권한이 없습니다."));
        }
        debateService.deleteDebate(debateId);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }
}
