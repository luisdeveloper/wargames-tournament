# **TournamentController**

| Method | Endpoint | Description |
|--------|----------|-------------|
|GET|/tournaments/{id}/summary|Retrieves data from *Tournament* whose id is received as parameter|
|GET|/tournaments/{id}/current-round|Retrieves the next round with no generated matches, from the *Tournament* whose id is received as parameter|
|POST|/tournaments|Adds a new *Tournament* to the database|
|POST|/tournaments/{tournamentId}/current-round/matches|Generates matches for the next *Round* with no generated matches|
<br/>

# **RoundController**

| Method | Endpoint | Description |
|--------|----------|-------------|
|GET|/rounds/{roundId}/matches|Retrieves matches assigned to *Round* whose id is "roundId"|
|PATCH|/rounds/{id}|Updates the properties beginDate and endDate from the *Round* whose id is "id"|
|DELETE|/rounds/{id}|Deletes the *Round* whose id is "id"|
<br/>


# **MatchController**

| Method | Endpoint | Description |
|--------|----------|-------------|
|PUT|/matches/results|Updates the results from one to multiple *matches*|
<br/>


# **PlayerController**

| Method | Endpoint | Description |
|--------|----------|-------------|
|GET|/players/ranking|Retrieves the list of *players*, ordered by their current points, in descending order|
|POST|/players|Adds a new *Player* to the database|
|PATCH|/players/personal-data|Updates the personal data from the *Player* received in the RequestBody|
|PATCH|/players/password|Updates the password from the *Player* received in the RequestBody|
|DELETE|/players/{id}|Deletes the *Player* whose id is "id"|


