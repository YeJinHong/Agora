package com.ssafy.api.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaqPostRes {

    private Long id;
    private String userId;
    private String category;
    private String content;
    private LocalDateTime registTime;
    private LocalDateTime updateTime;

    private String comment;
}
