# spaceShipGame
spaceShipGame created by Java - Orignal from class assignment, edited by the author
The game originally came from JAVA class assignment. 

One of the hightlights of this assignment, comparing with other classmates, is that it combines the Move() for both space ship and asteroid. Although the logic seems complex, but it makes the codes much more concise and efficient. 

Description: 
  1, play area: a 2D array with a 5 rows and 20 columns;
  2, rules: every element of 2D array has an icon, char 'S' representing spaceship, 'A' reprensenting 'asteroid';
  3, there are 5 spaceships in total at the beginning, and random number of asteroid during the game;
  4, spaceships move from far right column to the far left, they can go up, right and down randomly, they can not go out of the top and bottom lines;
  5, asteroids move only from far left column to far right;
  6, if a spaceship meets an asteroid, it will be destoryed, and will be counted as destroyedShip;
  7, if a spaceship meets another spaceship, it will not move at this round;
  8, if an asteroid meets another asteroed, they both will be removed;
  9, if a spaceship goes out of the far right column, means it has been escaped, and will be counted as escapedShip.

