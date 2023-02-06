package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Optional;

@Getter
@Setter
@ApiModel("DebateSearchAllGetReq")
public class DebateSearchAllGetReq {

    @ApiModelProperty(name="검색어")
    String keyword;

    @ApiModelProperty(name="검색조건")
    String condition;
}
