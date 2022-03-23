#!/bin/bash

# 사용자 계정
USER_1=user1@kakao.com 
PW_1=password
USER_2=user2@kakao.com 
PW_2=password
USER_3=user3@kakao.com 
PW_3=password

#로그인
echo "\n---start login---"
TOKEN_HEADER=$(curl -i -X POST http://localhost:8080/v1/login \
-H "Content-Type: application/json" \
-d '{"email": '"\"${USER_1}\""',"password": '"\"${PW_1}\""'}' | grep X-AUTH-TOKEN)

TOKEN="${TOKEN_HEADER:14}"

echo "token is"
echo $TOKEN

echo "\n---end login---"

#결재문서 생성1
echo "\n---start create document---"

curl -i -X POST http://localhost:8080/v1/api/documents \
-H "Content-Type: application/json" \
-H "X-AUTH-TOKEN: $TOKEN" \
-d \
'{ 
    "title": "this is title", 
    "content": "this is content", 
    "catagory": "BA", 
    "approver": [ 
        { 
            "email": "user2@kakao.com", 
            "name": "name2", 
            "priority": "1" 
        }, 
        { 
            "email": "user3@kakao.com", 
            "name": "name3", 
            "priority": "2" 
        } 
    ] 
}' 

echo "\n---end create document---"

#결재문서 생성2
echo "\n---start create document---"

curl -i -X POST http://localhost:8080/v1/api/documents \
-H "Content-Type: application/json" \
-H "X-AUTH-TOKEN: $TOKEN" \
-d \
'{ 
    "title": "this is title", 
    "content": "this is content", 
    "catagory": "BA", 
    "approver": [ 
        { 
            "email": "user2@kakao.com", 
            "name": "name2", 
            "priority": "1" 
        }, 
        { 
            "email": "user3@kakao.com", 
            "name": "name3", 
            "priority": "2" 
        } 
    ] 
}' 

echo "\n---end create document---"

#outbox documen 출력
echo "\n---start get outbox document---"

curl -i -X GET http://localhost:8080/v1/api/outbox/documents \
-H "Content-Type: application/json" \
-H "X-AUTH-TOKEN: $TOKEN" 

echo "\n---end get outbox document---"

#결재를 위해 결재자의 재로그인
echo "\n---start login---"
TOKEN_HEADER=$(curl -i -X POST http://localhost:8080/v1/login \
-H "Content-Type: application/json" \
-d '{"email": "user2@kakao.com","password": "password"}' | grep X-AUTH-TOKEN)

TOKEN="${TOKEN_HEADER:14}"

echo "token is"
echo $TOKEN

echo "\n---end login---"

#inbox 문서 출력
echo "\n---start get inbox document---"

curl -i -X GET http://localhost:8080/v1/api/inbox/documents \
-H "Content-Type: application/json" \
-H "X-AUTH-TOKEN: $TOKEN" 

echo "\n---end get inbox document---"

#inbox 문서 결재 승낙
echo "\n---start approve document---"

echo "\n inbox documents에서 approverId를 선택하여 입력해주세요: "
read x

curl -i -X PUT http://localhost:8080/v1/api/documents?type=approve \
-H "Content-Type: application/json" \
-H "X-AUTH-TOKEN: $TOKEN" \
-d \
'{ 
    "approverId": '"\"${x}\""', 
    "comment": "this is comment"
}'

echo "\n---end approve document---"


#inbox 문서 결재 거절
echo "\n---start reject document---"

echo "\n inbox documents에서 approverId를 선택하여 입력해주세요: "
read x

curl -i -X PUT http://localhost:8080/v1/api/documents?type=reject \
-H "Content-Type: application/json" \
-H "X-AUTH-TOKEN: $TOKEN" \
-d \
'{ 
    "approverId": '"\"${x}\""', 
    "comment": "this is comment"
}'

echo "\n---end reject document---"

#archive 문서 출력
echo "\n---start get archive document---"

curl -i -X GET http://localhost:8080/v1/api/archive/documents \
-H "Content-Type: application/json" \
-H "X-AUTH-TOKEN: $TOKEN"

echo "\n---end get archive document---"