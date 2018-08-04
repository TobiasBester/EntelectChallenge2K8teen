import java.io.*;
import java.io.IOException;
import java.util.ArrayList;

public class main {

  static int worker_count;

  public static void main(String[] args) {
      System.out.println("Hello there");
      Map theMap = new Map(readFileContents("./maps/map_1.input"), worker_count);
      System.out.println("Map read from file: " + theMap.getNumCols() + "x" + theMap.getNumRows());
      theMap.printMap();
      // theMap.startWorkers();
  }

  public static ArrayList<String> readFileContents(String fileName) {
    String line = null;
    ArrayList<String> inputMap = new ArrayList<String>();

    try {
      FileReader fr = new FileReader(fileName);
      BufferedReader br = new BufferedReader(fr);
      worker_count = Integer.parseInt(br.readLine());

      while ( (line = br.readLine()) != null) {
        inputMap.add(line);
      }

    } catch(IOException ex) {
        System.out.println("Error reading file: " + ex);
    }

    return inputMap;
  }

}
