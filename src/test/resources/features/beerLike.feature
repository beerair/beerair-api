Feature: Beer Like
    Scenario:  맥주 좋아요 기본 flow
        Given   access: '1234', refresh: '5678' 회원가입된 유저의 토큰이 발급 되어있다.
        And     Access Token 사용 : '1234'

        #Toggle True
        When    맥주 좋아요를 요청하면
        Then    요청이 성공한다.
        When    좋아요한 맥주 목록을 요청하면
        Then    1개가 조회된다.
        When    좋아요한 맥주 Count를 요청하면
        Then    조회된 맥주 Count는 1개이다.

        # Toogle False
        When    맥주 좋아요를 요청하면
        Then    요청이 성공한다.
        When    좋아요한 맥주 목록을 요청하면
        Then    0개가 조회된다.
        When    좋아요한 맥주 Count를 요청하면
        Then    조회된 맥주 Count는 0개이다.
