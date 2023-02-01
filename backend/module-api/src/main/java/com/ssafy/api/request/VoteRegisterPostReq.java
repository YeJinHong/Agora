package com.ssafy.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 청중 투표 API ([POST] /api/v1/vote) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("VoteRegisterPostRequest")
public class VoteRegisterPostReq {
    @ApiModelProperty(name="청중 ID")
    @JsonProperty("user_id")
    Long userId;

    @ApiModelProperty(name="토론 ID", example="1")
    @JsonProperty("debate_id")
    Long debateId;

    @ApiModelProperty(name="mvp ID", example="2")
    @JsonProperty("mvp_id")
    Long mvpId;

    @ApiModelProperty(name="관점 ID")
    @JsonProperty("perspective_id")
    Long perspectiveId;
}
