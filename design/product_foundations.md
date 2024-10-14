# Multitower definition

## Abstract

Multitower is a simple turn-based strategy game that I have decided to build just for fun. The idea is to create an open-source game that can be forked or used by any other people. Multitower is going to be developed in iterations of simple capabilities due to the fact that the team is composed of just one person. It will have a Backend platform composed of some services, all of them in a single repository. Frontend will also have a single repository.

The game is going to be a board game that should be able to be played from a smartphone. The game will let the user create games with other players that want to join new games. When the player has joined a new game, it will be able to move, recruit its units and attack the enemy units. Each player will have to defend a tower that will be far from the enemy’s tower.

When one of the players tower is destroyed, the game ends so the next player will finish.

## Joining the game

A new user will need to register to the platform.
The email will be used to identify and validate the user.
The user will provide a password that the platform will store securely.
An Oauth2 authorization server will be used in order to send the resource server an authorization token that will identify the customer in the platform securely.
Tokens will have an expiry. The user will also be able to login, logout and unregister from the platform.
At least Google social provider will be implemented.

## Home screen

The home screen will be simple, providing two buttons with a nice background.
One button will be to create a new game and another one to see the existing games the user has joined to.
A configurable limit of ongoing games per player is a good idea.

## Creating a new game

Once the user is registered and logged in, he/she will just want to play!
So, in the UI the user will be able to click the “Join a new game” button.
A matchmaker algorithm will need to select users from a queue of users waiting for a new game,
and automatically provide each of the users with the new game screen.

## Game mechanics

When a game starts, a coin is tossed, selecting which player will be the first to select its units.
Given a predefined game balance, users will be able to recruit units. When both players have completed the initializing recruiting turn, the first moving turn will start. The turn will let the player move all its units with its maximum capacity. It may be a good idea to let the user undo a move but an attacking move cannot be undone. When all its units cannot do any further moves, then the turn will be finished by the player.

Board should not be a large map, and it should be similar to isometric games.
It should be a small 2D map in order to play short games.
In later revisions of multitower, a map selection step may be a great addition in order to add a collection of maps to the game.

Each turn, the balance of a user will be increased so it can recruit new units nearby its tower again.

The game will finish when one of the users is able to destroy the enemy tower.
When the game is finished players will be asked if they want to create another game.

Stalled games are a likely scenario, so we should consider adding a mechanism to notify users that the game
has not been played in a configurable period of time and that if he doesn’t play in a period of time the game
will be won by the other user if he/she are active.

## Unit attributes

The game should have a flexible unit engine. Units will have some base attributes that will be related with their nature, there are some MUST attributes each unit will need.

* Cost in game currency

* Health points (HP)

* Horizontal movement

* Vertical movement

* Attack points (AP)

* Attack type: An enumeration of attack types should be able to be created. Some of these are:

  * Melee

  * Piercing

  * Ammunition

  * Magic

  * Gunpowder

  * Artillery

* Attack range (number of tiles of distance the unit can attack. 0 for melee, others for range units)

* Defense points (DP)

* Defense type: Based on the available attack types. A unit may have different defense points for each attack type.

When a unit moves, the horizontal and vertical movement attributes should be used to validate that a unit is able to do the move.

When a unit attacks another unit, the attack attributes will be used to decrease the attacked unit HP accordingly the attacker attack points,
the attack type and the defender defense type points.

Unit attributes may change in time, so we need to make sure that when a unit attribute is edited due to a rebalance, games that already started
are not altered, and the unit still has the same attributes during the game duration.

It may be nice that units have some kind of XP progress, but this is a nice to have feature that may be revisited in later revisions.

## Unit enumeration

* Militia: Cheap unit with basic melee attributes

* Lancer: Cheap unit with basic piercing melee attributes

* Archer: Cheap distance unit with piercing attack

* Swordsman: Medium unit with good melee attack and average defense

* Elite Pikeman: Medium unit with good piercing attack and average defense

* Elite Guard: Medium unit with good melee, piercing, ammunition defense

* Knight: Elite unit with great melee attack and movement range. High average defense.

* Dismounted Knight: A more cost-effective version of the knight with less movement range and a bit less attack.

* Wizard: Costly unit with high magic attack, but little defense.

* Musketeer: Costly unit with high gunpowder attack, but little defense.

* Golem: Costly unit. Unit with great defense attributes and nice melee.

* Catapult: Costly unit with nice artillery attack and little movement range and defense

## Statistics engine

Winning a game will award an amount of points to the user that will be available through statistics.
A ladder should be a good idea in order to have a ladder of friends, adversaries and a global ladder.

## Player networking?

NOT a mandatory feature, but nice to have. When players are playing a game it may be important to be able to interact
with other players. Some nice to have features would be:

* In-game chat interaction
* Player friends
* A chat to interact with friends. From this chat, it may also be possible to 

## Push notifications

Reminders and player communication may be implemented by sending push notifications.