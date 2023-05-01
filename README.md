# idle
방치형 게임 - 시간마다 돈이 모이게 되고, 모인 돈으로 무기를 뽑고, 업그레이드하는 게임
- 1분마다 1000원씩 모이게 된다.
- 상점에서 랜덤 무기 뽑기를 구매하여 무기를 뽑을 수 있다.
- 무기엔 등급이 존재한다. (normal, rare, epic, unique, legendary, end)
- legendary등급 무기부터 star가 존재한다.
- star는 같은 등급(legendary 또는 end 등급), 같은 종류의 무기 1개가 있으면 star를 올릴 수 있다.
- 등급 업 확률이 존재한다. (normal -> rare: 1%, rare -> epic: 0.5%, epic -> unique: 0.1%)
- legendary 등급 업은 normal, rare, epic, unique 각 각 upgrade가 100회 이상 인 같은 종류의 무기들을 합성하면 된다.
- end 등급 업은 legendary등급인 무기가 10성 이상이 되면 등급 업 할 수 있다.

## Skills
- Java17, Spring Boot, Sprinb Data JPA, MySQL

## ERD
![idle](https://user-images.githubusercontent.com/72899707/233398865-71094549-8408-4a8d-a73b-21da1e27960f.png)

## did
- multi-module을 이용한 의존성 관리

- OAuth2를 이용하여 카카오 소셜 로그인 기능 구현

- 각 사용자들의 마지막 update 시간을 저장한 후 각 사용자가 접속 하거나 갱신할때의 시간과 저장된 마지막 update 시간을 비교해 일괄 처리
