package tests;

import main.RobotManager;
import models.Robot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by richardspellman on 11/08/2016.
 */
public class RobotManagerTest {

  RobotManager robotManager;
  Scanner scanner;

  /**
   * Sets up the example test case.
   */
  @Before
  public void setUp(){
    robotManager = new RobotManager();
    // example test input
    String exampleInput = "5 5\n" +
        "1 2 N\n" +
        "LMLMLMLMM\n" +
        "3 3 E\n" +
        "MMRMMRMRRM\n" +
        "\n";
    scanner = new Scanner(exampleInput);

  }

  @After
  public void tearDown(){
    robotManager = null;
    scanner = null;
  }

  /**
   * Checks that the ArrayList of Robots created from the input strings contains the expected Robots and is of the
   * correct length.
   *
   * Note that the first raw input line - that representing the grid - is stripped out before making the assertions,
   * as RobotManager.getRobotsFromInput() expects this.
   *
   * @throws Exception
   */
  @Test
  public void testGetRobotsFromInput() throws Exception {
    ArrayList<Robot> actualRobots;
    ArrayList<String> inputLines = robotManager.getInputLines(scanner);
    inputLines.remove(0);
    actualRobots = robotManager.getRobotsFromInput(inputLines);
    Assert.assertEquals(2, actualRobots.size());

    Assert.assertEquals(1, actualRobots.get(0).getxPos());
    Assert.assertEquals(2, actualRobots.get(0).getyPos());
    Assert.assertEquals('N', actualRobots.get(0).getHeading());

    Assert.assertEquals(3, actualRobots.get(1).getxPos());
    Assert.assertEquals(3, actualRobots.get(1).getyPos());
    Assert.assertEquals('E', actualRobots.get(1).getHeading());


  }

  /**
   * Checks that each line read by scanner is as expected, given the example input.
   * @throws Exception
   */
  @Test
  public void testGetInputLines() throws Exception {
    ArrayList<String> inputLines = robotManager.getInputLines(scanner);
    Assert.assertEquals(5, inputLines.size());
    Assert.assertEquals("5 5", inputLines.get(0));
    Assert.assertEquals("1 2 N", inputLines.get(1));
    Assert.assertEquals("LMLMLMLMM", inputLines.get(2));
    Assert.assertEquals("3 3 E", inputLines.get(3));
    Assert.assertEquals("MMRMMRMRRM", inputLines.get(4));

  }

  /**
   * Tests running through the instruction list of the two robots in the example test case.
   * First asserts that robots are in expected starting position and heading, runs through instruction set for each
   * robot, then asserts that their final positions and headings are as expected.
   * @throws Exception
   */
  @Test
  public void testExecute() throws Exception {
    ArrayList<String> inputLines = robotManager.getInputLines(scanner);
    inputLines.remove(0);
    ArrayList<Robot> robots = robotManager.getRobotsFromInput(inputLines);

    Assert.assertEquals(1, robots.get(0).getxPos());
    Assert.assertEquals(2, robots.get(0).getyPos());
    Assert.assertEquals('N', robots.get(0).getHeading());

    Assert.assertEquals(3, robots.get(1).getxPos());
    Assert.assertEquals(3, robots.get(1).getyPos());
    Assert.assertEquals('E', robots.get(1).getHeading());

    robotManager.execute(robots);

    Assert.assertEquals(1, robots.get(0).getxPos());
    Assert.assertEquals(3, robots.get(0).getyPos());
    Assert.assertEquals('N', robots.get(0).getHeading());

    Assert.assertEquals(5, robots.get(1).getxPos());
    Assert.assertEquals(1, robots.get(1).getyPos());
    Assert.assertEquals('E', robots.get(1).getHeading());


  }

}