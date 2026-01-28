### Branching Strategy
- `master`: stable and deployable branch.
- `develop`: integration branch (optional)
- `feature/<name>`: new features.
- `fix/<name>`: bug fixes.
- `test/<name>`: creation or improvement of tests.
- `docs/<name>`: documentation changes.

### Commits
We follow the [Conventional Commits](https://www.conventionalcommits.org/) convention:
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

