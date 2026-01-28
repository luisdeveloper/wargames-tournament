# PATCH /players/password

**Description**  
Updates the results from one to multiple *matches*

**Endpoint**
- Method: `PATCH`
- URI: `/players/password`
- Content-Type: `application/json`

---
## Request

**Request Body Example**

```json
{
  "playerId": 12,
  "oldPassword": "MyOldPassword123",
  "newPassword": "MyNewPassword456"
}
```
## Response

**Expected Status Codes**
- `200 OK` — *Player* is successfully updated.
- `401 Invalid Credentials` — the password doesn't belong to the *Player*.
- `404 Not Found` — No player was found with the provided ID.
- `500 Internal Server Error` — Internal API error.

**Headers**
- `Content-Type: application/json`


