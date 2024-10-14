# Multitower, a turn-based strategy game

## Abstract
Multitower is a simple turn-based strategy game that I have decided to build just for fun. The idea is to create an open-source game that can be forked or used by any other people.
Multitower is going to be developed in iterations of simple capabilities due to the fact that the team is composed of just one person.
It will have a Backend platform composed of some Java-based services. Services are available in this repository.
The game is going to be a board game that should be able to be played from a smartphone. The game will let the user create games with other players that want to join new games.
When the player has joined a new game, it will be able to move, recruit its units and attack the enemy units. Each player will have to defend a tower that will be far from the enemy’s tower.
When one of the players tower is destroyed, the game ends so the next player will finish.

## Development status

The project now is in an early development stage and the idea is to get to a point that a wireframe client application can interact
with the Backend, being able to register, login, start a game and play a game.

## Domain Entities

This diagram models the current Minimum Viable Product necessary entities to support a game server Backend for the game.

![Domain Entities](design/entity-design-bounded-contexts.svg)

## Microservices architecture

First approach of a microservices architecture to divide the bounded contexts found during the entities analysis.

![Microservices architecture](design/microservices-architecture.svg)

Right now, the project is composed by two services, the authorization server and the game server.

## Technological stack

* Java 21
* Spring Boot
* PostgreSQL