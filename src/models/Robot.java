package models;

import utils.RobotUtils;

import java.util.LinkedList;

/**
 *
 * Class representing a Robot explorer.
 * <p>
 * A robot's position and location is represented by a combination of x and y co-ordinates and a letter
 * representing one of the four cardinal compass points. An example position might be 0, 0, N, which means the
 * robot is in the bottom left corner and facing North. Each robot also has an instruction set, represented by a
 * string of values indicating either a turn (L for left, R for Right) which changes the robots heading by 90 degrees
 * in the given direction, or a move (M) which moves the robot one square in the direction of it's current heading.
 * <p>
 * Robots navigate a rectangular grid. If a robot's instruction set results in the robot being given instructions to
 * exit the grid, the robot will pause and execute the next instruction that does not result in it exiting the grid.
 * <p>
 * Created by richardspellman on 11/08/2016.
 */
public class Robot {

  private int xPos;
  private int yPos;
  private char heading;
  private boolean instructionsComplete = false;
  private LinkedList instructionList;

  private int boundaryX;
  private int boundaryY;


  /**
   * Constructs a new Robot with initial x and y coordinates and initial heading.
   * @param xPos Initial x position of this Robot.
   * @param yPos Initial y position of this Robot.
   * @param heading Initial heading of this Robot.
   */
  public Robot(int xPos, int yPos, char heading){
    this.xPos = xPos;
    this.yPos = yPos;
    this.heading = heading;
  }

  /**
   * Constructs a new Robot with initial x and y coordinates and initial heading and landing area boundary values.
   * @param xPos Initial x position of this Robot.
   * @param yPos Initial y position of this Robot.
   * @param heading Initial heading of this Robot.
   * @param boundaryX The x coordinate of the eastern most boundary of the landing area.
   * @param boundaryY The y coordinate of the northern most boundary of the landing area.
   */
  public Robot(int xPos, int yPos, char heading, int boundaryX, int boundaryY){
    this.xPos = xPos;
    this.yPos = yPos;
    this.heading = heading;
    this.boundaryX = boundaryX;
    this.boundaryY = boundaryY;
  }

  /**
   * Constructs a new Robot with initial x and y coordinates, an initial heading and instruction list and landing
   * area boundary values.
   *
   * @param xPos Initial x position of this Robot
   * @param yPos Initial y position of this Robot
   * @param heading Initial heading of this Robot
   * @param instructionList This Robot's instruction list
   * @param boundaryX The x coordinate of the eastern most boundary of the landing area.
   * @param boundaryY The y coordinate of the northern most boundary of the landing area.
   */
  public Robot(int xPos, int yPos, char heading, LinkedList instructionList, int boundaryX, int boundaryY){
    this.xPos = xPos;
    this.yPos = yPos;
    this.heading = heading;
    this.instructionList = instructionList;
    this.boundaryX = boundaryX;
    this.boundaryY = boundaryY;
  }

  /**
   * Run sequentially through this robot's list of instructions , if not already complete.
   *
   * @return Currently always returns true.
   */
  public boolean executeInstructions() {
    if (!instructionsComplete) {
      for (Object instruction : instructionList) {
        if ((char) instruction == 'M') {
          executeMove();
        } else {
          executeTurn((char) instruction);
        }

      }
      instructionsComplete = true;
    }
    return true;
  }

  /**
   * Changes the heading of the Robot by making a left or right turn.
   *
   * @param direction The direction in which the Robot should turn
   */
  public void executeTurn(char direction){
    heading = RobotUtils.getNextDirection(heading, direction);
  }

  /**
   * Changes the position of the Robot based on it's current heading.
   *
   * Resultant position is constrained by the landing grid.
   */
  public boolean executeMove(){
    switch (heading){
      case 'N':
        yPos = Math.min(yPos + 1, boundaryY);
        return true;
      case 'W':
        xPos = Math.max(xPos - 1, 0);
        return true;
      case 'S':
        yPos = Math.max(yPos - 1, 0);
        return true;
      case 'E':
        xPos = Math.min(xPos + 1, boundaryX);
        return true;
      default:
        return true;
    }

  }

  /**
   *
   * @return This Robot's current x position.
   */
  public int getxPos() {
    return xPos;
  }

  /**
   *
   * @param xPos The x position to assign to this Robot.
   */
  public void setxPos(int xPos) {
    this.xPos = xPos;
  }

  /**
   *
   * @return This Robot's current y position.
   */
  public int getyPos() {
    return yPos;
  }

  /**
   *
   * @param yPos The y position to assign to this Robot.
   */
  public void setyPos(int yPos) {
    this.yPos = yPos;
  }

  /**
   *
   * @return This Robot's current heading.
   */
  public char getHeading() {
    return heading;
  }

  /**
   *
   * @param heading The new heading to assign to this Robot.
   */
  public void setHeading(char heading) {
    this.heading = heading;
  }

  /**
   *
   * @return This Robot's instruction list.
   */
  public LinkedList getInstructionList() {
    return instructionList;
  }

  /**
   * Allows a reset of the Robot's instruction list.
   *
   * The instructionsComplete field is also reset to false.
   * @param instructions A LinkedList of instructions
   */
  public void setInstructionList(LinkedList instructions) {
    this.instructionList = instructions;
    instructionsComplete = false;
  }

  /**
   * Unimplemented.
   */
  public void appendInstructions(){

  }

  /**
   *
   * @return
   */
  @Override
  public String toString(){
    return xPos + " " + yPos + " " + heading;
  }

}
