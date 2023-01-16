# API 명세서

| Index | Sub-Index | Method | URI | Description |
| --- | --- | --- | --- | --- |
| 1 | 1 | POST | /users | 회원 가입 |
| 1 | 2 | GET | /users | 회원 목록 조회 |
| 1 | 3 | GET | /users/{:id} | 회원 정보 조회 |
| 1 | 4 | PATCH | /users/{:id} | 회원 정보 수정 |
| 1 | 5 | DELETE | /users/{:id} | 회원 탈퇴 |
| 1 | 6 | GET | /users/id-check/{:id} | 아이디 중복 확인 |
| 1 | 7 | GET | /users/{:userId}/debates | 현재 유저의 토론 참여 기록 조회 |
| 2 | 1 | POST | /auth/ | 로그인 |
| 2 | 2 | GET | /auth/ | 로그아웃 |
| 2 | 3 | PATCH | /auth/{:userId} | 유저 권한 변경 |
| 3 | 1 | POST | /debate | 토론 생성 |
| 3 | 2 | GET | /debate | 토론 목록 조회 |
| 3 | 3 | GET | /debate/{:id} | 토론 정보 조회 |
| 3 | 4 | PATCH | /debate/{:id} | 토론 정보 수정 |
| 3 | 5 | DELETE | /debate/{:id} | 토론 삭제 |
|  |  |  |  |  |
|  |  |  |  |  |
|  |  |  |  |  |
|  |  |  |  |  |
|  |  |  |  |  |
|  |  |  |  |  |
|  |  |  |  |  |
|  |  |  |  |  |
|  |  |  |  |  |

## 도메인

- users
- debate
- chatting
- evaluation (토론 찬성/반대 패널에 대한 상호 평가)
- conclusion (회의 결과)