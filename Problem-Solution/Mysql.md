##❌Mysql-Communications link failure

###主要问题⚠️

错误🙅：docker-compose up 失败 <br>
直接原因：mysql启动失败 <br>
报错：HikariPool-1 - Exception during pool initialization

解决的心路历程👇

|原因|解决方法|参考文献|是否解决|
|---|---|---|---|
|时区问题(猜测）|application.yml文件中的datasource配置节中的url配置加上`&serverTimezone=UTC`，<br> 即: `spring.datasource.url=jdbc:mysql://database/mysql?serverTimezone=UTC&useSSL=true` |[SpringBoot配置JDBC连接MySql数据库的时候遇到了报错：HikariPool-1 - Exception during pool initialization](https://www.cnblogs.com/stilldream/p/11284187.html) |否|
|mysql 版本问题(猜测)|更换mysql版本。image: mysql:8.0.22 or mysql:5.7 |❌| 未解决 |
|Error creating root users(猜测)|并没真正问题| [Error creating root users](https://github.com/docker-library/mysql/issues/307)|未解决|
|docker-compose 设置(猜测)|在docker-compose.yaml文件中添加 `- MYSQL_HOST=localhost`|https://github.com/docker-library/mysql/issues/216|疑似解决==> 并没有，在其他文件中尝试，发现不更改，也可以实现|
|mysql启动耗时🙆(正确)|为mysql添加health check（直到mysql启动好，再启动app)|  - [Docker 容器的健康检查](https://beginor.github.io/2018/03/11/healthy-check-instruction-of-docker.html) <br> - [Docker-compose check if mysql connection is ready](https://stackoverflow.com/questions/42567475/docker-compose-check-if-mysql-connection-is-ready)|解决👏👏👏｜

###关联问题⚠️
health check 很好的解决了mysql connection ready 的问题，但带来了新的问题。<br>
即：第二次启动 `docker-compose up` 时，会产生错误：`ERROR: for app  Container "e3ddd0e143c2" is unhealthy`.<br>

解决方法👇

|原因|解决方法|参考文献|是否解决|
|---|---|---|---|
|第一次的container没有删除 <br> (❓为什么不能restart or create new one)|`docker-compose down`|[I am having this "Encountered errors while bringing up the project." error #5286](https://github.com/docker/compose/issues/5286)|已解决|

###附件📎:
|错误启动log|正确启动log|
|---|---|
|![Github Link](https://github.com/LunaTW/TodoList/blob/Feat-7_Add_user_feature/images/1-mysqlError(1).png) <br> ![Github Link](https://github.com/LunaTW/TodoList/blob/Feat-7_Add_user_feature/images/1-mysqlError(2).png) <br> ![Github Link](https://github.com/LunaTW/TodoList/blob/Feat-7_Add_user_feature/images/1-mysqlError(3).png)  |![Github Link](https://github.com/LunaTW/TodoList/blob/Feat-7_Add_user_feature/images/2-mysqlCorrect(1).png) <br> ![Github Link](https://github.com/LunaTW/TodoList/blob/Feat-7_Add_user_feature/images/2-mysqlCorrect(2).png)|
