# POST /tournaments

**Description**  
Inserts a new *Tournament* on the database

**Endpoint**
- Method: `GET`
- URI: `/tournaments`
- Content-Type: `application/json`

---
## Request
**Request Body Example**

```json
{
  "name": "Torneo Nacional de Wargame histórico"
  "beginDate": "2025-10-15",
  "endDate": "2025-10-17",
  "location": "Madrid",
  "prize": 1500.0,
  "entryPrice": 25.0,
  "rounds": [
    {
      "roundId": 1,
      "roundNumber": 1,
      "roundDate": "2025-10-15",
      "beginTime": "10:00",
      "endTime": "12:00",
      "matches": []
    },
    {
      "roundId": 2,
      "roundNumber": 2,
      "roundDate": "2025-10-16",
      "beginTime": "10:00",
      "endTime": "12:00",
      "matches": []
    },
    {
      "roundId": 3,
      "roundNumber": 3,
      "roundDate": "2025-10-17",
      "beginTime": "10:00",
      "endTime": "12:00",
      "matches": []
    }
  ]
}

```
## Response

**Expected Status Codes**
- `200 OK` — Tournament successfully created.
- `500 Internal Server Error` — Internal API error.

**Headers**
- `Content-Type: application/json`



**Response Body Example**

```json
{
  "id": 42,
  "name": "Chess Tournament",
  "beginDate": "2025-03-14",
  "endDate": "2025-03-16",
  "prize": 1500.0,
  "entryPrice": 25.0,
  "location": "Madrid"
}
```


