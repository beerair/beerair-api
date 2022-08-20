Feature: Member
    Scenario: 로그인 후 회원가입 하고 중복된 회원 가입 요청
        Given   memberId: 'AAA', access: '1234', refresh: '5678' 토큰이 발급 되어있다.
        And     Access Token 사용 : '1234'

        When    회원가입 요청을 하면
        Then    요청이 성공한다.
        When    회원가입 요청을 하면
        Then    요청이 실패한다.
