##How to warn up:

Terminal:

 1. `mvn clean install`
 2. `docker-compose build`  (rebuild)
 3. `docker-compose up`
 4. `docker-compose down`
 
Try ?  
`docker build -t todolist .` ???
 
 Postman:
 
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


