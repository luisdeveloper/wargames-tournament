# POST /tournaments/{tournamentId}/current-round/matches

**Description**  
Retrieves summary information for the *Tournament* whose ID is provided in the path parameter.

**Endpoint**
- Method: `POST`
- URI: `/tournaments/{tournamentId}/current-round/matches`
- Content-Type: `application/json`

---

## Response

**Expected Status Codes**
- `200 OK` — Round data successfully retrieved.
- `404 Not Found` — No tournament was found with the provided ID.
- `500 Internal Server Error` — Internal API error.

**Headers**
- `Content-Type: application/json`

**Response Body Example**

```json
{
  "roundId": 3,
  "roundNumber": 3,
  "roundDate": "2025-04-12",
  "beginTime": "10:00",
  "endTime": "12:30",
  "matches": [
    {
      "matchId": 101,
      "player1": {
        "name": "Ana",
        "email": "ana@example.com",
        "points": 15
      },
      "player2": {
        "name": "Carlos",
        "email": "carlos@example.com",
        "points": 12
      },
      "result": "PLAYER_1_VICTORY"
    },
    {
      "matchId": 102,
      "player1": {
        "name": "Lucía",
        "email": "lucia@example.com",
        "points": 10
      },
      "player2": {
        "name": "Mario",
        "email": "mario@example.com",
        "points": 11
      },
      "result": "TIE"
    },
    {
      "matchId": 103,
      "player1": {
        "name": "Pedro",
        "email": "pedro@example.com",
        "points": 8
      },
      "player2": {
        "name": null,
        "email": null,
        "points": 0
      },
      "result": "BYE"
    }
  ]
}

