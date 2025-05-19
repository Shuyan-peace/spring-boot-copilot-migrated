# Web application with springboot

This Web application in Java is migrated using Copilot, from struts. The original struts project is 
https://github.com/josesanchezr/struts2-project

### Pre requirements
Install Jdk 17

http://www.oracle.com/technetwork/java/javase/downloads/index.html

Setup JAVA_HOME environment variable

`set JAVA_HOME=C:\Program Files\Java\jdk17.0.12`

Install maven

https://maven.apache.org/install.html

### Installation
-------------------

To install this web application make the following steps:

#### 1. Clone the project
`create local directory, example mkdir project-example`

`cd project-example`

`git clone https://github.com/Shuyan-peace/spring-boot-copilot-migrated.git`

#### 2. Build the project
`cd spring-boot-copilot-migrated`

`mvn clean package`

### Run the project
-------------------

To run this web application make the following steps:

#### 1. Run embedded tomcat 7 with maven
`mvn spring-boot:run`

Open the URL following

`http://localhost:8080/`
