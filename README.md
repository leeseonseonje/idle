# idle
방치형 게임 - 시간마다 돈이 모이게 되고, 모인 돈으로 무기를 뽑고, 업그레이드 하는 게임

```
사용 기술: Java17, Spring Boot, Sprinb Data JPA, MySQL
```

## ERD
![idle](https://user-images.githubusercontent.com/72899707/233398865-71094549-8408-4a8d-a73b-21da1e27960f.png)

## did
- multi-module을 이용한 의존성 관리

- OAuth2를 이용하여 카카오 소셜 로그인 기능 구현

- 각 사용자들의 마지막 update 시간을 저장한 후 각 사용자가 접속 하거나 갱신할때의 시간과 저장된 마지막 update 시간을 비교해 일괄 처리
