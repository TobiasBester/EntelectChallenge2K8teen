import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Future;

public class Map {
  private int worker_count = 0;
  private int miner_count = 0;
  private int excavator_count = 0;
  private int hauler_count = 0;
  private int numRows = 0;
  private int numCols = 0;
  private int numMines = 0;
  private int numFactories = 0;
  private int budget = 0;
  public ArrayList<Mine> mines = new ArrayList<Mine>();
  public ArrayList<Factory> factories = new ArrayList<Factory>();
  public ArrayList<Worker> miners = new ArrayList<Worker>();
  public ArrayList<Worker> excavators = new ArrayList<Worker>();
  public ArrayList<Worker> haulers = new ArrayList<Worker>();

  Map(int rows, int cols, int numMiners, int numExcs, int numHaulers, int numMines, int numFacs, int budget) {
    this.numRows = rows;
    this.numCols = cols;
    this.miner_count = numMiners;
    this.excavator_count = numExcs;
    this.hauler_count = numHaulers;
    this.numMines = numMines;
    this.numFactories = numFacs;
    this.budget = budget;

    createWorkers();
    // lookForResources();
    // matchMinesWithDepots();
    // Object[] shortestDist = getShortestDist();
    // System.out.println("The closest worker is: " + shortestDist[0]);
    // System.out.println("Is the thing a mine? " + shortestDist[2]);
    // System.out.println("The closest thing is: " + shortestDist[1]);
  }

  public void addMine(Mine mine) {
    mines.add(mine);
  }

  public void addFactory(Factory factory) {
    factories.add(factory);
  }

  private void createWorkers() {
    System.out.println("Creating Workers");
    for (int i = 0; i < miner_count; i++) {
      Worker newMiner = new Worker(0, 0, 1, 0, this);
      miners.add(newMiner);
    }
    ]for (int i = 0; i < excavator_count; i++) {
      Worker newExc = new Worker(0, 0, 3, 1, this);
      excavators.add(newExc);
    }
    for (int i = 0; i < hauler_count; i++) {
      Worker newHauler = new Worker(0, 0, 5, 2, this);
      haulers.add(newHauler);
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

}
