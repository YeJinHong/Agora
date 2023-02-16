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
| 4	| 1	| POST	| /chat	| 채팅 내용 저장 |
| 4 | 2 | GET | /chat | 채팅 목록 조회 |
| 4	| 3	| GET | /chat/{:id} | 채팅 정보 조회 |
| 4	| 4 | DELETE | /chat/{:id} | 채팅 삭제 |
| 5 | 1	| POST | /evaluation/{:debateId} | 종료된 토론에 대한 상호 평가표 생성 |
| 5	| 2	| GET | /evaluation/{:debateId} | 종료된 토론에 대한 상호 평가표 조회 |
| 5	| 3	| PATCH | /evaluation/{:debateId} | 종료된 토론에 대한 상호 평가표 수정 |
| 5	| 4	| DELETE | /evaluation/{:debateId} | 종료된 토론 평가표 삭제 |
| 5 | 5 | GET	| /evaluation?limit=20&offset=100 | 종료된 토론 평가표 조회 |
| 6	| 1	| POST | /conclusion/{:debateId} | 특정 토론의 최종 결과 생성 (스크립트 생성 및 평가) |
| 6	| 2	| GET | /conclusion/{:debateId} | 특정 토론의 최종 결과 조회 |
| 6 | 3	| PATCH | /conclusion/{:debateId} |	특정 토론의 최종 결과 수정 |
| 7	| 1 | POST | /vote | 청중 투표 결과 생성 |
| 7 | 2 | GET | /vote | 청중 투표 결과 목록 조회 |
| 7 | 3 | DELETE | /vote/{:voteId} | 청중 투표 결과 삭제 |
| 8	| 1	| POST | /dashboard | 토론 결과 대시보드 생성 |
| 8	| 2	| GET |	/dashboard | 토론 결과 대시보드 목록 조회 |
| 8 | 3 | GET |	/dashboard/{:resultId} | 토론 결과 대시보드 조회 |
| 8 | 4 | DELETE | /dashboard/{:resultId} | 토론 결과 대시보드 삭제 |
| 8 | 5 | PATCH | /dashboard/{:resultId} | 토론 결과 대시보드 수정 |

## 도메인

- users
- debate
- chatting
- evaluation (토론 찬성/반대 패널에 대한 상호 평가)
- conclusion (회의 결과)
