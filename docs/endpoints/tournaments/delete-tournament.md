# DELETE /tournaments/{id}

**Description**  
Deletes the tournament identified by the given ID.

**Endpoint**
- Method: `DELETE`
- URI: `/tournaments/{id}`
- Content-Type: `application/json`

---

## Response

**Expected Status Codes**
- `204 No Content` — Tournament successfully deleted.
- `404 Not Found` — No tournament was found with the provided ID.
- `500 Internal Server Error` — Internal API error.

**Headers**
- `Content-Type: application/json`

