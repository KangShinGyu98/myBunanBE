# 프로젝트 소개
- 해외 좋은 곡들을 번역할 때, 원곡의 느낌을 살려서 발음과 뜻을 비슷하게 번역하는 것을 번안이라고 합니다.
- 해당 사이트에서는 누구나 좋아하는 해외의 곡을 올리고, 가사를 번안해볼 수 있도록 제작했습니다. 
- 혼자서 기획부터 배포까지 풀스택 웹사이트를 만들어보기 위해서 시작한 프로젝트의 백엔드 리포입니다. 

# 기술스택 
 - Java 17
 - Spring 2.7.15
 - Mysql 8.0
   
# 배포 
 - AWS EC2
 - RDS
 - CodeDeploy
 - Github Action
---
# 특이사항, 자랑할 거리 
 1. Github action, AWS CodeDeploy 서비스를 활용해서 CI/CD 자동화를 구축했습니다.
    - 산출물 : [github action workflow 파일](https://github.com/KangShinGyu98/myBunanBE/blob/main/.github/workflows/deploy.yml) 
 2. QueryDsl 로 동적쿼리를 관리합니다. (사이트의 필터링 검색 기능)
    - 산출물 : [MusicQuerydslRepositoryImpl.java](https://github.com/KangShinGyu98/myBunanBE/blob/main/src/main/java/com/cuttingEdge/bunan/repository/MusicQuerydslRepositoryImpl.java)
 3. Exception 처리를 했습니다.
 4. Dto 를 record로 관리합니다. 
 5. smtp 를 활용한 이메일 인증 기능이 있습니다.
 6. Github Secret 으로 Secret 값을 관리합니다.
    


 
