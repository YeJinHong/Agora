package com.ssafy.api.controller;

import com.ssafy.api.request.DebateRegisterPostReq;
import com.ssafy.api.service.DebateService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.entity.Debate;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 토론 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "토론 API", tags = {"Debate"})
@RestController
@RequestMapping("/api/v1/debates/")
@RequiredArgsConstructor
public class DebateController {

	private final DebateService debateService;

	@PostMapping()
	@ApiOperation(value = "토론 생성")
	public ResponseEntity<? extends BaseResponseBody> register(
			@RequestBody @ApiParam(value="회원가입 정보", required = true) DebateRegisterPostReq debateRegisterPostReq) {

		Debate debate = debateService.createDebate(debateRegisterPostReq);
		return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "토론 삭제")
	public ResponseEntity<BaseResponseBody> deleteDebate(@ApiIgnore Authentication authentication,
			@PathVariable Long id) {

		debateService.deleteDebate(id);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200,"Success"));
	}
}
