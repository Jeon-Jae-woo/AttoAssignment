# ATTOResearch Backend 과제 테스트

## 주제
>‘호스트들의 Alive 상태 체크 및 모니터링 API 서버 개발'  

&nbsp; 

## 간단 설명
>호스트들의 **등록 관리, 상태 확인, 모니터링 결과 조회**를 할 수 있는 REST API 입니다  

&nbsp; 



## 1. 호스트 등록 관리
- 호스트 등록, 조회, 수정, 삭제
- endpoint
  - **[POST] /hosts**
  - **[GET] /hosts**
  - **[GET] /hosts/{id}**
  - **[PATCH] /hosts/{id}**
  - **[DELETE] /hosts/{id}**

### [POST] /hosts
- 호스트 등록 -  name, ip를 입력하여 등록합니다

- **Request Body**
```
{
  "name" : "local",
  "ip" : "127.0.0.1"
}
```
- **Response**
  - 성공
```
{
  "Message" : "Success"
}
```

### [GET] /hosts
- 호스트 전체 조회

- **Response**
  - id, ip, name, 등록 시간, 수정 시간이 반환됩니다
```
[
    {
        "id": 1,
        "ip": "127.0.0.1",
        "name": "local",
        "created_at": "2022-05-22T16:30:13",
        "updated_at": "2022-05-22T16:34:38"
    },
    {
        "id": 2,
        "ip": "223.130.200.107",
        "name": "naver",
        "created_at": "2022-05-22T16:30:13",
        "updated_at": "2022-05-22T16:33:48"
    },
    {
        "id": 3,
        "ip": "142.250.207.46",
        "name": "google",
        "created_at": "2022-05-22T16:30:13",
        "updated_at": "2022-05-22T16:34:39"
    }
]
```

### [GET] /hosts/{id}
- 호스트 단일 조회

- **Parameter**
  - 호스트의 id 값을 보내 조회를 할 수 있습니다

- **Response**
  - id, ip, name, 등록 시간, 수정 시간이 반환됩니다
```
{
    "id": 1,
    "ip": "127.0.0.1",
    "name": "local",
    "created_at": "2022-05-22T16:30:13",
    "updated_at": "2022-05-22T16:34:38"
}
```

### [PATCH] /hosts/{id}
- 호스트의 ip 혹은 name을 수정할 수 있습니다
- **Request Body**
```
{
  "ip" : "ipAddress",
  "name" : "new Name"
}
```

- **Response**
  - 성공
```
{
  "Message" : "Success"
}
```

### [DELETE] /hosts/{id}
- 호스트를 삭제할 수 있습니다
- **Parameter**
  - 호스트의 id 값을 보내 삭제할 수 있습니다
- **Response**
  - 성공
```
{
  "Message" : "Success"
}
```


## 2. 특정 호스트의 현재 Alive 상태 조회
- 특정 호스트의 alive 상태를 조회할 수 있습니다
- endpoint
  - **[GET] /hosts/{id}/alive**

### [GET] /hosts/{id}/alive
- alive 상태 조회 
- **Parameter**
  - 호스트 id를 통해 조회할 수 있습니다
- **Response**
  - ip, name, 호스트 상태가 반환 됩니다. (true(살아있는 상태), false(죽어있는 상태))
```
{
    "ip": "127.0.0.1",
    "name": "local",
    "aliveStatus": true
}
```

## 3. 호스트들의 Alive 모니터링 결과 조회
- 특정 호스트 혹은 전체 호스트의 alive 모니터링 결과를 조회할 수 있습니다
- endpoint
  - **[GET] /hosts/monitor**
  - **[GET] /hosts/monitor/{id}**

### [GET] /hosts/monitor
- 호스트 전체 모니터링 결과 조회
- **Response**
  - id, ip, name, 호스트 등록 시간, 호스트 수정 시간, 호스트 상태, 호스트 시간이 반환됩니다.
  - alive_time이 null인 경우는 호스트가 등록이 됐지만 한 번도 살아있던 적이 없는 경우입니다. ( 호스트 등록시에 alive_time 값이 들어가지 않습니다 )
```
[
    {
        "id": 1,
        "ip": "127.0.0.1",
        "name": "local",
        "created_at": "2022-05-22T16:30:13",
        "updated_at": "2022-05-22T16:34:38",
        "alive_status": true,
        "alive_time": "2022-05-22T18:19:21.173"
    },
    {
        "id": 2,
        "ip": "223.130.200.107",
        "name": "naver",
        "created_at": "2022-05-22T16:30:13",
        "updated_at": "2022-05-22T16:33:48",
        "alive_status": false,
        "alive_time": null
    },
    {
        "id": 3,
        "ip": "142.250.207.46",
        "name": "google",
        "created_at": "2022-05-22T16:30:13",
        "updated_at": "2022-05-22T16:34:39",
        "alive_status": true,
        "alive_time": "2022-05-22T18:19:22.065"
    }
]
```

### [GET] /hosts/monitor/{id}
- 특정 호스트의 모니터링 결과 조회
- **Response**
  - id, ip, name, 호스트 등록 시간, 호스트 수정 시간, 호스트 상태, 호스트 시간이 반환됩니다.
```
{
    "id": 3,
    "ip": "142.250.207.46",
    "name": "google",
    "created_at": "2022-05-22T16:30:13",
    "updated_at": "2022-05-22T18:19:22",
    "alive_status": true,
    "alive_time": "2022-05-22T18:24:06.012"
}
```

# 제약 및 사항
- DB
  - DDL SQL 파일 경로
    - src/main/resources/sqlFile/hostDDL.sql
  - MariaDB
    - 10.6.7
  - ApplicationProperties
    - 3307 PORT 및 DB User는 별도로 생성해서 사용

- spring boot
  - 2.7.0 version

- gradle, java 8 사용
