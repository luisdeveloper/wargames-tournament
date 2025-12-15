# Requirements
## Required versions
- **Java:** 21
- **Spring Boot:** 3.3.4
- **Spring Web:** 3.3.4
- **Spring Data:** 3.3.4
- **H2** (dev):2.2.224
- **PostgreSQL** (prod): 42.7.7

## Dependencies
- org.springframework.boot:spring-boot-starter-data-jpa:jar:3.3.4:compile
- org.springframework.boot:spring-boot-starter-web:jar:3.3.4:compile
- jakarta.validation:jakarta.validation-api:jar:3.0.2:compile
- com.h2database:h2:jar:2.2.224:runtime
- org.springframework.boot:spring-boot-starter-test:jar:3.3.4:test

## Environment Variables

| Variable | Description | Required | Notes |
|---------|-------------|----------|-------|
| DB_URL | JDBC URL for the PostgreSQL database | Yes | Must point to the production database |
| DB_USER | Database username | Yes | |
| DB_PASSWORD | Database password | Yes | Must be provided securely |



