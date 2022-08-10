package com.beerair.core.auth.application.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
public class AuthResponse {
    private final List<String> roles;

    // TODO 어떤 정보를 반환 할지? 더 생각 해보기
}
