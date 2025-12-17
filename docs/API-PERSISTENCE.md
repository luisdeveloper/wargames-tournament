# Persistence and Data Storage

## Data Model
**Player**
- `id` (PK)
- `fullName`
- `email`
- `password`
- `points`

**Tournament**
- `id` (PK)
- `name`
- `begin_date`
- `end_date`
- `location`
- `prize`
- `entry_price`

**TournamentPlayer**
- `tournament_id` (FK → Tournament)
- `player_id` (FK → Player)

**Round**
- `id` (PK)
- `tournament_id` (FK → Tournament)
- `round_number`
- `round_date`
- `begin_time`
- `end_time`


**Match**
- `id` (PK)
- `round_id` (FK → Round)
- `player1_id` (FK → Player)
- `player2_id` (FK → Player)
- `result`

## ER Diagram

```mermaid
erDiagram
    TOURNAMENT {
        Long id PK
    }

    PLAYER {
        Long id PK
    }

   TOURNAMENT_PLAYER {
        Long tournament_id FK
        Long player_id FK
    }

    ROUND {
        Long id PK
        Long tournament_id FK
    }

    MATCH {
        Long id PK
        Long round_id FK
        Long player1_id FK
        Long player2_id FK
    }

    TOURNAMENT ||--o{ ROUND : has
    ROUND ||--o{ MATCH : has

    TOURNAMENT ||--o{ TOURNAMENT_PLAYER : registers
    PLAYER ||--o{ TOURNAMENT_PLAYER : participates
```
## Persistence Technology

- Spring Data JPA (Hibernate as JPA provider)
- H2 Database (development environment)
- PostgreSQL (production environment)

  
## Database Migrations

At this stage, database schema evolution is managed by Hibernate using the
`ddl-auto` configuration.

No dedicated migration tool (such as Flyway or Liquibase) is currently in use.
This may change in future versions as the data model evolves.
