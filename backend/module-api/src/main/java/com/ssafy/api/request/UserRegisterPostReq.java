package com.ssafy.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.entity.rdbms.Role;
import com.ssafy.entity.rdbms.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * 유저 회원가입 API ([POST] /api/v1/users) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserRegisterPostRequest")
public class UserRegisterPostReq {
	@ApiModelProperty(name="유저 Email")
	@JsonProperty("user_email")
	String email;

	@ApiModelProperty(name="유저 Password")
	String password;

	@ApiModelProperty(name="User 직위")
	String position;

	@ApiModelProperty(name="User 부서")
	String department;

	@ApiModelProperty(name="User 이름")
	String name;

	@ApiModelProperty(name="User 학년")
	int grade;

	@ApiModelProperty(name="User 반")
	int classNum;

	@ApiModelProperty(name="User 역할")
	String role;
}
