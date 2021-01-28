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
 |   User  | 1. user  | 1. user(userID) | 1. user(userID) | 1. All users <br> 2. user (userID)| 

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


