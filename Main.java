import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String [] args) throws FileNotFoundException {
        // Scanner to read in user input
        Scanner readUserInput = new Scanner(System.in);
        System.out.println("Please enter the flight data file name and requested flight data file name (ex: FlightData.txt RequestedFlightData.txt): " );

        String flightDataFileName = readUserInput.next();
        String requestedFlightDataFileName = readUserInput.next();

        // this passes the user input files for the graph to be implemented and the paths shown
        ReadFile.readFlightData(flightDataFileName, requestedFlightDataFileName);
    }
}