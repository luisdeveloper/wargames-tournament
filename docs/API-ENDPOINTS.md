# **TournamentController**

| Method | Endpoint | Description |
|--------|----------|-------------|
|GET|[/tournaments/{id}/summary](endpoints/tournaments/get-summary.md)|Retrieves summary information for the *Tournament* whose ID is provided in the path parameter|
|GET|[/tournaments/{id}/current-round](endpoints/tournaments/get-current-round.md)|Retrieves the next round of the *Tournament* (identified by the provided ID) that does not yet have generated matches|
|POST|[/tournaments](endpoints/tournaments/post-tournament.md)|Adds a new *Tournament* to the database|
|POST|[/tournaments/{tournamentId}/current-round/matches](endpoints/tournaments/post-matches.md)|Retrieves summary information for the *Tournament* whose ID is provided in the path parameter|
|DELETE|[/tournaments/{id}](endpoints/tournaments/delete-tournament.md)|Deletes the *Tournament* identified by the given ID|

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


