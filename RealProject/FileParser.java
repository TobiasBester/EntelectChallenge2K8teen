import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.text.ParseException;

public class FileParser {
    // public static ArrayList<Employee> read(String fileName) throws FileNotFoundException {
    //     Scanner scanner = new Scanner(new File(fileName));
    //     scanner.useDelimiter(",");
    //     String[] tokensVal;
    //     ArrayList<Employee> returnEmployees = new ArrayList<Employee>();
    //     while(scanner.hasNextLine()) {
    //         tokensVal = scanner.nextLine().split(",");
    //         try {
    //             returnEmployees.add(new Employee(tokensVal[0], tokensVal[1], tokensVal[2], tokensVal[3], tokensVal[4], tokensVal[5], tokensVal[6]));
    //         } catch (ParseException e) {
    //             System.out.println("Error: Could not parse values");
    //         }
            
            
    //     }
    //     scanner.close();

    //     // for (String item : tokensVal) {
    //     //     System.out.println(item);
    //     // }

    //     return new ArrayList<Employee>();
    // }

    public static Map read(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        scanner.useDelimiter(" ");
        String[] tokensVal;
        tokensVal = scanner.nextLine().split(" ");
        //Integer.parseInt(empID);
        Map output = new Map(Integer.parseInt(tokensVal[0]),    // R
                             Integer.parseInt(tokensVal[1]),    // C
                             Integer.parseInt(tokensVal[2]),    // M
                             Integer.parseInt(tokensVal[3]),    // E
                             Integer.parseInt(tokensVal[4]),    // H
                             Integer.parseInt(tokensVal[5]),    // MN
                             Integer.parseInt(tokensVal[6]),    // F
                             Integer.parseInt(tokensVal[7]));   // Budget
        int numMine = Integer.parseInt(tokensVal[5]);
        int numFact = Integer.parseInt(tokensVal[6]);
        for(int x = 0; x < numMine; x++) {
            tokensVal = scanner.nextLine().split(" ");
            Mine mine = new Mine(Integer.parseInt(tokensVal[0]),    // I
                                 Integer.parseInt(tokensVal[1]),    // T
                                 Integer.parseInt(tokensVal[2]),    // x
                                 Integer.parseInt(tokensVal[3]),    // y
                                 Integer.parseInt(tokensVal[4]));   // R
        }
        for(int x = 0; x < numFact; x++) {
            tokensVal = scanner.nextLine().split(" ");
            Factory factory = new Factory(Integer.parseInt(tokensVal[0]),    // I
                                          Integer.parseInt(tokensVal[1]),    // T
                                          Integer.parseInt(tokensVal[2]),    // x
                                          Integer.parseInt(tokensVal[3]));   // y
        }

        


    }
 }