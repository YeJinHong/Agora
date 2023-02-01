package com.ssafy.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Map;

/**
 * 토론 상호평가 생성 API ([POST] /api/v1/evaluation) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("EvaluationRegisterPostRequest")
public class EvaluationRegisterPostReq {
    @ApiModelProperty(name="토론 ID", example="1")
    Long debateId;
    @ApiModelProperty(name="피평가자 ID", example="ssafy@naver.com")
    String evaluatedId;
    @ApiModelProperty(name="평가 내용", example="")
    ArrayList<EvaluationBase> content;
}
