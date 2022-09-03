Feature: Review
    Scenario: 리뷰 맛 TOP3
        Given   access: '1234', refresh: '5678' 회원가입된 유저의 토큰이 발급 되어있다.
        And     Access Token 사용 : '1234'

        When    '에일의 정석' 맥주에 맛 1,2,3 리뷰 작성을 요청하면
        Then    요청이 성공한다.

        Given   access: '7777', refresh: '56781' 회원가입된 유저의 토큰이 발급 되어있다.
        And     Access Token 사용 : '7777'

        When    '에일의 정석' 맥주에 맛 4,5,6 리뷰 작성을 요청하면
        Then    요청이 성공한다.

        Given   access: '8888', refresh: '56782' 회원가입된 유저의 토큰이 발급 되어있다.
        And     Access Token 사용 : '8888'
        When    '에일의 정석' 맥주에 맛 4,5,6 리뷰 작성을 요청하면

        When    '에일의 정석' 맥주 맛 TOP3를 요청하면
        Then    요청이 성공한다.
        And     3개가 조회된다.
        And     맛 TOP3는 4,5,6 이다.

    Scenario: 리뷰 등록, 삭제, 목록 조회
        Given   access: '1234', refresh: '5678' 회원가입된 유저의 토큰이 발급 되어있다.
        And     Access Token 사용 : '1234'

        # 대한민국 -> 덴마크
        Given   '에일의 정석' 맥주에 맛 4,5,6 리뷰 작성을 요청하면
        Then    요청이 성공한다.
        When    '에일의 정석' 맥주 나의 리뷰 조회를 요청하면
        Then    리뷰의 출발지는 '대한민국' 이다.
        Then    리뷰의 도착지는 '덴마크' 이다.

        # 덴마크 -> 미국
        Given   '미국 맥주' 맥주에 맛 4,5,6 리뷰 작성을 요청하면
        Then    요청이 성공한다.
        When    '미국 맥주' 맥주 나의 리뷰 조회를 요청하면
        Then    리뷰의 출발지는 '덴마크' 이다.
        Then    리뷰의 도착지는 '미국' 이다.

        # 리뷰 삭제 되어 대한민국 -> 미국
        Given   '에일의 정석' 맥주 나의 리뷰 삭제를 요청하면
        Then    요청이 성공한다.
        When    '미국 맥주' 맥주 나의 리뷰 조회를 요청하면
        Then    리뷰의 출발지는 '대한민국' 이다.
        Then    리뷰의 도착지는 '미국' 이다.

        # 덴마크 -> 제주도
        Given   '제주 슬라이스' 맥주에 맛 1,2,3 리뷰 작성을 요청하면
        Then    요청이 성공한다.
        When    '제주 슬라이스' 맥주 나의 리뷰 조회를 요청하면
        Then    리뷰의 출발지는 '미국' 이다.
        Then    리뷰의 도착지는 '대한민국' 이다.

        When    나의 맥주 리뷰 목록을 조회하면
        Then    요청이 성공한다.
        And     2개가 조회된다.
