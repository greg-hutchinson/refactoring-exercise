# Refactoring Exercise Observations

After the refactoring exercise these were my observations.

Most of you were able to use the extract method, to pull out the individual
commented pieces and to give them meaningful names. (i.e isInvalidTarget, isInvalidHorizontalMove, etc.). These are all
mechanical refactorings and are absolutely good to do. Everything only had one level of indentation in the end, meaningful names, etc.
You "improved the design of existing code" so these were good refactorings.

Where some of you still some room for more refactoring were in two areas:
- There was still some duplicate code in the code that checked whether the paths were clear between horizontal moves
and vertical moves. (See Position class >>  getPathTo(Position target) for one solution to this.
- The "***looks like the language was designed for the problem***" was not completely met.

***Please Note***: this last one takes a good amount of practice. 

A couple of you came up with solutions to get rid of the first point above - also well done. The 
existing code was pretty convoluted. (On purpose, of course, because I have a mean streak in me - but I digress. :-)  )

The main point is that the refactorings are still probably not done. I believe that as soon as you went to implement
isInvalidMove for a Bishop, you would ***all*** have to re-refactor some/most of the methods that you have just finished. 
And... that's ok and is normal. You all have improved the design of existing code and you will have to do it again.

I have now published a solution for you to take a look at if you are interested, and this was my thought process after
I had done the initial refactorings that most of you had done as well.
- I didn't like all the negativity in the code. :-) (i.e. isInvalidMove() didn't read as nice as isValidMove())
- I had lots of code that I needed to share between Rook and Bishop (and other classes) 
- I felt like there was more code in the Rook than there should be
- I wanted it to be as easy as possible to add each new Piece - Compare the final code in Rook and Bishop
- And most importantly, I wanted the code to look like ***the language was designed for the problem***. 

I think the key to this were a couple of things:
- I added a class Move - which encapsulated the concept of a Chess move being a source and target position on a chessboard. 
I was able to push a lot of the details about horizontal and vertical movement and paths between objects here. This has the
extra benefit that all this code can be tested independently of any of the ChessPiece classes.
- Pushed several methods to the Position class (Enum). If you find that you are taking an object apart by querying its
data and making decisions, consider moving the behaviour to this class. In this example it is quite trivial but this 
is quite important in other scenarios. (I.e. 
```java
    //This queries the data of the object and makes decisions about what to do
    //This is a red-flag that maybe the object itself should have this behaviour.
    private boolean isValidHorizontalMove(Position targetPosition) {
       return targetPosition.x == getPosition().x;  //get the x coordinate from both objects
    }
```
now becomes something to the effect of
```java
    //Push the behaviour to the class that we were querying the data from
    private boolean isValidHorizontalMove(Position targetPosition) {
       return getPosition().isHorizontalTo(targetPosition);
    }
    //And this would then probably become in-lined within the Rook class
```

Anyway - Take a look at the published solution. Feedback is welcome and thanks everyone. Keep Refactoring.