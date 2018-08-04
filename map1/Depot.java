public class Depot {

  private int xLoc = 0;
  private int yLoc = 0;
  private Map theMap;
  private Character letter;
  public boolean receivedResource = false;
  private Mine matchingMine = null;

  Depot(Map theMap, int xLoc, int yLoc, Character letter) {
    // System.out.println("Found a depot at " + xLoc + "," + yLoc);
    this.theMap = theMap;
    this.letter = Character.toUpperCase(letter);
    this.xLoc = xLoc;
    this.yLoc = yLoc;
  }

  public void setMine(Mine matchingMine) {
    this.matchingMine = matchingMine;
  }

  public int getXloc() {
    return xLoc;
  }

  public int getYloc() {
    return yLoc;
  }

  public Character getLetter() {
    return letter;
  }

}
