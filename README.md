# 공구 프로젝트
---
## Skill
- Java 21
- SpringBoot 3
- Mysql 8
- JPA
- Redis

---
Database DCL

    use mysql;

    create database gonggu;
    
    show databases;
    
    create user gonggu@'%' identified by 'gonggu';
    
    select user, host from user;
    
    grant all on *.* to gonggu@'%';
    
    flush privileges;
    
    show grants for gonggu@'%';
---
### Git 커밋 메세지 규칙 <br>
- feat : 새로운 기능에 대한 커밋<br>
- fix : 버그 수정에 대한 커밋<br>
- build : 빌드 관련 파일 수정 / 모듈 설치 또는 삭제에 대한 커밋<br>
- chore : 그 외 자잘한 수정에 대한 커밋<br>
- ci : ci 관련 설정 수정에 대한 커밋<br>
- docs : 문서 수정에 대한 커밋<br>
- style :코드 스타일 혹은 포맷 등에 관한 커밋<br>
- refactor : 코드 리팩토링에 대한 커밋<br>
- test : 테스트 코드 수정에 대한 커밋<br>
- perf : 성능 개선에 대한 커밋<br><br>

ex) [feat] xxxx 기능 개발