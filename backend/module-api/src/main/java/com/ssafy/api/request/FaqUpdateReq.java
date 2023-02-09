package com.ssafy.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FaqUpdateReq {

    private String content;

    private String comment;
}
