# BowlingScorer

This bowling scorer was created as part of a coding challenge case study. The problem statement was to write a program that takes as input an array of integers representing a complete 10-pin bowling game, where each int represents the number of pins knocked down by a single roll, and outputs the score for the game.

--------
Info
--------
Developed in Eclipse Oxygen.3a Release (4.7.3a) - http://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/oxygen3a

Java JDK 8u171 - http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

To run the bowling scorer, compile in Eclipse with the JDK installed.

---------------
Assumptions
---------------
  - Users will enter input
  - No GUI required for input
  - Standard bowling rules apply: ten frames, two balls per frame unless a strike is rolled
  - Rolls must be valid entries

-----------
Scoring
-----------
Scores are added up for ten frames, each with ten possible pins.  If all ten pins are knocked down on the first roll, the score for that frame is 10 plus the next two rolls and there is no second roll for that frame. If all ten pins are knocked down after the second roll, the score for that frame is 10 plus the next roll.

Special rules apply to the tenth frame of the game. If a strike is earned, two more rolls are required to get the score for that frame, but these rolls are not added outside of determining the value of the strike (e.g. a strike is rolled followed by a roll of 1 and another roll of 1 - the score would account for the strike scoring (10 + 1 + 1), but would not additionally add the two pins rolled).  This same logic applies to a spare with one extra roll being required, but that roll only being used to calculate the spare scoring.

---------
Tests
---------
Test methods verify correct score is calculated for different cases - a perfect game, a game with no strikes or spares, a game with no pins knocked down, a game with a strike in the last frame, and a game with a spare in the last frame.

Input testing was done manually.
