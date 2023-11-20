# PersonalProject_1

# 프로젝트 설계

## 1. 프로젝트 기획

#### 1. Use-Case 다이어그램
![](https://velog.velcdn.com/images/whdrb2643/post/06e9a8c4-71c5-42d8-83ed-f95690c16b20/image.png)

#### 2. API
|기능 |메서드|Url|Request|Response|
|----|:----:|:----:|:----:|:----:|
|게시글 등록	   |Post  |/api/posting     | {"postingName" : "gogogogogo", "userName" : "gogo","password" : "1234","postingContent" : "Let's go!"} |등록된 게시글 |
|게시글 조회	   |GET   |/api/posting/{id}|   | 선택한 게시글 
|게시글 전체 조회|GET   |/api/postings    |   | 게시글 전체 목록
|게시글 수정    |PUT   |/api/posting/{id}/{password} |{"postingName" : "수정할게용", "userName" : "양배추", "postingContent" : "수정완료했네용" }   | 수정된 게시글
|게시글 삭제    |DELETE|/api/posting/{id}/{password}|   | 삭제된 게시글 id값

#### 3. ERD
![](https://velog.velcdn.com/images/whdrb2643/post/eed7f826-d00f-491b-9f12-be682b2c0fa5/image.png)

#### 4. 구현

[PersonalProject GitHub 주소](https://github.com/Kim-Jong-Gyu/PersonalProject_1)

------

## 20231117
#### 1. 수정한 API
[수정한 API](https://documenter.getpostman.com/view/20661979/2s9YXpVyVo)

#### 2. 수정 사항
- 변수명과 메서드명 수정
    - Posting -> post
- url path는 집합명사인 posts로 수정
- postingName 컬럼에서 제목은 중복이 가능하니 `Unique=true` 옵션 삭제
-  게시글 수정할 때, password 는 보안데이터 이기 때문에 path param 이나 query param 으로 받지 않고 body로 받도록 수정
- Delete 요청일 때, Password는 HttpHeader에 담아서 전송
- 전체 목록 조회할 때, 날짜기준 오름차순으로 나오도록 수정

## 20231120

#### 1. 수정사항
- ResponseEntity를 이용한 반환
- Exception 추가
- Comment 관련 기능 추가
    - Entity간의 연관관계 설정 
- Spring Security + filter 를 통한 JWT 토큰 로그인 회원가입 구현

#### 2. ERD
![Project ERD](https://github.com/Kim-Jong-Gyu/PersonalProject_1/assets/62927374/27bf0150-16d3-4cf6-b761-d045586c50e2)

#### 3. API
[ProjectAPI] (https://documenter.getpostman.com/view/20661979/2s9YXpVyVo)
