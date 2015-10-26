### Draugts AI 
**By University of Twente project group 11**

Draughts AI implemented using a provided Raspberry Pi B and DE1 SoC Board.

###Implementation
####Model
#####Board (Class)
The board is the center of the game.

It has an map of all pieces with a position.
- The key is the Position object
- The value an Piece object or its empty

It has methods to check:
- hasWinner() if the board has a winner
- isWinner() who is the winner
- draw() if its a draw
- gameOver() if the game is over (draw or winner)
- freePosition(Position) if the position given is free and not out of bounds

It also has methods to mutate the board:
- initializeBoard() to setup a board for playing a game
- move(Move, Colour) takes the move object to make the move on the board
- promotePiece(Position,Piece) promotes a Man to a King on the board
- generatePossibleMoves(Colour) generates all possible move for a player

generatePossibleMoves(Colour) is the main function to forward to the player. 
If a player must capture another piece, then the only possibility is to capture. If not then the only possibilty is the move a piece.

#####Piece (Interface)
A piece is an interface defining the following methods:
- getColour() returns a colour (Black or white) of the piece 
- validMove() to check whether the move is valid
- canMove() to check whether this piece can move on the board
- canCapture() to check whether this piece can capture another piece
- movesOnPosition() lists the possible moves on a particular position for a piece
- capturesOnPosition() lists the possible captures on a particular position for a piece

In this game, a Piece is either a Man or a King which each have their own properties.

######Men (Class implements Piece)
A man can only go left or right diagonal forward 1 space at a time. It can also capture the opponent by jumping over them if the space behind the opponent is free. This can also be backwards and multiple times if possible.

It implements the piece interface and its methods.

######King (Class implements Piece)
A king can go left or right diagonal and go any amount of spaces forward or backwards. It can also capture the opponent by jumping over them just like the men. The king can also land wherever he wants. 

It implements the piece interface and its methods.

#####Color (Class)
An enum of either black or white

#####Position (Class)
A cartesian product with an horizontal and vertical value
- For the horizontal axis we use: 1-10 (left to right)
- For the vertical axi we use: 1-10 (bottom to top)

#####Move (Class)
A move with an old position, a new position, the positions in between and the positions captured.

The following fields are available:
- Position oldPos is the old position of the piece
- Position newPos is the new position of the piece
- List<Positions> interPos is the positions between the old and new. This can only be filled if the piece captures other pieces.
- List<Positions> captured is all the positions which the move has captured. This can be from a single step or multiple (zigzag).

It also has a method calculateCaptured() to calculate which positions (which are pieces) are captured. When a move is created, this method is called.

#####Player (Abstract Class)
This abstract with 1 shared method. makeMove() makes a move on the board from the lists of move he can make. 

The way the move is chosen is implemented by determineMove() which is here an abstract class.

######HumanPlayer (Class extends Player)
This the human player. determineMove() lets the human player make a move. For now this is just with a TUI.

######Computerplayer (Class extends Player)
determineMove() asks the CommunicationsController for a move. It sends the board to for the AI to calculate the move.

####View
Gui classes...


####Controller

#####CommunicationsController (Class)
This controller controls all the communications between the Raspberry Pi and the FPGA.

#####GameController (Class)
This controller controls the gamestate and checks if the game is won or lost after a move is made.

#####GuiController (Class)
The GuiController makes sure the game is correctly displayed on the the monitor.
