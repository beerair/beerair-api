Feature: Member
    Scenario:   유저 정보 관리 Flow
        Given   memberId: 'AAA', access: '1234', refresh: '5678' 토큰이 발급 되어있다.
        And     Access Token 사용 : '1234'

        When    회원 가입을 요청 하면
        Then    요청이 성공한다.

        When    닉네임 변경을 요청 하면
        Then    요청이 성공한다.

        When    회원 탈퇴를 요청 하면
        Then    요청이 성공한다.
