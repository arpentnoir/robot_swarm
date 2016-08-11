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

  Robot turningRobot;

  LinkedList instructionList1;
  LinkedList instructionList2;

  @Before
  public void setUp(){

    // Example test setup
    instructionList1 = RobotUtils.createInstructionList("LMLMLMLMM");
    robot1 = new Robot(1, 2, 'N', instructionList1);

    instructionList2 = RobotUtils.createInstructionList("MMRMMRMRRM");
    robot2 = new Robot(3, 3, 'E', instructionList2);

    Assert.assertEquals(1, robot1.getxPos());
    Assert.assertEquals(2, robot1.getyPos());
    Assert.assertEquals('N', robot1.getHeading());

    Assert.assertEquals(3, robot2.getxPos());
    Assert.assertEquals(3, robot2.getyPos());
    Assert.assertEquals('E', robot2.getHeading());

    //
    turningRobot = new Robot(0, 0, 'N');
    Assert.assertEquals('N', turningRobot.getHeading());

  }

  @After
  public void tearDown(){
    instructionList1 = null;
    instructionList2 = null;

    robot1 = null;
    robot2 = null;
  }

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

  @Test
  public void testExecuteTurn() throws Exception {
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

  @Test
  public void testExecuteMoveNorth() throws Exception {
    Robot robot = new Robot(0, 0, 'N');
    Assert.assertEquals(0, robot.getxPos());
    Assert.assertEquals(0, robot.getyPos());
    Assert.assertEquals('N', robot.getHeading());

    robot.executeMove();

    Assert.assertEquals(0, robot.getxPos());
    Assert.assertEquals(1, robot.getyPos());
    Assert.assertEquals('N', robot.getHeading());
  }

  @Test
  public void testExecuteMoveSouth() throws Exception {
    // Robot starts at position (0, 1) facing south
    Robot robot = new Robot(0, 1, 'S');
    Assert.assertEquals(0, robot.getxPos());
    Assert.assertEquals(1, robot.getyPos());
    Assert.assertEquals('S', robot.getHeading());

    robot.executeMove();

    // Robot should end at position (0, 0) facing south
    Assert.assertEquals(0, robot.getxPos());
    Assert.assertEquals(0, robot.getyPos());
    Assert.assertEquals('S', robot.getHeading());
  }

  @Test
  public void testExecuteMoveEast() throws Exception {
    Robot robot = new Robot(0, 0, 'E');
    Assert.assertEquals(0, robot.getxPos());
    Assert.assertEquals(0, robot.getyPos());
    Assert.assertEquals('E', robot.getHeading());

    robot.executeMove();

    Assert.assertEquals(1, robot.getxPos());
    Assert.assertEquals(0, robot.getyPos());
    Assert.assertEquals('E', robot.getHeading());
  }

  @Test
  public void testExecuteMoveWest() throws Exception {
    Robot robot = new Robot(1, 0, 'W');
    Assert.assertEquals(1, robot.getxPos());
    Assert.assertEquals(0, robot.getyPos());
    Assert.assertEquals('W', robot.getHeading());

    robot.executeMove();

    Assert.assertEquals(0, robot.getxPos());
    Assert.assertEquals(0, robot.getyPos());
    Assert.assertEquals('W', robot.getHeading());
  }

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