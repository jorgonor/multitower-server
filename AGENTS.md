# Multitower server monorepo project

This repository includes some Gradle modules with dependencies between them in order to implement
the backend system of a tower defense game. The architecture is based on.

- Microservices.
- Clean architecture.

# Services overview

Modules may be application or java-library. The application modules actually are services, and the java-library
are used in order to implement clear layers of separation between different domains and infrastructure.
Clean architecture is the pattern to follow regarding code organization.

These are some explanations about the intent in each module.

# Clean architecture

Domain objects must be isolated from infrastructure logic.
Infrastructure logic is any code that is coupled to any external system like Redis, Valkey or a RDBMS database.
Domain modules define application services that act as the ports between the domain and the infrastructure when implemented.
Implementations of application services must be in infrastructure modules that may be imported or not and easily replaceable.
Application services must be in the java package "application.service" below the module base java package.
Entities must be in the java package "entity" below the module base java package.
Entities need identity objects in order to define the identity of the entity. These must be in the java package "identity".
Value objects are immutable objects that help define concepts in the domain that are immutable by nature.
They can be used in entities and as data transfer objects, method arguments and results.

## auth-server

Implements an authorization server using the spring boot oauth2 starter as its foundation. This server
is the responsible for authenticating the users that use the game and acts as the authorization server
for all the services that need to deal with user information.

## game-server

Contains the REST API that uses authentication tokens from the authorization server to authenticate the users.
The REST API is the interface to the game business logic that will be implemented in models.

## users-domain

Base module for the user domain. Defines the user related domain entities and the application services to glue
the domain with outer code.

## users-infrastructure

Base module for the user domain infrastructure. Contains the adapters with the code to communicate
with databases or dependencies that may couple the domain.

# Technical details

- Use Lombok to implement getters and setters when possible.
- Entities should use lombok.Data.
- Value objects must be immutable (avoid records, use lombok @Value annotation).