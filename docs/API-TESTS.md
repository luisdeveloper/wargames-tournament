# Testing
<br/>

## Types of Tests

**Unit Tests**  
Used to test complex business logic such as match generation, point assignment, and exception handling.

**Integration Tests**  
Used to verify the correct behaviour of API endpoints, including interaction with the database.

**Repository Tests**  
Used to test complex repository methods annotated with `@Query` and `@Modifying`.

---

## How to Run the Tests

Run the following command from the project root:

`mvn test`

## Frameworks and Tools Used

- JUnit 5
- Mockito
- Spring Boot Test
- Spring Data JPA Test (`@DataJpaTest`) for repository-level testing
