Feature: 회원 테스트

  Scenario: 소셜 사용자 첫 로그인 후 사용자 정보 등록
    When    'DEPROMEET' 소셜 로그인을 요청 하면
    Then    요청이 성공한다.
    And     반환된 권한에 'USER'가 포함된다.
    And     반환된 권한에 'MEMBER'가 포함되지 않는다.
    When    사용자 정보 등록을 요청하면
    Then    요청이 성공한다.
    And     반환된 권한에 'USER'가 포함되지 않는다.
    And     반환된 권한에 'MEMBER'가 포함된다.
