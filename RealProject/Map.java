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
  private double budget = 0;
  public double moneyUsed = 0;
  public ArrayList<Mine> mines = new ArrayList<Mine>();
  public ArrayList<Factory> factories = new ArrayList<Factory>();
  public ArrayList<Worker> workers = new ArrayList<Worker>();
  public ArrayList<Worker> miners = new ArrayList<Worker>();
  public ArrayList<Worker> excavators = new ArrayList<Worker>();
  public ArrayList<Worker> haulers = new ArrayList<Worker>();

  Map(int rows, int cols, int numMiners, int numExcs, int numHaulers, int numMines, int numFacs, double budget) {
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
    int index = 0;
    // System.out.println("Creating Workers");
    for (int i = 0; i < miner_count; i++) {
      Worker newMiner = new Worker(0, 0, 1, 0, index++, this);
      workers.add(newMiner);
      miners.add(newMiner);
    }
    for (int i = 0; i < excavator_count; i++) {
      Worker newExc = new Worker(0, 0, 3, 1, index++, this);
      workers.add(newExc);
      excavators.add(newExc);
    }
    for (int i = 0; i < hauler_count; i++) {
      Worker newHauler = new Worker(0, 0, 5, 2, index++, this);
      workers.add(newHauler);
      haulers.add(newHauler);
    }
  }

  public void startWorking() {
    // System.out.println("Started Working");
    while (mines.size() > 0) {
      for (Worker worker: workers) {
        if (worker.hasSpace()) {
          // System.out.println("Worker " + worker.getIndex() + " is heading to a mine");
          worker.goToMine();
        } else {

          for (String resource: worker.resourcesHeld) {
            // System.out.println("Worker " + worker.getIndex() + " is heading to a factory");
            //GOTO FACTORY
            String factory = resource;
            Factory goToFactory = null;
            for (Factory nextFact: this.factories){
              if(nextFact.getLetter().equals(factory)){
                goToFactory = nextFact;
                break;
              }
            }
            // System.out.println("Going to factory: " + goToFactory.getIndex());
            worker.goToFactory(goToFactory);
          }
        }
      }
    }
    // System.out.println("Stopped Working");
    System.out.print(printOutput());
  }

  public String printOutput() {
    String outString = "";
    for (Worker worker: workers) {
      outString += worker.getStringType() + "|";
      for (Integer index: worker.history) {
        outString += index + ",";
      }
      outString = outString.substring(0,outString.length()-1);
      outString += "\n";
    }
    return outString;
  }
  
  /*
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
          //depots.add(newDepot);
        }
      }
    }
  } */

 //  private void matchMinesWithDepots() {
//     System.out.println("Matching Mines with Depots");
//     for (Mine mine: mines) {
//       for (Depot depot: depots) {
//         if ( mine.getLetter().equals(depot.getLetter()) ) {
//           mine.setDepot(depot);
//           depot.setMine(mine);
//         }
//       }
//     }
//   }

  /*
  public void startWorkers() {
    for (int i = 0; i < worker_count; i++ ) {
      workers.get(i).StartWorking();
    }
  } */

  // public Object[] getShortestDist() {
//     double closestDist = 100000.0;
//     Object closestThing = null;
//     boolean thingIsMine = true;
//     Worker currentWorker = null;
//     Object[] result = new Object[3];
//     for (Worker worker: workers) {
//       for (Mine mine: mines) {
//         double dist = getDistFromWorkerToMine(worker, mine);
//         if (dist < closestDist) {
//           thingIsMine = true;
//           closestDist = dist;
//           closestThing = mine;
//           currentWorker = worker;
//         }
//       }
//       if (worker.hasResource) {
//         for (Depot depot: depots) {
//           double dist = getDistFromWorkerToDepot(worker, depot);
//           if (dist < closestDist) {
//             thingIsMine = false;
//             closestDist = dist;
//             closestThing = depot;
//             currentWorker = worker;
//           }
//         }
//       }
//     }
//     result[0] = currentWorker;
//     result[1] = closestThing;
//     result[2] = thingIsMine;
//     return result;
//   } */

  public double getDistFromWorkerToMine(Worker point1, Mine point2) {
    double result = 0.0;
    int x1 = point1.getXloc();
    int x2 = point2.getXloc();
    int y1 = point1.getYloc();
    int y2 = point2.getYloc();
    result = Math.abs(y2 - y1)  + Math.abs(x2 - x1);
    return result;
  }

  public double getDistFromWorkerToFactory(Worker point1, Factory point2) {
    double result = 0.0;
    int x1 = point1.getXloc();
    int x2 = point2.getXloc();
    int y1 = point1.getYloc();
    int y2 = point2.getYloc();
    result = Math.abs(y2 - y1) + Math.abs(x2 - x1);
    // System.out.println("Distance: " + result);
    return result;
  }

  public void increaseCost(double numCost) {
    if (validMove(numCost)) {
        moneyUsed += numCost;
    } else {
      System.out.println("NO MONEY BUDDY");
    }
  }

  public Boolean validMove(double numCost){
   double tmp =0;
   tmp = moneyUsed;
   Boolean flag = true;

   if((tmp + numCost)> budget)
   {
      return false;
   }
   else
      return true;

  }

}
