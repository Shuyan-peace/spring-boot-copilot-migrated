# Web application with springboot

This Web application in Java is migrated using Copilot, from struts. The original struts project is 
https://github.com/josesanchezr/struts2-project

### Pre requirements
#### 1. Install Jdk 17

http://www.oracle.com/technetwork/java/javase/downloads/index.html

Setup JAVA_HOME environment variable

`set JAVA_HOME=C:\Program Files\Java\jdk17.0.12`

#### 2. Install maven

https://maven.apache.org/install.html

### Installation
-------------------

To install this web application make the following steps:

#### 1. Clone the project
Create local directory, example: 

`mkdir project-example`

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



### ✅ Summary of Copilot Migratin (Sprint Boot): 
- Copilot is highly effective at scaffolding migration patterns but lacks full architectural context.
- Manual oversight is critical to ensure production-grade outcomes, particularly for configuration, file handling, and UI logic.
- Agentic tooling for SDLC migration will require enhanced planning, multi-step awareness, and stronger prompt engineering.

---
  
### 1. Log4j Conflicts
- **Issue:**  
  Copilot introduced new Log4j dependencies multiple times, resulting in configuration conflicts and runtime errors.

- **Root Cause:**  
  It did not recognize that Struts already uses Log4j internally.

- **Resolution:**  
  Removed all Copilot-injected Log4j configurations and retained the original Struts logging setup.

- **Recommendation:**  
  Prompt Copilot to detect and respect pre-existing logging frameworks to avoid duplication and conflicts.

---

### 2. HTML Page Not Rendered
- **Issue:**  
  After migration, HTML pages failed to render correctly.

- **Root Cause:**  
  Copilot missed including the Thymeleaf template engine dependency in the `pom.xml` file.

- **Resolution:**  
  Manually added Thymeleaf dependency and updated controller/view routing logic.

- **Recommendation:**  
  Include view engine requirements like Thymeleaf in initial migration prompts to Copilot when handling frontend migrations.

---

### 3. Missing Country List in Form
- **Issue:**  
  The migrated form did not display the country list dropdown.

- **Root Cause:**  
  Copilot failed to include logic in `request.html` to bind country data.

- **Incorrect Fix Suggested:**  
  Copilot attempted to inject country data directly in `WebController.java`, which didn’t resolve the issue.

- **Resolution:**  
  Manually added logic to populate the dropdown in the view.

- **Recommendation:**  
  Clearly define form requirements and data sources when prompting Copilot to migrate dynamic frontend logic.

---

### 4. JSON File Handling – Misplaced in JAR
- **Issue:**  
  Copilot placed JSON data files inside the compiled JAR, resulting in read-only access at runtime.

- **Why It’s Incorrect:**  
  Data files that need runtime access should reside outside the JAR for flexibility and write access.

- **Resolution:**  
  Refactored file loading logic to access resources from an external directory outside the application JAR.

- **Recommendation:**  
  Prompts to Copilot should specify that resource files must be placed externally for proper configuration management.





