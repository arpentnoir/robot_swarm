import models.Robot;

import java.util.ArrayList;
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

  }

  private void parseInput(){
    gridWidth = scanner.nextInt();
    gridHeight = scanner.nextInt();
    while(scanner.hasNext()){

    }
  }
}
