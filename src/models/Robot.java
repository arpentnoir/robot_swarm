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

  public Robot(int xPos, int yPos, char heading){
    this.xPos = xPos;
    this.yPos = yPos;
    this.heading = heading;
  }

  public Robot(int xPos, int yPos, char heading, LinkedList instructionList){
    this.xPos = xPos;
    this.yPos = yPos;
    this.heading = heading;
    this.instructionList = instructionList;
  }

  public boolean executeInstructions(){
    for(Object instruction : instructionList){
      if((char) instruction == 'L'|| (char) instruction == 'R'){
        executeTurn((char) instruction);
      } else {
        executeMove();
      }

    }
    return true;
  }

  public void executeTurn(char direction){
    heading = RobotUtils.getNextDirection(heading, direction);
  }

  public void executeMove(){
    switch (heading){
      case 'N':
        yPos++;
        break;
      case 'W':
        yPos++;
        break;
      case 'S':
        yPos--;
        break;
      case 'E':
        xPos--;
        break;
      default:
        throw new IllegalArgumentException();
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
