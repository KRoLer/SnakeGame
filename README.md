## Snake game
Your task is to model a Snake game, logic wise only.
There's a summary of what Snake is below.

No need for a fancy UI or threading, if you need to display something do it in Std.out!

#### Steps:
  1) Model the board (that wraps around), the snake and food pellets.
  2) Place a size 2 snake in the middle of the board and a food somewhere else than the snake.
  3) `Display` board state in the console (ASCII "art")
  4) Define a method that performs a 'step' of the game
    - takes a possible user input as parameter
    - is called from outside (Launcher?) at a regular time interval
    - update the board's state
  5) Code an input sequence that eats the first food pellet.
  6) Randomise food pellet placement


### Summary: Snake game

1. You control a Snake that moves around in a grid, the snake spans over the 2 cells at the beginning.
2. You can only turn the snake left or right and it moves one cell every "game turn".
3. There is always one food pellet on the grid.
4. Goal of the game is to eat said pellets by moving the snake to its position.
5. When the snake eats a pellet it grows one cell longer.
6. If the snake collides with itself, the game is lost.
7. If the snake reaches one of the grid's borders, it wraps around (i.e. going out on the left side means you come back in on the right side).

## How to run
To run the prepared solution 
```bash
> git clone https://github.com/KRoLer/SnakeGame.git
> cd SnakeGame
> sbt run
```
To run the tests
```bash
sbt test
```

## Customization
There are two ways to run the "game":
- Sequential - to run all steps and show the final result
- Step by Step - to run step by step and show only current board's state 

Also the board size and fruits could be changed 
```scala
Board(width = 5, height = 5, fruits = List(Cell(1, 2), Cell(5, 4)))
```
The board size limitation for displaying in the console is 500 cells height or width to avoid OOM.
The board bigger 500 cells could be played in tests, for example see the `GameSpec.scala` to play on 50000x50000 board with 20001 moves. 