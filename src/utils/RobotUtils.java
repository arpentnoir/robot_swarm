package utils;

import java.util.LinkedList;

/**
 * Contains some utility methods for handling input values and direction changes.
 *
 * Created by richardspellman on 11/08/2016.
 */
public class RobotUtils {

  private final static char[] directions = {'N', 'E', 'S', 'W'};

  /**
   * Creates a LinkedList of instructions from a given String.
   * @param instructions A String representing the list of instructions.
   * @return A LinkedList of instructions.
   */
  public static LinkedList createInstructionList(String instructions){
    LinkedList instructionList = new LinkedList();
    for(int i = 0; i < instructions.length(); i++){
      instructionList.add(instructions.charAt(i));
    }
    return instructionList;
  }

  /**
   * Gets the resultant heading given a current heading and a direction to turn.
   *
   * @param currentHeading A char representing the current heading, either 'N', 'S', 'E' or 'W'
   * @param turn A char representing the direction to turn, either 'L' or 'R'
   * @return A char representing the resultant direction from the given turn, either 'N', 'S', 'E' or 'W'.
   */
  public static char getNextDirection(char currentHeading, char turn){
    switch(turn){
      case 'L':
        return (currentHeading == 'N') ? 'W' : directions[getHeadingIndex(currentHeading) - 1];
      case 'R':
        return directions[(getHeadingIndex(currentHeading) + 1) % 4];
      default:
        throw new IllegalArgumentException();
    }

  }

  /**
   * Returns the index of the given heading.
   *
   * Used by RobotUtils.getNextDirection()
   * @param heading A char representing the heading to look up.
   * @return The position in the heading array of the given char.
   */
  private static int getHeadingIndex(char heading){

    switch(heading){
      case 'N':
        return 0;
      case 'E':
        return 1;
      case 'S':
        return 2;
      case 'W':
        return 3;
      default:
        throw new IllegalArgumentException();
    }
  }

}
