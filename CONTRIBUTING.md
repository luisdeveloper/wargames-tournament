### Branching Strategy
- `master`: stable and deployable branch.
- `develop`: integration branch (optional)
- `feature/<nombre>`: new features.
- `fix/<nombre>`: bug fixes.
- `test/<nombre>`: creation or improvement of tests.
- `docs/<nombre>`: documentation changes.

### Commits
Usamos la convención [Conventional Commits](https://www.conventionalcommits.org/):
- `feat:` new functionality.
- `fix:` bug fixes
- `test:` new or improved tests
- `refactor:` internal changes without altering behaviour
- `docs:` documentation updates

### Code Style
- Java 21
- Use of `record` for inmutables DTOs.
- Service methods annotated with `@Transactional` where applicable.

### Tests
- Unit tests: located in `src/test/java`, using JUnit 5 and Mockito.
- Integration tests: located in `src/test/java`.
- Tests must run successfully using `mvn test`.
- Each pull request must include tests for new features.

### Build
1. Clone the repository
2. Run mvn clean install
3. Run the application: java -jar target/tournament.jar

