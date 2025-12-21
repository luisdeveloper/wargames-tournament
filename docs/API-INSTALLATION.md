# Installation and Running the Application

## Installation and Running the Application

## Prerequisites
- Java 21
- Maven 3.9+
- Git

## Steps

1. Clone the repository:
```bash
git clone https://github.com/luisdeveloper/wargames-tournament.git
cd wargames-tournament
```
2. Build the project:
`mvn clean package`

3. Run the application:
`mvn spring-boot:run`

Notes
- The production profile uses PostgreSQL.
- Database connection details are provided via environment variables.


## Local Development

By default, the application runs using an in-memory H2 database.
No external database setup is required.

To run the application locally:

`mvn spring-boot:run`

## Production-like Setup (PostgreSQL)

When running with the prod profile, the application expects a PostgreSQL
database to be available and configured via environment variables.

Required Environment Variables
- SPRING_PROFILES_ACTIVE=prod
- DB_URL — JDBC URL of the PostgreSQL database
- DB_USER — Database username
- DB_PASSWORD — Database password

Example:
```bash
export SPRING_PROFILES_ACTIVE=prod
export DB_URL=jdbc:postgresql://localhost:5432/tournament
export DB_USER=postgres
export DB_PASSWORD=postgres

java -jar wargames-tournament.jar
```

The database must be reachable before starting the application.
