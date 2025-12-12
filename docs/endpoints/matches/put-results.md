# PUT /matches/results

**Description**  
Updates the results from one to multiple *matches*

**Endpoint**
- Method: `PUT`
- URI: `/matches/results`
- Content-Type: `application/json`

---
## Request

**Request Body Example**

```json
[
  {
    "matchId": 101,
    "player1Id": 1,
    "player2Id": 2,
    "result": "PLAYER_1_VICTORY"
  },
  {
    "matchId": 102,
    "player1Id": 3,
    "player2Id": 4,
    "result": "TIE"
  },
  {
    "matchId": 103,
    "player1Id": 5,
    "player2Id": null,
    "result": "BYE"
  }
]
```
## Response

**Expected Status Codes**
- `200 OK` — *Matches* are successfully updated.
- `500 Internal Server Error` — Internal API error.

**Headers**
- `Content-Type: application/json`

**Response Body Example**

```json
[
  {
    "name": "Ana López",
    "email": "ana.lopez@example.com",
    "points": 15
  },
  {
    "name": "Carlos García",
    "email": "carlos.garcia@example.com",
    "points": 12
  },
  {
    "name": "Lucía Torres",
    "email": "lucia.torres@example.com",
    "points": 10
  },
  {
    "name": "Mario Sánchez",
    "email": "mario.sanchez@example.com",
    "points": 11
  }
]
