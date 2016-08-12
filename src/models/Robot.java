package models;

import utils.RobotUtils;

import java.util.LinkedList;

/**
 * Created by richardspellman on 11/08/2016.
 */
public class Robot {

  private int xPos;
  private int yPos;
  private char heading;

  private boolean instructionsComplete = false;

  private LinkedList instructionList;

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
   * Constructs a new Robot with initial x and y coordinates, an initial heading and instruction list.
   *
   * @param xPos Initial x position of this Robot
   * @param yPos Initial y position of this Robot
   * @param heading Initial heading of this Robot
   * @param instructionList This Robot's instruction list
   */
  public Robot(int xPos, int yPos, char heading, LinkedList instructionList){
    this.xPos = xPos;
    this.yPos = yPos;
    this.heading = heading;
    this.instructionList = instructionList;
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
   */
  public boolean executeMove(){
    switch (heading){
      case 'N':
        yPos++;
        return true;
      case 'W':
        xPos--;
        return true;
      case 'S':
        yPos--;
        return true;
      case 'E':
        xPos++;
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
