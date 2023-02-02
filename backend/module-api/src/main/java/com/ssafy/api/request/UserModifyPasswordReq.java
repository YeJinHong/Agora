package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserPasswordRequest")
public class UserModifyPasswordReq {

    @ApiModelProperty(name="현재 유저 Password", example="nowPassword")
    private String nowPassword;

    @ApiModelProperty(name="새로운 유저 Password", example="newPassword")
    private String newPassword;


}
