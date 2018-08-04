public class Mine {

  private int index = 0;
  public int xLoc = 0;
  public int yLoc = 0;
  private Map theMap;
  private String letter;
  private int numResources = 0;

  Mine(int index, String letter, int xLoc, int yLoc, int numResources, Map theMap) {
    // System.out.println("Found a mine at " + xLoc + "," + yLoc);
    this.index = index;
    this.letter = letter;
    this.xLoc = xLoc;
    this.yLoc = yLoc;
    this.numResources = numResources;
    this.theMap = theMap;
  }
  
/*
  public void setDepot(Depot matchingDepot) {
    this.matchingDepot = matchingDepot;
  }

  public Character getLetter() {
    return letter;
  }*/

  public int getXloc() {
    return xLoc;
  }

  public int getYloc() {
    return yLoc;
  }

}
