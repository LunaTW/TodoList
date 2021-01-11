##How to warn up:

Terminal:

 1. `mvn clean install`
 2. `docker-compose build`  (rebuild)
 3. `docker-compose up`
 
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
| id  | Long |
| message  | String |
| complete|Boolean|
| tag | List |
| data | LocalDate | 

12.21 -- 12.27:
1. POST, DELETE, GET, POST 
2. mysql
3. exception

12.28 -- 1.3:
1. 
