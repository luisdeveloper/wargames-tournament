# DELETE /rounds/{id}

**Description**  
Deletes the *Round* with the specified ID

**Endpoint**
- Method: `DELETE`
- URI: `/rounds/{id}`
- Content-Type: `application/json`

---

## Response

**Expected Status Codes**
- `204 No Content` — *Round* successfully deleted.
- `404 Not Found` — No Round was found with the provided ID.
- `500 Internal Server Error` — Internal API error.

**Headers**
- `Content-Type: application/json`