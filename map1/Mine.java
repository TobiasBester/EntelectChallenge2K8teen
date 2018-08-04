public class Mine {

  public int xLoc = 0;
  public int yLoc = 0;
  private Map theMap;
  private Character letter;
  public boolean pickedUp = false;
  private Depot matchingDepot = null;

  Mine(Map theMap, int xLoc, int yLoc, Character letter) {
    // System.out.println("Found a mine at " + xLoc + "," + yLoc);
    this.theMap = theMap;
    this.letter = letter;
    this.xLoc = xLoc;
    this.yLoc = yLoc;
  }

  public void setDepot(Depot matchingDepot) {
    this.matchingDepot = matchingDepot;
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
