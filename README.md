# Online Food Ordering System (Core Java)-ofos





# Project Overview

This is a console-based Java backend application that simulates an online food ordering system. The project is designed using Object-Oriented Programming principles and follows a layered architecture to separate concerns between data models, business logic, and persistence.



# Tech Stack

- Java (Core Java)

- OOP Concepts

- File Handling

- Java Serialization

- Git & GitHub


# Project Architecture

- models – Represents core entities like MenuItem, Order, OrderItem, User

- services – Contains business logic for menu and order management

- utils – Utility classes like ID generator


- DataStore – Handles data persistence using serialization




Features

- Add, view, and remove menu items

- Create customer orders

- Add items to orders

- Calculate total order amount

- Persist data using file-based storage

- Load previous data on application restart


Data Persistence

The application uses Java Serialization to store application state in a binary file:


Data is automatically saved and restored when the application restarts.









## How to Run the Project



### Compile

bash

javac -d out src/com/example/ofos/**/*.java src/com/example/ofos/*.java


Run:

java -cp out com.example.ofos.App


Learning Outcomes:


Strong understanding of OOP and separation of concerns



Practical experience with Java file handling and serialization



Hands-on project structuring and modular design



Prepared foundation for converting to Spring Boot REST API



Future Enhancements:


Convert backend to Spring Boot REST APIs



Add Angular frontend



Dockerize the application



Deploy using Kubernetes


