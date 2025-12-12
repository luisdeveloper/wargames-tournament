# DELETE /players/{id}

**Description**  
Deletes the *Player* identified by the given ID.

**Endpoint**
- Method: `DELETE`
- URI: `/players/{id}`
- Content-Type: `application/json`

---

## Response

**Expected Status Codes**
- `204 No Content` — Player successfully deleted.
- `404 Not Found` — No player was found with the provided ID.
- `500 Internal Server Error` — Internal API error.

**Headers**
- `Content-Type: application/json`