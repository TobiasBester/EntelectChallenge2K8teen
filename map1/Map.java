import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Future;

public class Map {
  private Character[][] theMap;
  private int worker_count = 0;
  private int numRows = 0;
  private int numCols = 0;
  public ArrayList<Worker> workers = new ArrayList<Worker>();
  public ArrayList<Mine> mines = new ArrayList<Mine>();
  public ArrayList<Depot> depots = new ArrayList<Depot>();

  Map(ArrayList<String> mapArray, int worker_count) {
    this.numRows = mapArray.size();
    this.numCols = mapArray.get(0).length();
    theMap = new Character[this.numRows][this.numCols];
    for (int i=0; i < this.numRows; i++) {
      for (int j=0; j < this.numCols; j++) {
        theMap[i][j] = mapArray.get(i).charAt(j);
      }
    }
    this.worker_count = worker_count;

    createWorkers();
    lookForResources();
    matchMinesWithDepots();
    Object[] shortestDist = getShortestDist();
    System.out.println("The closest worker is: " + shortestDist[0]);
    System.out.println("Is the thing a mine? " + shortestDist[2]);
    System.out.println("The closest thing is: " + shortestDist[1]);
  }

  private void createWorkers() {
    System.out.println("Creating Workers");
    for (int i = 0; i < worker_count; i++) {
      Worker newWorker = new Worker(numRows-1, 0, this);
      workers.add(newWorker);
    }
  }

  private void lookForResources() {
    System.out.println("Looking for mines and depots");
    for (int i = 0; i < this.numRows; i++) {
      for (int j = 0; j < this.numCols; j++) {
        if (Character.isUpperCase(theMap[i][j])) {
          Mine newMine = new Mine(this, i, j, theMap[i][j]);
          mines.add(newMine);
        }
        if (Character.isLowerCase(theMap[i][j])) {
          Depot newDepot = new Depot(this, i, j, theMap[i][j]);
          depots.add(newDepot);
        }
      }
    }
  }

  private void matchMinesWithDepots() {
    System.out.println("Matching Mines with Depots");
    for (Mine mine: mines) {
      for (Depot depot: depots) {
        if ( mine.getLetter().equals(depot.getLetter()) ) {
          mine.setDepot(depot);
          depot.setMine(mine);
        }
      }
    }
  }

  public void startWorkers() {
    for (int i = 0; i < worker_count; i++ ) {
      workers.get(i).StartWorking();
    }
  }

  public Object[] getShortestDist() {
    double closestDist = 100000.0;
    Object closestThing = null;
    boolean thingIsMine = true;
    Worker currentWorker = null;
    Object[] result = new Object[3];
    for (Worker worker: workers) {
      for (Mine mine: mines) {
        double dist = getDistFromWorkerToMine(worker, mine);
        if (dist < closestDist) {
          thingIsMine = true;
          closestDist = dist;
          closestThing = mine;
          currentWorker = worker;
        }
      }
      if (worker.hasResource) {
        for (Depot depot: depots) {
          double dist = getDistFromWorkerToDepot(worker, depot);
          if (dist < closestDist) {
            thingIsMine = false;
            closestDist = dist;
            closestThing = depot;
            currentWorker = worker;
          }
        }
      }
    }
    result[0] = currentWorker;
    result[1] = closestThing;
    result[2] = thingIsMine;
    return result;
  }

  public double getDistFromWorkerToMine(Worker point1, Mine point2) {
    double result = 0.0;
    int x1 = point1.getXloc();
    int x2 = point2.getXloc();
    int y1 = point1.getYloc();
    int y2 = point2.getYloc();
    result = Math.sqrt( Math.pow( y2 - y1 , 2 ) + Math.pow( x2 - x1 , 2 )  );
    return result;
  }

  public double getDistFromWorkerToDepot(Worker point1, Depot point2) {
    double result = 0.0;
    int x1 = point1.getXloc();
    int x2 = point2.getXloc();
    int y1 = point1.getYloc();
    int y2 = point2.getYloc();
    result = Math.sqrt( Math.pow( y2 - y1 , 2 )  - Math.pow( x2 - x1 , 2 )  );
    // System.out.println("Distance: " + result);
    return result;
  }

  public Character getMapCoord(int x, int y) {
    return this.theMap[x][y];
  }

  public int getNumRows() {
    return this.numRows;
  }

  public int getNumCols() {
    return this.numCols;
  }

  public int getWorkerCount() {
    return this.worker_count;
  }

  public void printMap() {
    for (int i = 0; i < numRows; i++) {
      String output = Arrays.toString(theMap[i]);
      System.out.println(output);
    }
  }

}
