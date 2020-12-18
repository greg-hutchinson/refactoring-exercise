Kurt,

I liked where you were going with the refactor - I can tell that you 
were just running out of time. I think that you would have also seen 
that there were some behaviours between the pieces that you could have 
also pushed up so that they were inherited. IMO - these are good 
refactors, because code duplication is the worse violation.

The downside to this is that the overview of the abstract class sometimes get polluted with all these 
"Convenience" methods for the subclasses. I have found that after I have done that, it sometimes becomes clear
that a lot of these convenience methods can be encapsulated into another Class - a Move perhaps. I see that
some of your methods refer to path (i.e. isPathClear). I had that at one point and created a class Path
which I ended up renamimg to Move in the end.

Lastly, getHorizontalPositions and getVerticalPositions can probably be refactored down into 
getPositions  - with just a little more smarts for the startingX, startingY
int startX = getPosition().getXOffset();
int endX = targetPosition.getXOffset();
int incrementX = getIncrement(startX, endX);
int startY = getPosition().getYOffset();
int endY = targetPosition.getYOffset();
//Now iterate ...
etc.

Overall - I think you were headed in the right direction and as I said, all the refactors
were good refactors (good names - ie, isLShapedMoved, isHorizontal, - you were making methods that made
the language look like it was designed for the problem. This is the sweet spot IMHO.
Thanks for taking the time to do the exercise.

