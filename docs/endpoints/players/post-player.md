# POST "/players"

**Description**  
Inserts a new *Tournament* on the database

**Endpoint**
- Method: `PST`
- URI: `"/players"`
- Content-Type: `application/json`

---
## Request
**Request Body Example**

```json
{
  "tournamentId": 42,
  "name": "Javier Ruiz",
  "email": "javier.ruiz@example.com",
  "password": "SecurePass123"
}
```
## Response

**Expected Status Codes**
- `201 Created` — Player successfully created.
- `404 Not Found` — No Tournament found with the provided ID.
- `500 Internal Server Error` — Internal API error.

**Headers**
- `Content-Type: application/json`




