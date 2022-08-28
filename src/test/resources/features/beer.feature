Feature: Beer
    Scenario:   안마셔본(리뷰가 없는) 맥주 추천 받기
        Given   access: '1234', refresh: '5678' 회원가입된 유저의 토큰이 발급 되어있다.
        And     Access Token 사용 : '1234'

        When    맥주 추천 목록을 요청하면
        Then    요청이 성공한다.
