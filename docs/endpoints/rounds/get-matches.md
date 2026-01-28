# GET /rounds/{roundId}/matches

**Description**  
Retrieves the matches assigned to the *Round* with the specified ID.

**Endpoint**
- Method: `GET`
- URI: `/rounds/{roundId}/matches`
- Content-Type: `application/json`

---

## Response

**Expected Status Codes**
- `200 OK` — *Round* data successfully retrieved.
- `404 Not Found` — No Round was found with the provided ID.
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
  ]
}

