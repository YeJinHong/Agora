package com.ssafy.api.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Builder
public class FaqPostReq {
    private Long id;

    private String userId;
    private String title;
    private String content;

    private Date registTime;

    private Date updateTime;

}
