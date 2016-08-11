import models.Robot;
import utils.RobotUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by richardspellman on 11/08/2016.
 */
public class RobotManager {
  private int gridWidth;
  private int gridHeight;

  private ArrayList<Robot> robots = new ArrayList<Robot>();

  Scanner scanner = new Scanner(System.in);

  public static void main(String[] args){
    RobotManager rm = new RobotManager();
    System.out.println("Paste input below, make sure to include an eof character:");
    rm.parseInput();

    rm.robots.get(0).executeInstructions();
    rm.robots.get(1).executeInstructions();

    System.out.println(rm.robots.get(0).toString());
    System.out.println(rm.robots.get(1).toString());
  }

  private void parseInput(){
    String[] firstLine = scanner.nextLine().split("\\s+");
    gridWidth = Integer.valueOf(firstLine[0]);
    gridHeight = Integer.valueOf(firstLine[1]);

    while(true){
      String robotString = scanner.nextLine();
      if(robotString.equals("")) break;
      String instructionString = scanner.nextLine();
      String[] robotVals = robotString.split("\\s+");
      int x = Integer.valueOf(robotVals[0]);
      int y = Integer.valueOf(robotVals[1]);
      char h = robotVals[2].charAt(0);
      LinkedList instructionList = RobotUtils.createInstructionList(instructionString);

      robots.add(new Robot(x, y, h, instructionList));
    }
  }
}
