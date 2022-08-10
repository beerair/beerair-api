Feature: 회원 테스트

  Scenario: 소셜 사용자 첫 로그인 후 사용자 정보 등록
    Given   {소셜:'KAKAO',토큰:'1234'}으로 'kakao1234@kakao.com' 이메일이 등록 되어 있다.
    When    {소셜:'KAKAO',토큰:'1234'}으로 소셜 로그인을 요청하면
    Then    요청이 성공한다.
