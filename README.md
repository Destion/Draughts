### Draugts AI 
**By University of Twente project group 11**

Draughts AI implemented using a provided Raspberry Pi B and DE1 SoC Board.

###Implementation
####Model
#####Board
A board has an array of all pieces with a position4
   a10 b10 c10 d10 e10 f10 g10 h10 i10
   a9  b9  c9  d9  e9  f9  g9  h9  i9
   a8  b8  c8  d8  e8  f8  g8  h8  i8
   a7  b7  c7  d7  e7  f7  g7  h7  i7
   a6  b6  c6  d6  e6  f6  g6  h6  i6
   a5  b5  c5  d5  e5  f5  g5  h5  i5
   a4  b4  c4  d4  e4  f4  g4  h4  i4
   a3  b3  c3  d3  e3  f3  g3  h3  i3
   a2  b2  c2  d2  e2  f2  g2  h2  i2
   a1  b1  c1  d1  e1  f1  g1  h1  i1

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
