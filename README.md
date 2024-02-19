

# Overview

This is a fullstack application built using Spring Boot for the backend and React for the frontend. The application consists of three main components:

1. **Commission Management**: Handles the creation, updating, and tracking of commissions.
2. **Warehouse Parts Ordering**: Allows users to order parts directly from the warehouse.
3. **Wholesalers Parts Ordering**: Facilitates ordering parts from wholesalers.


## Table of Contents

- [Design Patterns Used](#design-patterns-used)
- [How to run](#how-to-run)
- [Security](#security)
- [Technologies Used](#technologies-used)
- [UML Diagram](#uml-diagram)


## How to run 


1. install mvn

2. Type in parent directory:

```bash
mvn -f CarService/pom.xml package -DskipTests && docker compose up
```


## Design Patterns Used:

1. **Builder Pattern** - Utilized in the commission handling module for creating complex commission objects.
2. **Singleton Pattern** - Implemented in the warehouse module to ensure only one instance of the warehouse is available throughout the application.
3. **Mediator Pattern** - Used in the communication between different modules, particularly in the warehouse component for managing communication between different parts of the system.
4. **Bridge Pattern** - Applied in the invoice module to decouple the abstraction from its implementation.
5. **Adapter Pattern** - Implemented in the wholesalers module to adapt the interface of the wholesalers' systems to the application's interface.

## Security:

Endpoints within the system are secured against unauthorized access through an authentication mechanism, ensuring that only authenticated users can access the functionalities.

## Technologies Used:

- **Spring Boot 3**: Backend service designed in monolithic architecture. Written in Java 21. 
- **React**: For creating dynamic and interactive user interfaces.
- **RESTful APIs**: Used for communication between frontend and backend components.
- **JSON Web Tokens (JWT)**: Employed for secure authentication and authorization of API endpoints.


## UML Diagram

![diagram-14112519493894189060](https://github.com/WojciechGos/Car-Service/assets/36795978/971fb941-8df7-4b48-a23a-c3463ddbbd23)
