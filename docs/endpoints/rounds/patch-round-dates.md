# PATCH /rounds/{id}

**Description**  
Updates the date and time bounds for the *Round* with the specified ID

**Endpoint**
- Method: `PATCH`
- URI: `/rounds/{id}`
- Content-Type: `application/json`

---
## Request

```json
{
  "roundId": 3,
  "roundDate": "2025-10-16",
  "beginTime": "10:00",
  "endTime": "12:30"
}
```

## Response

**Expected Status Codes**
- `204 No Content` — *Round* dates successfully updated.
- `404 Not Found` — No Round was found with the provided ID.
- `500 Internal Server Error` — Internal API error.

**Headers**
- `Content-Type: application/json`

