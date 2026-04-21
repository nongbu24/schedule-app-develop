# API 명세

## 1. 일정 (Schedule) API

### 1-1. 일정 생성

| 항목                       | 내용                                                                                                                                                                    |
|--------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **URL**                  | `POST /schedules`                                                                                                                                                     |
| **Request Body**         | `user` (User, 필수) <br> `title` (String, 필수) <br> `content` (String, 필수)                                                                                               |
| **Request Body (JSON)**  | { <br> "user": "유저", <br> "title": "제목", <br> "content": "내용" <br>}                                                                                                   |
| **Response**             | `201 Created`                                                                                                                                                         |
| **Response Body**        | `id`, `user`, `title`, `content`, `createdAt`, `modifiedAt`                                                                                                           |
| **Response Body (JSON)** | { <br> "id": 1, <br> "user": "유저", <br> "title": "제목", <br> "content": "내용", <br> "createdAt": "2026-04-20T12:00:00", <br> "modifiedAt": "2026-04-20T12:00:00" <br> } |
| **Error**                | `400 Bad Request` - 필수값 누락                                                                                                                                            |

### 1-2. 일정 조회

| 항목 | 내용                                                                                                                                                                                  |
| --- |-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **URL** | `GET /schedules`                                                                                                                                                                    |
| **Response** | `200 OK`                                                                                                                                                                            |
| **Response Body** | 일정 목록 배열 (`id`, `user`, `title`, `content`, `createdAt`, `modifiedAt`)                                                                                                              |
| **Response Body (JSON)** | [ <br> { <br> "id": 1, <br> "user": "유저", <br> "title": "제목", <br> "content": "내용", <br> "createdAt": "2026-04-20T12:00:00", <br> "modifiedAt": "2026-04-20T12:00:00" <br> } <br> ] |

### 1-3. 일정 단 건 조회

| 항목                       | 내용 |
|--------------------------| --- |
| **URL**                  | `GET /schedules/{scheduleId}` |
| **Path Variable**        | `scheduleId` (Long, 필수) |
| **Response**             | `200 OK` |
| **Response Body**        | 일정 목록 배열 |
| **Response Body (JSON)** | { <br> "id": 1, <br> "user": "유저", <br> "title": "제목", <br> "content": "내용", <br> "createdAt": "2026-04-20T12:00:00", <br> "modifiedAt": "2026-04-20T12:00:00" <br> } |

### 1-4. 일정 수정

| 항목 | 내용                                                                                                                                                                          |
| --- |-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **URL** | `PUT /schedules/{scheduleId}`                                                                                                                                               |
| **Path Variable** | `scheduleId` (Long, 필수)                                                                                                                                                     |
| **Request Body** | `title` (String, 필수) <br> `content` (String, 필수) <br>                                                                                                                       
| **Request Body (JSON)** | { <br> "title": "제목 바꿈", <br> "content": "내용 바꿈" <br> }                                                                                                                     
| **Response** | `200 OK`                                                                                                                                                                    |
| **Response Body** | 수정된 일정 정보 배열 (`id`, `user`, `title`, `content`, `createdAt`, `modifiedAt`)                                                                                                  |
| **Response Body (JSON)** | { <br> "id": 1, <br> "user": "유저", <br> "title": "제목 바꿈", <br> "content": "내용 바꿈", <br> "createdAt": "2026-04-20T12:00:00", <br> "modifiedAt": "2026-04-20T12:08:51" <br> } |
| **Error**                | `400 Bad Request` - 필수값 누락 
| 비고 | 제목과 내용만 수정 가능                                                                                                                                                               |

### 1-5. 일정 삭제

| 항목 | 내용 |
| --- | --- |
| **URL** | `DELETE /schedules/{scheduleId}`|
| **Path Variable** | `scheduleId` (Long, 필수) |
| **Response** | `204 NO_CONTENT`|

## 2. 유저 (User) API

### 2-1. 유저 생성

| 항목      | 내용                                                                                                                                                         |
|---------|------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **URL** | `POST /users`                                                                                                                                              |
| **Request Body** | `name` (String, 필수) <br> `email` (String, 필수)                                                                                                              |
| **Request Body (JSON)** | { <br> "name": "닉네임", <br> "email": "ex@ample.com" <br> }                                                                                                  |
| **Response** | `201 Created`                                                                                                                                              |
| **Response Body** | `id`, `name`, `email`, `createdAt`, `modifiedAt` |
| **Response Body (JSON)** | { <br> "id": 1, <br> "name": "닉네임", <br> "email": "ex@ample.com", <br> "createdAt": "2026-04-20T12:00:00", <br> "modifiedAt": "2026-04-20T12:00:00" <br> } |
| **Error** | `400 Bad Request` - 필수값 누락                                                                                                                                 |

### 2-2. 유저 전체 조회

| 항목 | 내용 |
| --- |-|
| **URL** | `GET /users` |
| **Response** | `200 OK` |
| **Response Body** | 유저 목록 배열 (`id`, `name`, `email`, `createdAt`, `modifiedAt`) |
| **Response Body (JSON)** | [ <br> { <br> "id": 1, <br> "name", "닉네임", <br> "email": "ex@ample.com", <br> "createdAt": "2026-04-20T12:00:00", <br> "modifiedAt": "2026-04-20T12:00:00" <br> } <br> ]

### 2-3. 유저 단 건 조회

| 항목 | 내용 |
| --- | --- |
| **URL** | `GET /users/{userId}` |
| **Path Variable** | `userId` (Long, 필수) |
| **Response** | `200 OK` |
| **Response Body** | 유저 목록 배열 |
| **Response Body (JSON)** | { <br> "id": 1, <br> "name", "닉네임", <br> "email": "ex@ample.com", <br> "createdAt": "2026-04-20T12:00:00", <br> "modifiedAt": "2026-04-20T12:00:00" <br> } |

### 2-4. 유저 수정

| 항목                      | 내용                                                                                             |
|-------------------------|------------------------------------------------------------------------------------------------|
| **URL**                 | `PUT /users/{userId}`                                                                          |
| **Path Variable**       | `userId` (Long, 필수)                                                                            |
| **Request Body**        | `name` (String, 필수)                                                                            |
| **Request Body (JSON)** | { <br> "name": "변경된 닉네임", <br> }                                                               |
| **Response**            | `200 OK`                                                                                       |
| **Response Body**       | 수정된 유저 정보 (`id`, `name`, `email`, `createdAt`, `modifiedAt`)                                   |
| **Request Body (JSON)** | { <br> "id": 1, <br> "name": "변경된 닉네임", <br> "email": "ex@ample.com", <br> "createdAt": "2026-04-20T12:00:00", <br> "modifiedAt": "2026-04-20T12:50:00" <br> } 
| **Error** | `400 Bad Request` - 필수값 누락                                                                     |
| 비고 | 유저 이름만 수정 가능                                                                                   |

### 2-5. 유저 삭제

| 항목 | 내용 |
| --- | --- |
| **URL** | `DELETE /users/{userId}` |
| **Path Variable** | `userId` (Long, 필수) |
| **Response** | `204 NO_CONTENT` |