# Installation and Running the Application

## Prerequisites

Make sure all required versions and environment variables are configured as
described in the [Requirements](API-REQUIREMENTS.md) document.

---

## Local Development

By default, the application runs using an in-memory H2 database.
No external database setup is required.

To run the application locally:

```bash
mvn spring-boot:run
```

## Production-like Setup (PostgreSQL)

When running with the `prod` profile, the application expects a PostgreSQL
database to be available and configured via environment variables.

See → [API Requirements](API-REQUIREMENTS.md) for the full list of required environment variables.

Example:
```bash
export SPRING_PROFILES_ACTIVE=prod
export DB_URL=jdbc:postgresql://localhost:5432/tournament
export DB_USER=postgres
export DB_PASSWORD=postgres

java -jar wargames-tournament.jar
```
The database must be reachable before starting the application.

