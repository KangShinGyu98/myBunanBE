
# 프로젝트 소개
- 해외 좋은 곡들을 번역할 때, 원곡의 느낌을 살려서 발음과 뜻을 비슷하게 번역하는 것을 번안이라고 합니다.
- 해당 사이트에서는 누구나 좋아하는 해외의 곡을 올리고, 가사를 번안해볼 수 있도록 제작했습니다. 
- 혼자서 기획부터 배포까지 풀스택 웹사이트를 만들어보기 위해서 시작한 프로젝트의 백엔드 리포입니다. 
- 사이트 링크 - https://www.bunan.co.kr/

# 기술스택 
- back-end : spring boot 2.7.15, Query DSL, Java 17 amazon corretto
- Database: MySQL
- Server: AWS EC2, RDS
- CI: Github actions, AWS CodeDeploy
---
# 특이사항, 자랑할 거리 
- 프론트엔드부터 백엔드까지 **1인으로 개발**하여 배포하는 경험을 얻음
- JPA 를 활용하여 H2로 빠르게 개발 후 Mysql 및 RDS로 **DB migration**
- 검색어 필터링을 위해서 **Query DSL 을 활용한 동적 쿼리** 기능 구현
    - 제목으로 검색, 국가별, 장르별 검색 및 좋아요, 등록 순서대로 Sorting, 기능 구현
- **SMTP** 를 사용하여 로그인 시에 이메일 인증 도입
- [Swagger 링크](https://bunanbe.shop/swagger-ui/index.html)
# 인프라 구조
![map](https://github.com/KangShinGyu98/myBunanBE/assets/103213494/68d9abf2-929d-495b-adab-16c0ba7e3435)

# ERD
![erd](https://github.com/KangShinGyu98/myBunanBE/assets/103213494/e0cfabc6-f241-4454-a9e0-ee19f4084be9)
