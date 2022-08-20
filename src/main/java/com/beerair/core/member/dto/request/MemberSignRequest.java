package com.beerair.core.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignRequest {
    @Length(min = 1, max = 15, message = "닉네임은 최대 15글자까지 입력할 수 있습니다.")
    private String nickname;
}
