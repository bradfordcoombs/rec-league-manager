# Rec League Team Management

Small web application for managing players and teams in a recreational league.

## Requirements

For building and running the application you need:

- [JDK 21](https://jdk.java.net/archive/)

## Running the application locally

The application can be deployed to a local application server, such as Tomcat

Alternatively you can use the [Cargo Maven plugin](https://codehaus-cargo.github.io/cargo/Maven+3+Plugin.html) like so:

```shell
./mvnw cargo:run
```

This will run the app on the local 8080 port at the /assignment root context.

## Data

The application relies on an in-memory H2 database configured with default settings and a set of default teams and players.
This database will be reset every time the application is started.

## Security
The app is configured with 2 users, one with each of the following roles: a DIRECTOR and a PLAYER.

- director@test.com
- player@test.com

Passwords can be configured with environment variables

```
bc.security.director.password=
bc.security.player.password=
```

These have default values if not provided:

```
bc.security.director.password=director123
bc.security.player.password=player123
```

Permissions are based on role and and assignment.

- DIRECTOR users are considered admins with access to all operations.
- PLAYER users have write access on themselves and read access on the team to which they are assigned.