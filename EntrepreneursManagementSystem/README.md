# Entrepreneurs Management System

College-level Java web project using **JSP, Jakarta Servlets, JDBC, MySQL 8, and Apache Tomcat 10.1**. It uses an MVC layout: JSP files render views, servlets validate requests and choose views, and DAO classes run parameterized SQL. No Spring framework is used.

## Project structure

    EntrepreneursManagementSystem/
    ├── src/main/java/college/ems/
    │   ├── dao/       # JDBC queries (DAO pattern)
    │   ├── model/     # Entrepreneur, Business, Employee, Investment, User
    │   ├── servlet/   # Login, dashboard, CRUD controllers, authentication filter
    │   └── util/      # DBUtil and password hash helper
    ├── src/main/resources/
    │   ├── schema.sql
    │   └── db.properties.example
    ├── src/main/webapp/
    │   ├── assets/styles.css
    │   └── WEB-INF/views/  # JSP views; kept out of direct public URL access
    ├── pom.xml
    └── README.md

## Requirements

- JDK 25 (the Tomcat 10.1 runtime must also use Java 25)
- Apache Tomcat 10.1 (Jakarta Servlet 6)
- MySQL 8
- Eclipse IDE for Enterprise Java and Web Developers (includes Web Tools Platform)
- Maven support (m2e is included in the Enterprise package)

This project targets Tomcat 10.1 / jakarta.servlet. Do not deploy it to Tomcat 9; Tomcat 9 requires javax.servlet and is a different project target.

## 1. Create the database

Open MySQL Workbench or a MySQL command prompt and run src/main/resources/schema.sql. It creates entrepreneurs_management_system and the five requested tables with their foreign keys. To populate a first demo, optionally run src/main/resources/sample-data.sql after the schema. Deleting an entrepreneur cascades to their businesses, employees and investments.

Create a database account for this app (replace the sample password):

    CREATE USER 'ems_user'@'localhost' IDENTIFIED BY 'choose-a-local-password';
    GRANT SELECT, INSERT, UPDATE, DELETE ON entrepreneurs_management_system.* TO 'ems_user'@'localhost';

## 2. Configure the database connection

Copy src/main/resources/db.properties.example to src/main/resources/db.properties and edit the URL, username and password. db.properties is git-ignored. Alternatively set EMS_DB_URL, EMS_DB_USERNAME and EMS_DB_PASSWORD as environment variables; environment values take precedence.

The Java code has no real database password hardcoded. DBUtil.getConnection() is the single connection configuration point.

## 3. Create an administrator login

Passwords are stored as BCrypt hashes. Generate a hash using the included helper from PowerShell in the project directory:

    mvn dependency:build-classpath "-Dmdep.outputFile=target/classpath.txt"
    $cp = "target\classes;" + (Get-Content target\classpath.txt -Raw)
    java -cp $cp college.ems.util.PasswordHashTool "your-new-password"

Copy the printed hash and insert the account in MySQL (replace the username and hash):

    INSERT INTO users (username, password_hash, role)
    VALUES ('admin', 'PASTE_THE_BCRYPT_HASH_HERE', 'ADMIN');

Sign in with the username and original password used to generate the hash. The login uses a server-side session, 30-minute inactivity timeout, and BCrypt verification; no demo default password is installed.

## 4. Open in Eclipse

1. Extract/copy this EntrepreneursManagementSystem folder to a normal development location.
2. In Eclipse, choose **File → Import → Maven → Existing Maven Projects**, select this folder, and finish. Eclipse imports it as a Maven web application; included .project, .classpath and facet metadata support Dynamic Web Project tooling.
3. Right-click the project → **Properties → Project Facets**. Ensure Java 25 and Dynamic Web Module 6.0 are selected. In **Targeted Runtimes**, select Apache Tomcat v10.1, configured to run with Java 25.
4. If Tomcat is not listed: **Window → Show View → Servers**, choose **New → Server → Apache → Tomcat v10.1**, and browse to your Tomcat installation.
5. Right-click the project → **Maven → Update Project** so Maven resolves MySQL Connector/J, BCrypt and Jakarta JSTL. Connector/J is included by Maven; do not separately add a second driver copy.
6. Start MySQL and confirm your db.properties or environment settings.
7. Right-click the project → **Run As → Run on Server**, select Tomcat 10.1, and finish. Open http://localhost:8080/EntrepreneursManagementSystem/.

Build a deployable WAR outside Eclipse with:

    mvn clean package

The WAR is target/EntrepreneursManagementSystem.war.

## 5. Servlet URLs

| URL | Method | Purpose |
|---|---|---|
| /login | GET, POST | Login page and BCrypt/session authentication |
| /logout | POST | Invalidate the signed-in session |
| /dashboard | GET | Live summary counts |
| /entrepreneurs | GET | List, ?q= search and ?status= filter |
| /entrepreneurs/new | GET | New entrepreneur form |
| /entrepreneurs/edit?id=1 | GET | Edit form |
| /entrepreneurs/save | POST | Insert or update entrepreneur |
| /entrepreneurs/delete | POST | Delete entrepreneur |
| /businesses, /employees, /investments | GET | List child records |
| /<section>/new and /<section>/edit?id=1 | GET | Child record form |
| /<section>/save and /<section>/delete | POST | Child record insert/update/delete |
| /reports, /settings | GET | Database-backed summary pages |

Valid section values are businesses, employees, and investments. All data-changing forms use POST. User-provided SQL values use PreparedStatement; SQL table names are selected from a fixed server-side allow-list.

## Notes for the project viva

- **Servlet** receives the HTTP request and coordinates the response.
- **JSP** renders the HTML; views are in WEB-INF so they cannot be opened directly without going through a servlet.
- **DAO** contains the SQL and maps result rows to Java model objects.
- **JDBC** connects the DAO to MySQL through reusable DBUtil.
- **BCrypt** hashes/verifies login passwords. There is no hardcoded initial administrator.
- This is a teaching/demo system. Before public deployment, add CSRF tokens, stronger role/authorization rules and production-grade secret management.

## Troubleshooting

- **Database configuration error:** check that the database was created, db.properties is copied/filled in, and MySQL is running.
- **Access denied:** ensure the database user has privileges on entrepreneurs_management_system.
- **Missing driver:** in Eclipse, run Maven → Update Project; for a manual non-Maven setup, add MySQL Connector/J to the web app runtime classpath.
- **404 or Jakarta class errors:** verify that the server is Tomcat 10.1 and deployed context path is EntrepreneursManagementSystem.
- **Duplicate email:** entrepreneur emails are unique by schema; update the existing record or use another email.
