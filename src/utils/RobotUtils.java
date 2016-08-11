package utils;

import models.Robot;

import java.util.LinkedList;

/**
 * Created by richardspellman on 11/08/2016.
 */
public class RobotUtils {

  private final static char[] directions = {'N', 'E', 'S', 'W'};

  public static LinkedList createInstructionList(String instructions){
    LinkedList instructionList = new LinkedList();
    for(int i = 0; i < instructions.length(); i++){
      instructionList.add(instructions.charAt(i));
    }
    return instructionList;
  }

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

  public void printRobotPositions(Robot[] robots){

  }
}
