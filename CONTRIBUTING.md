### Flujo de ramas
- `master`: rama estable y desplegable.
- `develop`: rama de integración (opcional).
- `feature/<nombre>`: nuevas funcionalidades.
- `fix/<nombre>`: correcciones de errores.
- `test/<nombre>`: creación o mejora de tests.
- `docs/<nombre>`: cambios de documentación.

### Commits
Usamos la convención [Conventional Commits](https://www.conventionalcommits.org/):
- `feat:` nueva funcionalidad
- `fix:` corrección de bug
- `test:` tests nuevos o mejorados
- `refactor:` cambios internos sin alterar el comportamiento
- `docs:` documentación

### Estilo de código
- Java 21
- Uso de `record` para DTOs inmutables
- Métodos de servicio con anotaciones `@Transactional` donde aplica

### Tests
- Unit tests: en `src/test/java`, usando JUnit 5 y Mockito.
- Integration tests: en `src/test/java`.
- Los tests deben ejecutarse con `mvn test` sin errores.
- Cada PR debe incluir tests para nuevas funcionalidades.

### Build
1. Clonar el repo
2. `mvn clean install`
3. Ejecutar la app: `java -jar target/tournament.jar`

