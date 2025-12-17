# Persistence and Data Storage

## Data Model

**Tournament**
- `id` (PK)
- `name`
- `begin_date`
- `end_date`
- `location`
- `prize`
- `entry_price`

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
        String name
        LocalDate begin_date
        LocalDate end_date
        String location
        double prize
        double entry_price
    }

    ROUND {
        Long id PK
        Long tournament_id FK
        int round_number
        LocalDate round_date
        LocalTime begin_time
        LocalTime end_time
        
    }

    MATCH {
        Long id PK
        Long round_id FK
        int round_number
        LocalDate round_date
        LocalTime begin_time
        LocalTime end_time
        
    }

    TOURNAMENT ||--o{ ROUND : has
    ROUND ||--o{ MATCH : has
```
