Feature: Beer Like
    #제주 슬라이스 Id = 3
    Scenario:  맥주 좋아요 기본 flow
        Given   access: '1234', refresh: '5678' 회원가입된 유저의 토큰이 발급 되어있다.
        And     Access Token 사용 : '1234'

        #True
        When    3 맥주 좋아요를 요청하면
        Then    요청이 성공한다.
        When    좋아요한 맥주 Count를 요청하면
        Then    좋아요한 맥주 Count는 1개이다.
        When    좋아요한 맥주 목록을 요청하면
        Then    요청이 성공한다.
        Then    1개가 조회된다.

        # False
        When    3 맥주 좋아요 해제를 요청하면
        Then    요청이 성공한다.
        When    좋아요한 맥주 Count를 요청하면
        Then    좋아요한 맥주 Count는 0개이다.
        When    좋아요한 맥주 목록을 요청하면
        Then    요청이 성공한다.
        Then    0개가 조회된다.


    Scenario:  2명의 사용자가 같은 맥주로 각자의 좋아요를 한다.
        #사용자 1
        Given   access: '1234', refresh: '5678' 회원가입된 유저의 토큰이 발급 되어있다.
        And     Access Token 사용 : '1234'

        When    좋아요한 맥주 Count를 요청하면
        Then    좋아요한 맥주 Count는 0개이다.
        When    3 맥주 좋아요를 요청하면
        Then    요청이 성공한다.
        When    좋아요한 맥주 Count를 요청하면
        Then    좋아요한 맥주 Count는 1개이다.
        When    좋아요한 맥주 목록을 요청하면
        Then    1개가 조회된다.

        # 사용자 2
        Given   access: '12345', refresh: '56789' 회원가입된 유저의 토큰이 발급 되어있다.
        And     Access Token 사용 : '12345'

        When    좋아요한 맥주 Count를 요청하면
        Then    좋아요한 맥주 Count는 0개이다.
        When    3 맥주 좋아요를 요청하면
        Then    요청이 성공한다.
        When    좋아요한 맥주 Count를 요청하면
        Then    좋아요한 맥주 Count는 1개이다.
        When    좋아요한 맥주 목록을 요청하면
        Then    1개가 조회된다.

    Scenario:  좋아요한 맥주에서 리뷰와 함께 조회
        Given   access: '1234', refresh: '5678' 회원가입된 유저의 토큰이 발급 되어있다.
        And     Access Token 사용 : '1234'

        When    3 맥주에 맛 4,5,6 리뷰 작성을 요청하면
        Then    요청이 성공한다.
        When    3 맥주 좋아요를 요청하면
        Then    요청이 성공한다.
        When    좋아요한 맥주 목록을 요청하면
        Then    1개가 조회된다.
        Then    맥주 목록에서 첫번째 맥주에 리뷰가 존재한다.
