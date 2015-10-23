### Draugts AI 
**By University of Twente project group 11**

Draughts AI implemented using a provided Raspberry Pi B and DE1 SoC Board.

###Implementation
####model
#####Board
A board has an map of all pieces with a position.
- The key is the Position object
- The value an Piece object or its empty


#####Piece
A pieces has a color and is a type Men or King
- The color is either black or white 
- The type is either men or king
The piece has a function validMove(Board board) to check wether this move is valid.

######Men
A man can only go left or right diagonal forward 1 space at a time. It can also capture the opponent by jumping over them if the space behind the opponent is free. This can also be backwards and multiple times if possible.

######King
A king can go left or right diagonal and go any amount of spaces forward or backwards. It can also capture the opponent by jumping over them just like the men. The king can also land wherever he wants. 

#####Color
An enum of either black or white

#####Position
A cartesian product with an horizontal and vercical value
- For the horizontal axis we use: a-j (left to right)
- For the vertical axi we use: 1-10 (bottom to top)

#####Player
A player can make a move. This is an abstract class with 1 shared function. makeMove() returns a Positon object to tell the game that he want to make that move.
######HumanPlayer
This the human player. Getmove() lets the human player make a move.
######Computerplayer
Getmove() asks the FPGA controller for a move. It sends the board to for the AI to calculate the move.

####view
Gui classes...


####Controller
