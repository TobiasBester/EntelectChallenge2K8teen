public class Factory {
  
  private int index =0;
  private int xLoc = 0;
  private int yLoc = 0;
  private Map theMap;
  private String letter;
  public boolean receivedResource = false;
  private Mine matchingMine = null;

  Factory(int index, String letter ,int xLoc, int yLoc, Map theMap) {
    // System.out.println("Found a depot at " + xLoc + "," + yLoc);
    this.theMap = theMap;
    this.letter = letter.toUpperCase();
    this.xLoc = xLoc;
    this.yLoc = yLoc;
    this.index = index;
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

  public String getLetter() {
    return letter;
  }

}
