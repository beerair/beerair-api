package com.beerair.core.member.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberChangeNicknameRequest {
    @ApiModelProperty(
        dataType = "String",
        value = "변경할 닉네임",
        example = "맥주아저씨",
        required = true
    )
    @Length(min = 3, max = 15)
    private String nickname;
}
