CMPE 281: Cloud Technologies-Project 1 FileCab
==================================================

### Introduction
* Project 1: FileCab
* University Name: http://www.sjsu.edu/
* Course: [Cloud Technologies](http://info.sjsu.edu/web-dbgen/catalog/courses/CMPE281.html)
* Professor: [Sanjay Garje](https://www.linkedin.com/in/sanjaygarje/)
* ISA: [Anushri Srinath Aithal](https://www.linkedin.com/in/anushri-aithal/)

### Project Introduction
This Web Application is allowed users to upload, delete, access their files from anywhere with any device.
Users shall be able to create account, sign in, upload and modify the files.

### Features
* Users shall be able to register a account.
![signup](https://raw.githubusercontent.com/Junten/File_Cab/master/images/Screen%20Shot%202018-10-29%20at%204.41.59%20PM.png)

* Users shall be able to login to the application 
![login](https://raw.githubusercontent.com/Junten/File_Cab/master/images/Screen%20Shot%202018-10-29%20at%2010.37.33%20AM.png)

* Users shall be able to Upload up to 10MB size files.
![upload](https://raw.githubusercontent.com/Junten/File_Cab/master/images/Screen%20Shot%202018-10-29%20at%204.41.46%20PM.png)

* User shall be able to Download the files.
![download](https://raw.githubusercontent.com/Junten/File_Cab/master/images/Screen%20Shot%202018-10-29%20at%204.40.57%20PM.png)

* User shall be able to Delete the files.

* Diagram
![diagram](https://github.com/Junten/File_Cab/blob/master/images/Untitled%20Diagram.jpg?raw=true)

### Local Machine Development
IDE: Eclipse
Java Framework: Spring Boot
Building Software: Apache Maven

### Deploy Local
- Download the Repo
- Edit the /src/main/resources/application.properties file, add the database info and asw info
- on cli, run ```$mvn spring-boot:run```
