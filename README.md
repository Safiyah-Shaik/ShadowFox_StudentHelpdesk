# ShadowFox Student Helpdesk – Support Ticket Console System

## About the Project
The Student Helpdesk Support Ticket Console System is a Java-based application that helps manage student support requests. Students can create tickets, view all tickets, search for specific tickets, update ticket details, track ticket status, and close resolved tickets.

## Features
- Create support tickets with a title and description.
- Categorize tickets as Technical, Academic, Account, or Other.
- Assign Low, Medium, or High priority.
- View all tickets and their details.
- Search tickets using a unique Ticket ID.
- Update ticket information.
- Track ticket status: Open, In Progress, Resolved, and Closed.
- Close tickets only after they are resolved.
- Validate user input and handle errors.
- Automatically generate unique ticket IDs.

## Technologies Used
- Java
- Object-Oriented Programming (OOP)
- Java Collections Framework (`ArrayList`, `List`)
- Java Enums
- Java Date and Time API (`LocalDateTime`)
- Git and GitHub

## Project Structure
```text
ShadowFox_StudentHelpdesk/
├── .gitignore
└── src/
    └── main/
        └── java/
            └── com/
                └── shadowfox/
                    └── helpdesk/
                        ├── Main.java
                        ├── User.java
                        ├── Ticket.java
                        ├── HelpdeskService.java
                        ├── TicketStatus.java
                        ├── TicketCategory.java
                        └── Priority.java
```

## How to Run

### Prerequisites
- Java Development Kit (JDK) installed.
- A terminal or command prompt.

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/Safiyah-Shaik/ShadowFox_StudentHelpdesk.git
   ```

2. Navigate to the project directory:
   ```bash
   cd ShadowFox_StudentHelpdesk
   ```

3. Compile the Java source files:
   ```bash
   javac -d out src/main/java/com/shadowfox/helpdesk/*.java
   ```

4. Run the application:
   ```bash
   java -cp out com.shadowfox.helpdesk.Main
   ```

## Ticket Status Workflow
`OPEN → IN_PROGRESS → RESOLVED → CLOSED`

A ticket must move through the workflow in order. Only resolved tickets can be closed.

## Learning Outcomes
- Practiced object-oriented programming in Java.
- Used classes, objects, enums, and collections.
- Implemented CRUD-style ticket management operations.
- Applied input validation and exception handling.
- Practiced compiling, running, and publishing a Java project using Git and GitHub.

## Author
**Safiyah Shaik**

## Internship
**ShadowFox – Java Development Internship**
