Feature: Member
    Scenario:   Member 기본 Flow
        Given   access: '1234', refresh: '5678' 회원가입된 유저의 토큰이 발급 되어있다.
        And     Access Token 사용 : '1234'

        When    나의 정보를 요청 하면
        Then    요청이 성공한다.
        And     나의 정보에서 티어는 1 이다.

        When    닉네임 변경을 요청 하면
        Then    요청이 성공한다.

        When    회원 탈퇴를 요청 하면
        Then    요청이 성공한다.

        When    나의 정보를 요청 하면
        Then    요청이 실패한다.


    Scenario:   닉네임 변경 Validation
        Given   access: '1' 토큰이 발급 되어있다.
        And     Access Token 사용 : '1'
        When    'AAAAA' 닉네임으로 회원 가입을 요청 하면
        Then    요청이 성공한다.

        Given   access: '2' 토큰이 발급 되어있다.
        And     Access Token 사용 : '2'
        When    'AAAAA' 닉네임으로 회원 가입을 요청 하면
        Then    요청이 실패한다.
