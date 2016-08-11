package tests;

import org.junit.Assert;
import org.junit.Test;
import utils.RobotUtils;

/**
 * Created by richardspellman on 11/08/2016.
 */
public class RobotUtilsTest {
  @Test
  public void testCreateInstructionList() throws Exception {

  }

  @Test
  public void testGetNextDirection() throws Exception {

    Assert.assertEquals('W', RobotUtils.getNextDirection('N', 'L'));
    Assert.assertEquals('E', RobotUtils.getNextDirection('S', 'L'));
    Assert.assertEquals('N', RobotUtils.getNextDirection('E', 'L'));
    Assert.assertEquals('S', RobotUtils.getNextDirection('W', 'L'));

    Assert.assertEquals('E', RobotUtils.getNextDirection('N', 'R'));
    Assert.assertEquals('W', RobotUtils.getNextDirection('S', 'R'));
    Assert.assertEquals('S', RobotUtils.getNextDirection('E', 'R'));
    Assert.assertEquals('N', RobotUtils.getNextDirection('W', 'R'));
  }

  @Test
  public void testPrintRobotPositions() throws Exception {

  }
}
