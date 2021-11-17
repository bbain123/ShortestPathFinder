# ShortestPathFinder
A refined version of PathFinder where the shortest path in a neighbourhood is found.
## Tiles
***Middlesex College tile***: The starting point  
***Single house tile***: the destination  
***4-way tile***: The computer can move in any direction (up, down, left or right)  
***One-way tile***: The computer can enter from the base of the arrow and can only move in the direction of the head  
If a path is found, the tiles of the found path will turn red, and the terminal will tell you how  
long the path is. If no path is found, an appropriate message is displayed through the terminal.

## How to run
run CityMap.java with arguments *map1.txt, map2.txt*, *map3.txt* ... or *map8.txt*. You can also make your own board and pass that text file as an argument.  
> Ex. java CityMap map4.txt


## Configure a custom board
**To play a custom board, follow this format:**  
line-to-ignore (can write anything here)  
num-rows num-cols (speed) (speed is optional. The smaller the number, the faster it runs)  
*place each piece of the board*     
where S = start, E = end, B = blocked, C = 4-way, U = one-way up, D = one-way down, L = one-way left, R = one-way right   
>Ex.    
>this can be a blank line  
>5 5 50  
>B B B S B   
>C C C D C  
>B C B D C  
>E C L C B  
>B B B B B  
#### Note: Each row has 5 letters **separated by spaces** because there are 5 columns on the board, and there are 5 rows of letters because there are 5 rows on the board.
