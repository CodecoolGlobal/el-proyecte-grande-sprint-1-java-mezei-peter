# Bitter

## Description
Bitter is a social media website where users can create their account, follow each other, post "bits" and comment on each other's content.\
It is a fullstack teamwork project using Java Spring for the backend and React.js for the frontend.

## Used technologies
- Java
  - Spring Boot
  - Spring Data
  - Spring Security
  - JUnit Jupiter
  - Lombok
- Javascript
  - Node.js
  - React.js
- Tailwind
- PostgreSQL
- JWT

## Dependencies
- JDK
- Maven
- Node.js and npm
- Relational Database: The backend can be run using **environment variables** to connect to the database:
  ```
  DRIVER=[Relational database driver]
  URL=[The database's URL]
  USERNAME=[Username to connect to the database]
  PASSWORD=[User's password to connect to the database]
  ```

## Features
- User can register.
- User can log in. In this case, a JWT token is stored in the Local Storage and sent with each request where Spring Security validates it.
- User can log out.
- User can search for other users using a search bar.
- User can follow other users.
- User can post short text content called 'bits.'
- Each user has a public details page containing bits that they posted + information about their followers and followed people.
- Each user has a private feed page containing bits by their followed users and themselves.

## Usage
