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

  private LinkedList instructionList;

  /**
   *
   * @param xPos
   * @param yPos
   * @param heading
   */
  public Robot(int xPos, int yPos, char heading){
    this.xPos = xPos;
    this.yPos = yPos;
    this.heading = heading;
  }

  /**
   *
   * @param xPos
   * @param yPos
   * @param heading
   * @param instructionList
   */
  public Robot(int xPos, int yPos, char heading, LinkedList instructionList){
    this.xPos = xPos;
    this.yPos = yPos;
    this.heading = heading;
    this.instructionList = instructionList;
  }

  /**
   * Run sequentially through this robot's list of instructions
   * @return
   */
  public boolean executeInstructions(){
    for(Object instruction : instructionList){
      if((char) instruction == 'M'){
        executeMove();
      } else {
        executeTurn((char) instruction);
      }

    }
    return true;
  }

  /**
   *
   * @param direction
   */
  public void executeTurn(char direction){
    heading = RobotUtils.getNextDirection(heading, direction);
  }

  /**
   *
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

  public int getxPos() {
    return xPos;
  }

  public void setxPos(int xPos) {
    this.xPos = xPos;
  }

  public int getyPos() {
    return yPos;
  }

  public void setyPos(int yPos) {
    this.yPos = yPos;
  }

  public char getHeading() {
    return heading;
  }

  public void setHeading(char heading) {
    this.heading = heading;
  }

  public LinkedList getInstructionList() {
    return instructionList;
  }

  public void setInstructionList(LinkedList instructions) {

  }

  public void appendInstructions(){

  }

  @Override
  public String toString(){
    return xPos + " " + yPos + " " + heading;
  }

}
