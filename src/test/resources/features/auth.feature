Feature: Auth
    Scenario:   로그인 후 Refresh Token 사용해 Access Token 갱신
        Given   memberId: 'AAA', access: '1234', refresh: '5678' 토큰이 발급 되어있다.
        When    '5678' Refresh Token 으로 Access Token 발급을 요청하면
        Then    요청이 성공한다.
        When    '5678' Refresh Token 으로 Access Token 발급을 요청하면
        Then    요청이 실패한다.

    Scenario:   로그인 후 토큰 정보 조회
        Given   memberId: 'AAA', access: '1234', refresh: '5678' 토큰이 발급 되어있다.
        And     Access Token 사용 : '1234'
        And     나의 토큰 정보 조회를 요청 하면
        Then    요청이 성공한다.