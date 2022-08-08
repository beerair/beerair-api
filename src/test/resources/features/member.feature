Feature: 회원 테스트

  Scenario: 유저 기본 Flow (cucumber 세팅용)
    When    'username1234@google.com' 이메일로 회원가입 했을때
    Then    요청이 성공한다.
    And     'ok'가 반환된다.
