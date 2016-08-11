package utils;

/**
 * Created by richardspellman on 11/08/2016.
 */
public class DirectionList {


  private static DirectionList directionList = new DirectionList();

  private DirectionList(){}

  public static DirectionList getInstance(){
    return directionList;
  }


}

