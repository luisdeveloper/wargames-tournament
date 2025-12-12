# GET /players/ranking

**Description**  
Retrieves the list of *players*, ordered by their current points, in descending order

**Endpoint**
- Method: `GET`
- URI: `/players/ranking`
- Content-Type: `application/json`

---

## Response

**Expected Status Codes**
- `200 OK` — Ranking successfully retrieved
- `500 Internal Server Error` — Internal API error.

**Headers**
- `Content-Type: application/json`

**Response Body Example**

```json
[
  {
    "name": "Ana López",
    "email": "ana.lopez@example.com",
    "points": 18
  },
  {
    "name": "Miguel Torres",
    "email": "miguel.torres@example.com",
    "points": 15
  },
  {
    "name": "Lucía Martínez",
    "email": "lucia.martinez@example.com",
    "points": 14
  },
  {
    "name": "Carlos Rivera",
    "email": "carlos.rivera@example.com",
    "points": 12
  }
]
```
