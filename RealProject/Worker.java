public class Worker {

  private int xLoc = 0;
  private int yLoc = 0;
  private int carryLoad = 0;
  private int type = 0;
  public Boolean hasResource = false;
  public Boolean hasDeposited = false;
  private Map theMap;
  public Mine currentMine = null;
  private int heuristic;
  public int currentLoad = 0;

  Worker(int x, int y,int carryLoad,int type, Map theMap) {
    this.xLoc = x;
    this.yLoc = y;
    this.theMap = theMap;
    this.carryLoad = carryLoad;
    this.type = type;
  }

/*
  public void calculateHeuristic() {
    // The heuristic is the distance to mine if not carrying a resource
    // Else it is the distance to the depot
    if (hasResource == false) {
      // If this is the case, which mine should be considered? The nearest one?
      currentMine = getClosestMine();
      System.out.println("Current mine: " + currentMine.getLetter());
    } else {

    }
  }

  private Mine getClosestMine() {
    double closestDist = 10000000.0;
    Mine closestMine = null;
    for (Mine mine: theMap.mines) {
      double dist = theMap.getDistFromWorkerToMine(this, mine);
      if (dist < closestDist) {
        closestDist = dist;
        closestMine = mine;
      }
    }
    return closestMine;
  }*/

  public int getXloc() {
    return xLoc;
  }

  public int getYloc() {
    return yLoc;
  }

}
