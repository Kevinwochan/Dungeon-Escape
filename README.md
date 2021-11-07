# Dungeon Escape

## The Model View Controller (MVC)
- Model: Defines what the program knows and how it changes what it knows 
- View: Responsible for translating user interactions into beep boop bop instructions and presenting information intuitively
- Controller: A centralised interface that takes beep boop bop instructions and delegates the change to models

## Getting Started
### Playing the game
Run using the Main Class file in terminal or eclipse
type "help" for a list of commands available

#### Puzzle Mode

#### Design Mode



## The Software Design
### Design Pattern
#### Observer
Used to track each character's turns. Npcs and items (bombs) are all observers of the player 

#### Builder
Used to create dungeons in design mode

e.g when the user is in puzzle mode a dungeonBuilder is initalised
once the user selects a dungeon item to place on the maze a obect is generated, this object is added to the Dungon class,

#### Factory
Used in the design mode when adding things to the dungeon

e.g when a user enters design mode a dungeonFactory class is created
depending on user input, the dungeon factory generates dungeon objects at run time

Used to generate dungeon objects 

e.g when a user enters puzzle mode, the dungeon factory generates a list of default dungeon objects

#### State
Used for the overarching process

e.g when the application first starts it will be in the state Menu
from the menu state the user can eneter either Puzzle or Design state
based on this state the JavaFX will display the correct UI for each state.
The user will not be able to directly enter the puzzle state from the Design state ( and vice versa)
they will first need to enter the menu state

### Strategy

### Decorator
Used to define potions, items and npc behaviour

e.g an sword y uses is a class decorated by a use clase

### UML Diagram


### Dungeon
list of implemented dungeon objects

### Items
list of available items and effects

## The Tests

# Development
## Environment Depedencies
- Maven
- OpenJDK
- JavaFX
## Installing depdencies
`mvn clean javafx:run`
