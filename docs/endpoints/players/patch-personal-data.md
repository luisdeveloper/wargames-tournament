# PATCH /players/personal-data

**Description**  
Updates the results from one to multiple *matches*

**Endpoint**
- Method: `PATCH`
- URI: `/players/personal-data`
- Content-Type: `application/json`

---
## Request

**Request Body Example**

```json
{
  "playerId": 12,
  "name": "Laura Martínez",
  "email": "laura.martinez@example.com"
}
```
## Response

**Expected Status Codes**
- `200 OK` — *Player* is successfully updated.
- `404 Not Found` — No *Player* was found with the provided ID.
- `500 Internal Server Error` — Internal API error.

**Headers**
- `Content-Type: application/json`