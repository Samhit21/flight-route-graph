import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class ReadFile {
    public static void readFlightData(String flightDataFileName, String requestedPlansFileName) throws FileNotFoundException {
        // file opened and scanner class initialized to read file
        File originDestination = new File(flightDataFileName);
        Scanner readFile = new Scanner(originDestination);

        // Two array list, one for storing edges and the other to implement the adjacency list with an arraylist of linked lists
        ArrayList<String> adjacencyLists = new ArrayList<String>();
        ArrayList<Path> edges = new ArrayList<Path>();

        // first line read in, although not used in my implementation of the program
        int rowsDataFile1 = Integer.parseInt(readFile.nextLine());

        // initialized to zero
        int adjacencyListsIndex = 0;

        // each line read in and then a new Scanner class is called that reads each line word by word separated by delimiters
        while (readFile.hasNextLine()) {
            String tempLine = readFile.nextLine();
            Scanner readLine = new Scanner(tempLine);
            readLine.useDelimiter("\\|");

            String tempOrigin = readLine.next();
            String tempDestination = readLine.next();
            int tempCost = Integer.parseInt(readLine.next());
            int tempTime = Integer.parseInt(readLine.next());

            // First we add each city to the array list, it is important that we do not have multiple cities so we do a validation
            // next we need to read in the edges so that we can implement it later on doing our arraylist of linked lists
            int indexOrigin;
            int indexDestination;
            if (!(adjacencyLists.contains(tempOrigin))) {
                adjacencyLists.add(tempOrigin);
                indexOrigin = adjacencyLists.indexOf(tempOrigin);
            } else {
                indexOrigin = adjacencyLists.indexOf(tempOrigin);
            }

            if (!(adjacencyLists.contains(tempDestination))) {
                adjacencyLists.add(tempDestination);
                indexDestination = adjacencyLists.indexOf(tempDestination);
            } else {
                indexDestination = adjacencyLists.indexOf(tempDestination);
            }

            // temporary edge made with weighted cost and time, and the origin and destination
            // undirected so works both ways.
            // edges then added to our linked list to be used later on
            // OriginDestination is the wrapper class and contains the weights and origin destination
            Path tempEdge = new Path(indexOrigin, indexDestination, tempCost, tempTime);
            edges.add(tempEdge);
        }

        // initialize graph with adjacencyLists.size() indicating number of vertices
        Graph graph = new Graph(adjacencyLists.size());

        // now it is time to use our edges and make the linked list of each individual arraylist element
        // add edge called, again since undirected origin-destination is equal to destination-origin
        // for every edge, we go through the array list and add the linked list to array list
        for (int i = 0; i < edges.size(); i++) {
            graph.addEdge(edges.get(i).origin, edges.get(i).destination);
        }

        // debugging code to make sure each vertext is connected to the correct vertex in the adjacency list
        // graph.printGraph(adjacencyLists);

        // now we will read in the requested flight plans to output in the command line the best flight paths
        File requestedFlights = new File(requestedPlansFileName);
        Scanner readFile2 = new Scanner(requestedFlights);

        // first line read in, although not used in my implementation of the program
        int rowsDataFile2 = Integer.parseInt(readFile2.nextLine());

        // number of requested flights, initialized to 0 and then incremeneted as each path is read
        int numberPaths = 0;

        // each line read in and then a new Scanner class is called
        // that scanner class reads each line word by word separated by delimiters
        while (readFile2.hasNextLine()) {
            String tempLine = readFile2.nextLine();
            Scanner readLine = new Scanner(tempLine);
            readLine.useDelimiter("\\|");

            String tempOrigin = readLine.next();
            // System.out.println(tempOrigin);
            String tempDestination = readLine.next();
            // System.out.println(tempDestination);
            String tempSort = readLine.next();
            numberPaths++;

            // the variable that tells which variable needs to be sorted by
            if (tempSort.equals("T"))
                tempSort = "Time";
            if (tempSort.equals("C"))
                tempSort = "Cost";

            // display title
            System.out.printf("Flight %d: %s, %s (%s)\n", numberPaths, tempOrigin, tempDestination, tempSort);

            // origin and destination indices are -1 to find flights that may not exist
            int originIndex = -1;
            int destinationIndex = -1;


            // in my implementation, index of the cities is inside the arraylist of linked list
            // so we find the indices and we ill pass it later
            for (int i = 0; i < adjacencyLists.size(); i++) {
                if ((tempOrigin).equals(adjacencyLists.get(i))) {
                    originIndex = i;
                }
                if ((tempDestination).equals(adjacencyLists.get(i))) {
                    destinationIndex = i;
                }
            }
            // System.out.println(originIndex + " " + destinationIndex);  // debugging

            // No flight possible finding
            if (originIndex == -1 | destinationIndex == -1) {
                System.out.println("No flights possible");
                System.out.println("");
                continue;
            }

            // integer representaton of the variable it needs to be sorted by
            int tempSortInt = -1;
            if (tempSort.equals("Time"))
                tempSortInt = 0;
            if (tempSort.equals("Cost"))
                tempSortInt = 1;

            // printing all graph paths requested
            graph.printAllPaths(adjacencyLists, edges, tempSortInt, originIndex, destinationIndex);
        }
    }
}