Feature: Auth
    Scenario:   로그인 후 Refresh Token 사용해 Access Token 갱신
        Given   memberId: 'AAA', access: '1234', refresh: '5678' 토큰이 발급 되어있다.
        When    '5678' Refresh Token 으로 Access Token 발급을 요청하면
        Then    요청이 성공한다.
        When    '5678' Refresh Token 으로 Access Token 발급을 요청하면
        Then    요청이 실패한다.

    Scenario:   로그인 후 권한 정보 조회 해보기
        Given   memberId: 'AAA', access: '1234', refresh: '5678' 토큰이 발급 되어있다.
        When    '1234' Access Token 으로 나의 토큰 정보 조회를 요청 하면
        Then    요청이 성공한다.
        When    '12345' Access Token 으로 나의 토큰 정보 조회를 요청 하면
        Then    요청이 실패한다.
