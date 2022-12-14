Feature: Review
    #에일의 정석 Id = 1
    #미국 맥주 Id = 2
    #제주 슬라이스 Id = 3

    Scenario: 리뷰 작성하면 레벨과 경험치 증가
        Given   access: '1234', refresh: '5678' 회원가입된 유저의 토큰이 발급 되어있다.
        And     Access Token 사용 : '1234'

        When    1 맥주에 맛 1,2,3 리뷰 작성을 요청하면
        Then    요청이 성공한다.

        When    나의 정보를 요청 하면
        Then    요청이 성공한다.
        And     나의 정보에서 티어는 2 이다.

    Scenario: 리뷰 맛 TOP3
        Given   access: '1234', refresh: '5678' 회원가입된 유저의 토큰이 발급 되어있다.
        And     Access Token 사용 : '1234'

        When    1 맥주에 맛 1,2,3 리뷰 작성을 요청하면
        Then    요청이 성공한다.

        Given   access: '7777', refresh: '56781' 회원가입된 유저의 토큰이 발급 되어있다.
        And     Access Token 사용 : '7777'

        When    1 맥주에 맛 4,5,6 리뷰 작성을 요청하면
        Then    요청이 성공한다.

        Given   access: '8888', refresh: '56782' 회원가입된 유저의 토큰이 발급 되어있다.
        And     Access Token 사용 : '8888'
        When    1 맥주에 맛 4,5,6 리뷰 작성을 요청하면

        When    1 맥주 맛 TOP3를 요청하면
        Then    요청이 성공한다.
        And     3개가 조회된다.
        And     맛 TOP3는 4,5,6 이다.

        When    1 맥주 맛 TOP6를 요청하면
        Then    요청이 성공한다.
        And     6개가 조회된다.

    Scenario: 리뷰 등록, 삭제, 목록 조회, 특정 맥주에 대한 리뷰 조회 (Cursor 방식으로 2개씩 조회)
        Given   access: '1234', refresh: '5678' 회원가입된 유저의 토큰이 발급 되어있다.
        And     Access Token 사용 : '1234'

        # 대한민국 -> 덴마크
        Given   1 맥주에 맛 4,5,6 리뷰 작성을 요청하면
        Then    요청이 성공한다.
        When    1 맥주 나의 리뷰 조회를 요청하면
        Then    리뷰의 출발지는 '대한민국' 이다.
        Then    리뷰의 도착지는 '덴마크' 이다.

        # 덴마크 -> 미국
        Given   2 맥주에 맛 4,5,6 리뷰 작성을 요청하면
        Then    요청이 성공한다.
        When    2 맥주 나의 리뷰 조회를 요청하면
        Then    리뷰의 출발지는 '덴마크' 이다.
        Then    리뷰의 도착지는 '미국' 이다.

        # 리뷰 삭제 되어 대한민국 -> 미국
        Given   1 맥주 나의 리뷰 삭제를 요청하면
        Then    요청이 성공한다.
        When    2 맥주 나의 리뷰 조회를 요청하면
        Then    리뷰의 출발지는 '대한민국' 이다.
        Then    리뷰의 도착지는 '미국' 이다.

        # 덴마크 -> 제주도
        Given   3 맥주에 맛 1,2,3 리뷰 작성을 요청하면
        Then    요청이 성공한다.
        When    3 맥주 나의 리뷰 조회를 요청하면
        Then    리뷰의 출발지는 '미국' 이다.
        Then    리뷰의 도착지는 '대한민국' 이다.

        When    나의 맥주 리뷰 목록을 조회하면
        Then    요청이 성공한다.
        And     2개가 조회된다.

        # 최근 리뷰 조회
        When    최근 리뷰 조회 2개를 요청하면
        Then    요청이 성공한다.
        And     2개가 조회된다.

        Given   -- 리스트 중 0번째 선택
        Then    리뷰의 출발지는 '미국' 이다.
        Then    리뷰의 도착지는 '대한민국' 이다.

        # 리뷰 등록
        Given   access: '2222', refresh: '3333' 회원가입된 유저의 토큰이 발급 되어있다.
        And     Access Token 사용 : '2222'
        And     2 맥주에 맛 4,5,6 리뷰 작성을 요청하면

        # 1개씩 2번 Cursor 방식 페이징 조회
        Given   -- Cursor 초기화
        When    2 맥주 리뷰 목록 1개 조회를 요청하면
        Then    요청이 성공한다.
        And     1개가 조회된다.

        Given   -- Cursor 저장
        When    2 맥주 리뷰 목록 1개 조회를 요청하면
        Then    요청이 성공한다.
        And     1개가 조회된다.
        And     다음 Cursor를 반환하지 않는다.
