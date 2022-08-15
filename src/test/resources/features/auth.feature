Feature: Auth
    Scenario:   로그인 후 Refresh Token 사용해 Access Token 갱신
        When    'naver' 로그인을 요청하면
        Then    요청이 성공한다.
