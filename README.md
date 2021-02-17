##How to warn up:

Terminal:

 1. `mvn clean install`
 2. `docker-compose build`  (rebuild)
 3. `docker-compose up`
 4. `docker-compose down`
 
Try ?  
`docker build -t todolist .` ???
 
 -> -> ->-> -> ->-> -> -> 这只是演习 -> ->-> -> ->-> -> ->-> -> ->
 ##Functions:
 |   😄  | POST  | DELETE | PUT | GET |
 |  ----  | ----  | --- | --- | --- |
 |   Memo  | 1. memo(userID) | 1. memo(memoID) <br>  2. memo(userId) | 1. memo(memoID)  | 1. All memos <br> 2. All memos (public)[暂时未用] <br> 3. All memos (userID) <br> 4. memo (memoID) <br> 5. memo (tags) <br> 6. memo (keyword) <br> 7. memo (complete) <br>    |
 |   User  | 1. users  | 1. users(userID) | 1. users(userID) | 1. All users <br> 2. users (userID)| 

  ###Postman:
- POST:
 
  `localhost:8080/memos`
  
  `{
      "message":"xixi",
      "tag":"This is a taghh"
   }`
  
- GET:
  
  `localhost:8080/memos`
  
  `localhost:8080/memos/1`
  
- DELETE:
  
  `localhost:8080/memos/2`
  
- PUT
  
  `localhost:8080/memos/4`
  
  `{
       "message":"去南京",
       "tag":"This is a tag"
   }`

-> -> ->-> -> ->-> -> -> 这只是演习 -> ->-> -> ->-> -> ->-> -> ->

memo:

|  Field   | Type  |
|  ----  | ----  |
| MemoId  | Long |
| tag | String |
/complete| Boolean|
|publicity|Boolean|
|publish-time|LocalDate|
|Modified-time | LocalDate |
|UserID|Long|

User:

|  Field   | Type  |
|  ----  | ----  |
| UserID | Long |
| DoB | Date |
| email | String | 
| phone | String | 
| Admin | Boolean |

~~~
admin 原则:
1. 个人可对自己memo，users info 增删改查. 
2. 个人无法查看他人的私人memo, 可查看他人的public memo和个人信息.
3. admin可以get all info，delete all info, 但无法更改他人信息.


问题：memo post：
1- 检测userID，若userId不存在，则返回error
2- password 更新。
3- 睡否需要token,若需要，是否需要反解锁
4- memo增删改查需要检测 userId or token
5- 整体的规划 - 权限 - 
