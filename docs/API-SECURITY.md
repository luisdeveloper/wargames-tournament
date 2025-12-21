# Security

At the current stage, the application implements a minimal security model.

The API is designed to be operated by a single administrator user, responsible for:
- Creating and managing tournaments
- Registering players
- Generating rounds and match pairings
- Updating match results

No role-based access control or token based authentication is currently implemented.

## Password Management

When updating a password, the API requires the current password to be provided.
This ensures that only the legitimate users can perform password changes.

## Limitations

- No authentication mechanism (such as JWT or OAuth is currently in place).
- All endpoints are accessible without role-based restrictions.
- The API is not intended for public exposure in its current state.

## Future improvements

Future versions may introduce:
- Token-based authentication.
- Role-based access control.
- Separation between administrative and player level operations.
