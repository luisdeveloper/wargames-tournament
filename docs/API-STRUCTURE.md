# Project Structure

## Modules

The project is structured as a single Maven module containing the complete
backend application, including business logic, persistence, and API exposure.

## Packages

Base package: `com.github.luisdeveloper.tournament`

**`.controller`**  
Contains REST controllers responsible for handling HTTP requests and responses.

**`.service`**  
Contains business logic and application services.

**`.repository`**  
Contains Spring Data JPA repositories used to access the database.

**`.entity`**  
Contains entity classes used to map data between the API and the database.

**`.dto`**  
Contains Data Transfer Objects used to exchange data between layers and with clients.

**`.enums`**  
Contains enums used to define common values within the API, such as match results.

**`.exceptions`**  
Contains custom exceptions defined by the API.

**`.mapper`**  
Contains utility classes used to map data between entity classes and DTOs.






## Architectural Pattern

The application follows a layered architecture and exposes a RESTful API.

The API complies with Level 2 of the Richardson Maturity Model:
- Resource-oriented endpoints
- Proper use of HTTP methods
- Meaningful HTTP status codes

HATEOAS (Level 3) is not implemented.
