# 키친포스

## 1단계 리팩터링 요구 사항
* [x] kitchenpos 패키지의 코드를 보고 키친포스의 요구 사항을 README.md에 작성한다.
* [x] 정리한 키친포스의 요구 사항을 토대로 테스트 코드를 작성한다.
  - [x] 모든 Business Object에 대한 테스트 코드를 작성한다.
  - [x] (Optional)@SpringBootTest를 이용한 통합 테스트 코드를 작성한다. 
  - [ ] (Optional)@ExtendWith(MockitoExtension.class)를 이용한 단위 테스트 코드를 작성한다.

## 2단계 리팩터링 요구 사항
* 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드를 분리해 단위 테스트 가능한 코드에 대해 단위 테스트를 구현한다.
  - Spring Data JPA 사용 시 spring.jpa.hibernate.ddl-auto=validate 옵션을 필수로 준다.
- [x] JdbcTemplate 대신 JPA를 사용하도록 변경한다.
- [x] 모델에 setter 메서드 넣지 않기

## 요구 사항
### Order API(주문)
* 주문 상태는 요리중, 식사중, 완료 3가지가 있다.
* 주문 당시의 시간을 알 수 있어야한다.
* 주문에는 메뉴들과 각 메뉴의 수량을 포함한다.
* 기능
* [x] 주문 생성
  * [x] 요청 주문 항목들은 0개일 수 없다. (0개라면 예외가 발생)
  * [x] 요청 주문 항목들은 존재하지 않는 항목이라면 예외가 발생한다.
  * [x] 주문을 한 테이블이 존재하지 않는 테이블이라면 예외가 발생한다.
  * [x] 주문을 한 테이블이 비어있으면 예외가 발생한다.(손님이 앉아 있어야함)
  * [x] 주문이 생성되면 주문 상태는 요리중(COOKING)이 된다.
  * [x] 주문 시간은 현재 시간을 기준으로 기록한다.
  * [x] 데이터베이스에 주문을 저장한다.
  * [x] 주문 항목도 데이터베이스에 저장해야한다. (orderId를 포함시킨)
* [x] 주문 전체 조회
  * [x] 주문 목록 전체를 조회한다.
  * [x] 각 주문에는 주문 항목을 포함해야 한다.
* [x] 주문 상태 변경
  * [x] 주문의 상태를 변경할 수 있어야한다.
  * [x] 요청 orderId가 데이터베이스에 존재하지 않으면 예외가 발생한다.
  * [x] 변경하려는 주문의 상태가 주문완료(COMPLETE)라면 예외가 발생한다.
  * [x] 변경한 주문의 상태를 데이터베이스에 반영한다.

### Product API(상품)
* 상품은 이름과 가격을 가진다.
* 상품의 가격은 0 이상의 숫자여야한다.
* 기능
* [x] 상품 등록
  * [x] 상품의 가격은 0보다 작을 수 없다.
  * [x] 상품을 저장한다.
* [x] 상품 전체 조회

### Menu API (메뉴)
* 메뉴는 이름, 가격, 메뉴 그룹, 상품들을 가진다.
* 메뉴의 가격은 메뉴에 포함된 상품들의 가격 총합보다 작거나 같아야한다.
* (메뉴의 가격 <= 메뉴에 포함된 상품들의 가격 총합)
* 메뉴 가격은 0이상의 양수여야한다.
* 메뉴는 어떤 메뉴 그룹에 항상 포함되어야한다.
* 기능
* [x] 메뉴 생성
  * [x] 가격은 null 이거나 0보다 작을 수 없다.
  * [x] 추가하려는 메뉴 그룹이 존재하지 않으면 예외가 발생한다.
  * [x] 메뉴의 가격은 메뉴에 속하는 상품의 가격 총합과 일치해야한다.
  * [x] 메뉴를 데이터베이스에 추가한다.
  * [x] 메뉴 상품들에 메뉴 ID를 추가해서 데이터베이스에 추가해야한다.
* [x] 메뉴 전체 조회
  * [x] 메뉴 상품들과 함께 전체 메뉴 결과를 반환해야한다.

### Menu Group API (메뉴 그룹 관련 기능)
* 메뉴 그룹은 이름을 가진다.
  * ex. 추천메뉴, 인기메뉴, 식사류, 사이드메뉴 등등
* 기능
* [x] 메뉴 그룹 생성
* [x] 메뉴 그룹 전체 조회

### Table API (테이블 기능)
* 테이블은 앉아있는 손님의 인원 수, 테이블이 활성화 상태인지를 알 수 있어야한다.
* 테이블은 다른 테이블과 그룹을 만들 수 있다.
* [x] 테이블 생성
  * [x] 테이블 생성시에는 그룹화가 존재하지 않는다.
  * [x] 테이블 생성시 상태는 빈 테이블이다.
  * [x] 테이블 생성시 인원 수는 0이다.
  * [x] 테이블을 데이터베이스에 저장해야한다.
* [x] 테이블 전체 조회
  * [x] 테이블 전체를 조회할 수 있어야한다.
* [x] 테이블 상태를 변경 (비 활성화 테이블 <-> 활성화된 테이블)
  * [x] 테이블의 상태를 변경할 수 있다.
  * [x] 상태 변경을 요청한 테이블이 실제 존재하는지 확인해야한다.
  * [x] 테이블이 테이블 그룹에 속한 상태면 테이블의 상태를 변경할 수 없다.
  * [x] 변경 요청한 테이블의 주문 상태가 요리중, 식사중 이라면 테이블 상태를 변경할 수 없다.
* [x] 테이블 손님 인원 수 수정
  * [x] 테이블의 인원 수를 수정할 수 있어야한다.
  * [x] 인원수는 음수 일 수 없다.(인원수는 최소한 0이어야 한다.)
  * [x] 인원 수 수정 시 존재하는 테이블인지 확인해야한다.
  * [x] 테이블이 비어있는(비활성화) 상태면 인원 수를 수정할 수 없다.

### TableGroup API (테이블 그룹화 관련 기능)
* 2개 이상의 테이블을 그룹화 시킴 (1개의 테이블을 그룹화시킬 수 없다)
* 그룹화 시키는 대상 테이블은 존재하는 테이블이어야 한다.
* 그룹화 시킨 시간을 알 수 있어야한다.
* 그룹화는 비활성화 테이블만 가능하다.
* 그룹화는 해제할 수 있어야한다. (단, 테이블 중 하나라도 주문 상태가 요리중, 식사중 이라면 해제할 수 없다.)
* [x] 테이블 그룹화
  * [x] 속한 테이블이 0개거나 1개라면 예외가 발생한다. (그룹화 불가능)
  * [x] 그룹화하려는 테이블이 데이터베이스에 존재하지 않으면 예외가 발생한다.
  * [x] 그룹화하려는 테이블이 활성화 상태거나 이미 그룹화됐으면 예외가 발생한다.
  * [x] 데이터베이스에 테이블 그룹화를 저장한다.
  * [x] 그룹화된 테이블에 table group id를 추가해서 반영한다.
* [x] 테이블 그룹화 해제
  * [x] 그룹화 하려는 테이블의 주문이 요리중, 식사중 이라면 그룹화를 해제할 수 없다.
  * [x] 그룹화를 해제한다.

## 용어 사전

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 상품 | product | 메뉴를 관리하는 기준이 되는 데이터 |
| 메뉴 그룹 | menu group | 메뉴 묶음, 분류 |
| 메뉴 | menu | 메뉴 그룹에 속하는 실제 주문 가능 단위 |
| 메뉴 상품 | menu product | 메뉴에 속하는 수량이 있는 상품 |
| 금액 | amount | 가격 * 수량 |
| 주문 테이블 | order table | 매장에서 주문이 발생하는 영역 |
| 빈 테이블 | empty table | 주문을 등록할 수 없는 주문 테이블 |
| 주문 | order | 매장에서 발생하는 주문 |
| 주문 상태 | order status | 주문은 조리 ➜ 식사 ➜ 계산 완료 순서로 진행된다. |
| 방문한 손님 수 | number of guests | 필수 사항은 아니며 주문은 0명으로 등록할 수 있다. |
| 단체 지정 | table group | 통합 계산을 위해 개별 주문 테이블을 그룹화하는 기능 |
| 주문 항목 | order line item | 주문에 속하는 수량이 있는 메뉴 |
| 매장 식사 | eat in | 포장하지 않고 매장에서 식사하는 것 |
