# demo-spring

Steps to run the application:

1) Install MySQL Workbench/Server locally.
2) For root MySQL password I have "12345". Change it with your root password in application.properties file which is in scr/main/resources folder.
3) Connect locally with localhost:3306.
4) Create Schema with name usersdb and a Table inside it with Name userregistration.
5) Table Column Names and Datatypes should be: 
	id - INT (PK,NN,AI checked)
	username - VARCHAR(45) (all unchecked - NULL in Default/Expression)
	password - VARCHAR(45) (all unchecked - NULL in Default/Expression)
	email - VARCHAR(100) (all unchecked - NULL in Default/Expression)
6) For the Java project you need to install jre1.8.0_261.
7) Download or Git Clone demo-spring locally.
8) Go to properties then Java Build Path, select Order and Export tab and add your jre1.8.0_261.
9) Run it as Spring Boot App.

This Java Spring demo has been created for test purposes and works with the TheodoreTsg/react-project.
More info on how to run the Front End part are inside the README.md file in that project.