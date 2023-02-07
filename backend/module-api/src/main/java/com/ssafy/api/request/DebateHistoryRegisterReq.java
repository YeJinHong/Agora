package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("DebateHistoryRegisterReq")
public class DebateHistoryRegisterReq {
    @ApiModelProperty(name = "")

    @CreationTimestamp
    @ApiModelProperty(name = "로그 시간")
    private LocalDateTime insertedTime;
}
