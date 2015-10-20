### Draugts AI 
**By University of Twente project group 11**

Draughts AI implemented using a provided Raspberry Pi B and DE1 SoC Board.

###Implementation
####Model
#####Board
A board has an array of all pieces with a position

#####Piece
A pieces has a color and is a type Men or King
The color is either black or white 
The type is either men or king

######Men
A man can only go left or right diagonal forward 1 space at a time. It can also capture the opponent by jumping over them if the space behind the opponent is free. This can also be backwards and multiple times if possible.

######King
A king can go left or right diagonal and go any amount of spaces forward or backwards. It can also capture the opponent by jumping over them just like the men. The king can also land wherever he wants. 

#####Color
An enum of either black or white

#####Position
A cartesian product with an horizontal and vercical value
For the horizontal axis we use: a-j (left to right)
For the vertical axi we use: 1-10 (bottom to top)

#####Player
A player can make a move

####View 
Gui classes...


####Controller
