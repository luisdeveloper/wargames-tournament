# GET /tournaments/{id}/summary

**Description**  
Retrieves summary information for the *Tournament* whose ID is provided in the path parameter.

**Endpoint**
- Method: `GET`
- URI: `/tournaments/{id}/summary`
- Content-Type: `application/json`

---

## Response

**Expected Status Codes**
- `200 OK` — Tournament data successfully retrieved.
- `404 Not Found` — No tournament was found with the provided ID.
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
