package tests;

import models.Robot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.RobotUtils;


import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by richardspellman on 11/08/2016.
 */
public class RobotTest {


  Robot robot1;
  Robot robot2;

  LinkedList instructionList1;
  LinkedList instructionList2;

  /**
   * Set up and check example test case.
   */
  @Before
  public void setUp(){

    // Example test setup
    instructionList1 = RobotUtils.createInstructionList("LMLMLMLMM");
    robot1 = new Robot(1, 2, 'N', instructionList1, 5, 5);

    instructionList2 = RobotUtils.createInstructionList("MMRMMRMRRM");
    robot2 = new Robot(3, 3, 'E', instructionList2, 5, 5);

    Assert.assertEquals(1, robot1.getxPos());
    Assert.assertEquals(2, robot1.getyPos());
    Assert.assertEquals('N', robot1.getHeading());

    Assert.assertEquals(3, robot2.getxPos());
    Assert.assertEquals(3, robot2.getyPos());
    Assert.assertEquals('E', robot2.getHeading());

  }

  @After
  public void tearDown(){
    instructionList1 = null;
    instructionList2 = null;

    robot1 = null;
    robot2 = null;
  }

  /**
   * Checks that the Robot's expected position and heading are as expected after executing it's instruction list.
   * @throws Exception
   */
  @Test
  public void testExecuteInstructions() throws Exception {
    Assert.assertTrue(robot1.executeInstructions());
    Assert.assertTrue(robot2.executeInstructions());

    Assert.assertEquals(1, robot1.getxPos());
    Assert.assertEquals(3, robot1.getyPos());
    Assert.assertEquals('N', robot1.getHeading());

    Assert.assertEquals(5, robot2.getxPos());
    Assert.assertEquals(1, robot2.getyPos());
    Assert.assertEquals('E', robot2.getHeading());

  }

  /**
   * Checks that a left or right turn results in the expected orientation, given all starting headings
   * @throws Exception
   */
  @Test
  public void testExecuteTurn() throws Exception {
    //
    Robot turningRobot = new Robot(0, 0, 'N');
    Assert.assertEquals('N', turningRobot.getHeading());

    turningRobot.executeTurn('L');
    Assert.assertEquals('W', turningRobot.getHeading());
    turningRobot.executeTurn('L');
    Assert.assertEquals('S', turningRobot.getHeading());
    turningRobot.executeTurn('L');
    Assert.assertEquals('E', turningRobot.getHeading());
    turningRobot.executeTurn('L');
    Assert.assertEquals('N', turningRobot.getHeading());
    turningRobot.executeTurn('R');
    Assert.assertEquals('E', turningRobot.getHeading());
    turningRobot.executeTurn('R');
    Assert.assertEquals('S', turningRobot.getHeading());
    turningRobot.executeTurn('R');
    Assert.assertEquals('W', turningRobot.getHeading());
    turningRobot.executeTurn('R');
    Assert.assertEquals('N', turningRobot.getHeading());

  }

  /**
   * Checks that execution of a move directive by a robot facing north results in an increment of it's y position.
   * Also checks that an instruction to move north when already at the boundary does not result in the robot exiting
   * the landing area.
   * @throws Exception
   */
  @Test
  public void testExecuteMoveNorth() throws Exception {
    Robot robot = new Robot(0, 0, 'N', 5, 5);
    Assert.assertEquals(0, robot.getxPos());
    Assert.assertEquals(0, robot.getyPos());
    Assert.assertEquals('N', robot.getHeading());

    robot.executeMove();

    Assert.assertEquals(0, robot.getxPos());
    Assert.assertEquals(1, robot.getyPos());
    Assert.assertEquals('N', robot.getHeading());

    // create new robot at top most boundary
    robot = new Robot(0, 5, 'N', 5, 5);
    Assert.assertEquals(0, robot.getxPos());
    Assert.assertEquals(5, robot.getyPos());
    Assert.assertEquals('N', robot.getHeading());

    robot.executeMove();

    Assert.assertEquals(0, robot.getxPos());
    Assert.assertEquals(5, robot.getyPos());
    Assert.assertEquals('N', robot.getHeading());
  }

  /**
   * Checks that execution of a move directive by a robot facing south results in a decrement of it's y position.
   * Also checks that an instruction to move south when already at the boundary does not result in the robot exiting
   * the landing area.
   * @throws Exception
   */
  @Test
  public void testExecuteMoveSouth() throws Exception {
    // Robot starts at position (0, 1) facing south
    Robot robot = new Robot(0, 1, 'S', 5, 5);
    Assert.assertEquals(0, robot.getxPos());
    Assert.assertEquals(1, robot.getyPos());
    Assert.assertEquals('S', robot.getHeading());

    robot.executeMove();

    // Robot should end at position (0, 0) facing south
    Assert.assertEquals(0, robot.getxPos());
    Assert.assertEquals(0, robot.getyPos());
    Assert.assertEquals('S', robot.getHeading());

    // create new robot at bottom most boundary
    robot = new Robot(0, 0, 'S', 5, 5);
    Assert.assertEquals(0, robot.getxPos());
    Assert.assertEquals(0, robot.getyPos());
    Assert.assertEquals('S', robot.getHeading());

    robot.executeMove();

    Assert.assertEquals(0, robot.getxPos());
    Assert.assertEquals(0, robot.getyPos());
    Assert.assertEquals('S', robot.getHeading());
  }

  /**
   * Checks that execution of a move directive by a robot facing east results in an increment of it's x position.
   * Also checks that an instruction to move east when already at the boundary does not result in the robot exiting
   * the landing area.
   * @throws Exception
   */
  @Test
  public void testExecuteMoveEast() throws Exception {
    Robot robot = new Robot(0, 0, 'E', 5, 5);
    Assert.assertEquals(0, robot.getxPos());
    Assert.assertEquals(0, robot.getyPos());
    Assert.assertEquals('E', robot.getHeading());

    robot.executeMove();

    Assert.assertEquals(1, robot.getxPos());
    Assert.assertEquals(0, robot.getyPos());
    Assert.assertEquals('E', robot.getHeading());

    //create new robot at rightmost boundary
    robot = new Robot(5, 0, 'E', 5, 5);
    Assert.assertEquals(5, robot.getxPos());
    Assert.assertEquals(0, robot.getyPos());
    Assert.assertEquals('E', robot.getHeading());

    robot.executeMove();

    Assert.assertEquals(5, robot.getxPos());
    Assert.assertEquals(0, robot.getyPos());
    Assert.assertEquals('E', robot.getHeading());
  }

  /**
   * Checks that execution of a move directive by a robot facing west results in a decrement of it's x position.
   * Also checks that an instruction to move west when already at the boundary does not result in the robot exiting
   * the landing area.
   * @throws Exception
   */
  @Test
  public void testExecuteMoveWest() throws Exception {
    Robot robot = new Robot(1, 0, 'W', 5, 5);
    Assert.assertEquals(1, robot.getxPos());
    Assert.assertEquals(0, robot.getyPos());
    Assert.assertEquals('W', robot.getHeading());

    robot.executeMove();

    Assert.assertEquals(0, robot.getxPos());
    Assert.assertEquals(0, robot.getyPos());
    Assert.assertEquals('W', robot.getHeading());

    // create new robot at left most boundary
    robot = new Robot(0, 0, 'W', 5, 5);
    Assert.assertEquals(0, robot.getxPos());
    Assert.assertEquals(0, robot.getyPos());
    Assert.assertEquals('W', robot.getHeading());

    robot.executeMove();

    Assert.assertEquals(0, robot.getxPos());
    Assert.assertEquals(0, robot.getyPos());
    Assert.assertEquals('W', robot.getHeading());
  }

  /**
   * Checks that the Robot's toString method prints as expected.
   * @throws Exception
   */
  @Test
  public void testToString() throws Exception {

    Assert.assertEquals("1 2 N", robot1.toString());
    Assert.assertEquals("3 3 E", robot2.toString());

    Assert.assertTrue(robot1.executeInstructions());
    Assert.assertTrue(robot2.executeInstructions());

    Assert.assertEquals("1 3 N", robot1.toString());
    Assert.assertEquals("5 1 E", robot2.toString());

  }
}