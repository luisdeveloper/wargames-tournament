# Project Structure

## Modules

The project is structured as a single Maven module containing the complete
backend application, including business logic, persistence, and API exposure.

## Packages

**`<base-package>.controller`**  
Contains REST controllers responsible for handling HTTP requests and responses.

**`<base-package>.service`**  
Contains the business logic and application services.

**`<base-package>.repository`**  
Contains Spring Data JPA repositories used to access the database.

**`<base-package>.dto`**  
Contains Data Transfer Objects used to exchange data between layers and with clients.

## Architectural Pattern

The application follows a layered architecture and exposes a RESTful API.

The API complies with Level 2 of the Richardson Maturity Model:
- Resource-oriented endpoints
- Proper use of HTTP methods
- Meaningful HTTP status codes

HATEOAS (Level 3) is not implemented.
