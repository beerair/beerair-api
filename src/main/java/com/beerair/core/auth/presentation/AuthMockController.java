package com.beerair.core.auth.presentation;

import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.auth.dto.response.CustomGrantedAuthority;
import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.member.domain.Member;
import com.beerair.core.member.domain.vo.MemberSocial;
import com.beerair.core.member.domain.vo.SocialType;
import com.beerair.core.member.dto.LoggedInUser;
import com.beerair.core.member.infrastructure.MemberRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import static com.beerair.core.auth.presentation.AuthTokenAuthenticationFilter.TOKEN_TYPE;
import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Profile("local")
@Api(tags = "[0] (local) Auth Mock API")
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auths/mock", produces = APPLICATION_JSON_UTF_8)
@RestController
public class AuthMockController {
    private final AuthTokenCrypto authTokenCrypto;
    private final MemberRepository memberRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    @Transactional
    @ApiOperation(value = "모의 유저 생성")
    @PostMapping("access-token")
    public ResponseEntity<?> issueMockAccessToken() {
        Member member = member();
        var authentication = AuthTokenAuthentication.from(
                loggedInUser(member),
                authorities(member),
                new Date(new Date().getTime() + 1000000000)
        );
        String token = authTokenCrypto.encrypt(authentication);
        redisTemplate.opsForValue().set("authToken:" + member.getId(), token);
        return ResponseDto.ok(TOKEN_TYPE + " " + token);
    }

    private Member member() {
        Member member = Member.socialBuilder()
                .social(new MemberSocial(new Date().getTime() + "", SocialType.KAKAO))
                .phoneNumber("01012345678")
                .email("useremail@kakao.com")
                .profileUrl("https://i.picsum.photos/id/794/250/250.jpg?hmac=QUci8yt4pzVImB4UNMnMALBOHgHxmNN8DvCZG4s98i0")
                .build();
        return memberRepository.save(member);
    }

    private LoggedInUser loggedInUser(Member member) {
        return LoggedInUser.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .build();
    }

    private Collection<? extends GrantedAuthority> authorities(Member member) {
        return member.getRole()
                .getAuthorities()
                .stream()
                .map(CustomGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
