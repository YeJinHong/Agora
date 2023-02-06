package com.ssafy.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@Builder
public class FaqPostRes {

    private Long id;
    private String userId;
    private String title;
    private String content;
    private LocalDateTime registTime;
    private LocalDateTime updateTime;

    private String comment;
}
