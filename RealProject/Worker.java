import java.util.ArrayList;

public class Worker {

  private int index = 0;
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
  public ArrayList<String> resourcesHeld = new ArrayList<String>();
  public ArrayList<Integer> history = new ArrayList<Integer>();

  Worker(int x, int y,int carryLoad,int type, int index, Map theMap) {
    this.xLoc = x;
    this.yLoc = y;
    this.theMap = theMap;
    this.carryLoad = carryLoad;
    this.type = type;
    this.index = index;
  }

  public int getType(){
   return type;
  }

  public int getIndex() {
    return index;
  }

  public int getCarryLoad(){
   return carryLoad;
  }

  public Boolean hasSpace()
  {
   return carryLoad > currentLoad;
  }

  private void pickUpResource(String letter) {
    resourcesHeld.add(letter);
  }

  public void goToMine() {
    if (theMap.mines.size() == 0) {
      return;
    }

    Mine currentMine = theMap.mines.get(0);

    double currShortest = 1000000000;
    for (Mine mine: theMap.mines) {
      double dist = theMap.getDistFromWorkerToMine(this, mine);
      if (dist < currShortest) {
        currShortest = dist;
        currentMine = mine;
      }
    }

    xLoc = currentMine.getXloc();
    yLoc = currentMine.getYloc();
    this.history.add(currentMine.getIndex());
    currentLoad++;
    theMap.increaseCost(theMap.getDistFromWorkerToMine(this, currentMine));
    pickUpResource(currentMine.getLetter());
    currentMine.removeResources();
  };

  public int getXloc() {
    return xLoc;
  }

  public int getYloc() {
    return yLoc;
  }

  public void goToFactory(Factory factory) {
    theMap.increaseCost(theMap.getDistFromWorkerToFactory(this, factory));
    this.xLoc = factory.getXloc();
    this.yLoc = factory.getYloc();
    this.history.add(factory.getIndex());
    this.currentLoad--;
    this.resourcesHeld.remove(factory.getLetter().toLowerCase());
  }

  public String getStringType(){
    if(this.type == 0) {
      return "M";
    } else if (this.type == 1){
      return "E";
    } else {
      return "H";
    }
  }

}
