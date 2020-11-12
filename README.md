# Course-Registration-System
Client-Server application for a course registration system utilizing GUI and SQL database.  

## IDE Used
Eclipse IDE was used to create and test the Java files.

## Requirements
MySQL must be installed prior to running this program.

## Installation/Execution
1.) Download all files locally  
2.) Compile the java files  
```bash
javac *.java
```
3.) "Controller.java" is my server side main file and should be executed after compilation with no arguments  
```bash
java Controller
```
4.) "ComController.java" is my client side main file and should be executed with no arguments after "Controller" is executed on the same computer
```bash
java ComController
```
## How to Use
Users can login as a student or administrator. They will be asked for their ID and password which must match an account given in the SQL database. As a student, they will be able to view and search through the course catalogue. Then they can add or remove courses to their schedule.  
As an admin, they will be able to view, search and add courses to the course catalogue. Once courses are added to the catalogue, it will update it in the SQL database and can be viewed by the students.

### Contact
Andrew Eom  
eomandrew@gmail.com
