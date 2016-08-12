package main;

import models.Robot;
import utils.RobotUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Robot Swarm Manager
 *
 * This class contains a main method which, when run, handles user input from the command line, creates the robots
 * represented by the input and runs through each of their instructions, printing output to command line.
 *
 * Created by richardspellman on 11/08/2016.
 */
public class RobotManager {
  private int gridWidth;
  private int gridHeight;

  private ArrayList<Robot> robots;

  private Scanner scanner;

  // validation patterns
  Pattern gridPattern = Pattern.compile("\\d+\\s\\d+");
  Pattern robotPattern = Pattern.compile("\\d+\\s\\d+\\s[NSEW]");
  Pattern instructionPattern = Pattern.compile("[LRM]+");

  /**
   * Takes input from command line, parses to create Robots then executes all instructions and prints output.
   *
   */
  public static void main(String[] args){

    RobotManager rm = new RobotManager();
    rm.scanner = new Scanner(System.in);
    System.out.println("Paste or enter input below, make sure to include an EOF character:");
    ArrayList<String> inputLines = new ArrayList<>();

    boolean continueInput = true;
    while(continueInput) {
      inputLines = rm.getInputLines(rm.scanner);
      try {
        if(rm.validate(inputLines)) continueInput = false;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
        System.out.println("Paste or enter input below, make sure to include an EOF character:");
        inputLines = rm.getInputLines(rm.scanner);
      }
    }
    String[] gridString = inputLines.remove(0).split("\\s+");

    rm.gridWidth = Integer.valueOf(gridString[0]);
    rm.gridHeight = Integer.valueOf(gridString[1]);

    rm.robots = rm.getRobotsFromInput(inputLines);
    rm.execute(rm.robots);
    rm.printPositions(rm.robots);

  }

  /**
   * Gets input lines from a Scanner object.
   * @param scanner The Scanner object to read from.
   * @return Returns and ArrayList of strings representing the input.
   */
  public ArrayList<String> getInputLines(Scanner scanner){
    ArrayList<String> inputLines = new ArrayList<String>();
    while(scanner.hasNextLine()){
      String line = scanner.nextLine();
      if(line.length() == 0){
        break;
      } else {
        inputLines.add(line);
      }
    }

    return inputLines;
  }

  /**
   * Creates an ArrayList of Robots from a list of input lines.
   *
   * Assumes that the initial line of input, representing the grid size, has been stripped out.
   * @param inputLines An ArrayList of input Strings.
   * @return An ArrayList of Robot Objects.
   */
  public ArrayList<Robot> getRobotsFromInput(ArrayList<String> inputLines){
    ArrayList<Robot> robots = new ArrayList<Robot>();
    for(int i = 0; i < inputLines.size() - 1; i += 2){
      String[] robotLine = inputLines.get(i).split("\\s+");
      String instructionLine = inputLines.get(i + 1);
      LinkedList instructionList = RobotUtils.createInstructionList(instructionLine);
      int xPos = Integer.valueOf(robotLine[0]);
      int yPos = Integer.valueOf(robotLine[1]);
      char heading = robotLine[2].charAt(0);

      robots.add(new Robot(xPos, yPos, heading, instructionList));
    }
    return robots;

  }

  /**
   * Executes the command list for all Robots in the input ArrayList.
   * @param robots An ArrayList of Robot Objects.
   */
  public void execute(ArrayList<Robot> robots){
    for(int i = 0; i < robots.size(); i++){
      robots.get(i).executeInstructions();
    }
  }

  /**
   * Prints the position and orientation of all Robots in the input ArrayList
   * @param robots An ArrayList of Robot Objects.
   */
  public void printPositions(ArrayList<Robot> robots){
    for(int i = 0; i < robots.size(); i++){
      System.out.println(robots.get(i).toString());
    }
  }

  /**
   * Validates input from scanner again regex patterns representing expected format.
   *
   * Patterns:
   *   gridPattern  - "\\d+\\s\\d+" : Any two integers seperated by whitespace character
   *   robotPattern - "\\d+\\s\\d+\\s[NSEW]" : Any two integers seperated by a whitespace character and ending in
   *                                           either an N, S, E or W
   *   instructionPattern - "[LRM]+" : Any sequence of the characters L, R or M.
   *
   * @param inputLines The ArrayList of input lines to validate
   * @return True if all input lines match relevant regex
   * @throws IllegalArgumentException if input line does not match regex
   */
  public boolean validate(ArrayList<String> inputLines) {
    if (!gridPattern.matcher(inputLines.get(0)).matches()) {
      throw new IllegalArgumentException("ERROR: First line of input must contain two integers separated by a space");
    }
    for (int i = 1; i < inputLines.size() - 1; i += 2) {
      if (!robotPattern.matcher(inputLines.get(i)).matches()) {
        throw new IllegalArgumentException("ERROR: Lines defining robot's starting position should be in the form '1 2 N' ");
      }
      if (!instructionPattern.matcher(inputLines.get(i + 1)).matches()) {
        throw new IllegalArgumentException("ERROR: Lines defining robot's instructions must only contain characters L, R or M");
      }
    }
    return true;
  }

}
