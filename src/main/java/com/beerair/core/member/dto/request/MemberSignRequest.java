package com.beerair.core.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignRequest {
    @Length(min = 3, max = 15)
    private String nickname;
}
