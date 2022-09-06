Feature: Beer Search
    Scenario: 로그인한 상태로 다양한 검색 조건을 등록해 맥주를 검색 한다.
        Given   access: '1234', refresh: '5678' 회원가입된 유저의 토큰이 발급 되어있다.
        And     Access Token 사용 : '1234'

        # 국가
        Given   [맥주 검색 조건 초기화]

        And     맥주 검색 조건에 국가 ID 1 추가
        When    맥주 검색을 요청하면
        Then    요청이 성공한다.
        And     맥주 검색 결과의 검색된 갯수는 1개 이다.

        Given   맥주 검색 조건에 국가 ID 2 추가
        When    맥주 검색을 요청하면
        Then    요청이 성공한다.
        Then    맥주 검색 결과의 검색된 갯수는 2개 이다.

        # 맥주 종류 에일 추가
        # 검색 조건을 초기화 하지 않고 국가와 함께 검색한다.
        Given     맥주 검색 조건에 맥주 종류 ID 1 추가
        When      맥주 검색을 요청하면
        Then      요청이 성공한다.
        And       맥주 검색 결과의 검색된 갯수는 1개 이다.

        # 맥주 종류 페일 에일 추가
        Given     맥주 검색 조건에 맥주 종류 ID 2 추가
        When      맥주 검색을 요청하면
        Then    요청이 성공한다.
        And       맥주 검색 결과의 검색된 갯수는 2개 이다.

        # 키워드 제주
        Given     [맥주 검색 조건 초기화]

        And       맥주 검색 조건에 키워드 '제주' 등록
        When      맥주 검색을 요청하면
        And       요청이 성공한다.
        Then      맥주 검색 결과의 검색된 갯수는 1개 이다.
        And       맥주 검색 결과의 0번 맥주의 이름은 '제주 슬라이스' 이다.

        # 정렬 리뷰 많은순
        Given    [맥주 검색 조건 초기화]

        And      '에일의 정석' 맥주에 맛 1,2,3 리뷰 작성을 요청하면
        And      맥주 검색 조건에 정렬 'REVIEW' 등록
        When     맥주 검색을 요청하면
        Then     요청이 성공한다.
        And      맥주 검색 결과의 0번 맥주의 이름은 '에일의 정석' 이다.

        # 정렬 이름순
        Given    [맥주 검색 조건 초기화]

        And      맥주 검색 조건에 정렬 'NAME' 등록
        When     맥주 검색을 요청하면
        Then     요청이 성공한다.
        And      맥주 검색 결과의 0번 맥주의 이름은 '제주 슬라이스' 이다.

        # 정렬 도수 높은순
        Given    [맥주 검색 조건 초기화]

        And      맥주 검색 조건에 정렬 'ALCOHOL_HIGHEST' 등록
        When     맥주 검색을 요청하면
        Then     요청이 성공한다.
        And      맥주 검색 결과의 0번 맥주의 이름은 '에일의 정석' 이다.

        # 정렬 도수 낮은순
        Given    [맥주 검색 조건 초기화]

        And      맥주 검색 조건에 정렬 'ALCOHOL_LOWEST' 등록
        When     맥주 검색을 요청하면
        Then     요청이 성공한다.
        And      맥주 검색 결과의 0번 맥주의 이름은 '제주 슬라이스' 이다.

    Scenario: 로그인 하지 않고 검색한다.
        Given    맥주 검색 조건에 국가 ID 1 추가
        And      맥주 검색 조건에 맥주 종류 ID 1 추가
        And      맥주 검색 조건에 키워드 '제주' 등록
        And      맥주 검색 조건에 정렬 'ALCOHOL_HIGHEST' 등록

        When     맥주 검색을 요청하면
        Then     요청이 성공한다.
        And      맥주 검색 결과의 검색된 갯수는 1개 이다.
        And      맥주 검색 결과의 0번 맥주의 이름은 '제주 슬라이스' 이다.
