package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 청중 투표 결과 조회 API ([GET] /api/v1/vote/debates/{:debateId}) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("VoteResponse")
public class VoteRes {
    @ApiModelProperty(name="토론 Id")
    Long debateId;

    @ApiModelProperty(name="mvp Id")
    Long mvpId;
    @ApiModelProperty(name="청중 투표 결과 리스트")
    List<VotePerspectiveRes> votePerspectiveResList;
}
