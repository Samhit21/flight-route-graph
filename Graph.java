import java.util.*;

public class Graph {
    // global variable for array list of linked list and the vertices
    ArrayList<stringFlights> paths = new ArrayList<stringFlights>();
    int vertex;
    LinkedList<Integer> list[];

    // constructor with vertex
    public Graph(int vertex) {
        this.vertex = vertex;

        // array list of linked list adjacency list created
        // number of vertices = number of array list contents
        list = new LinkedList[vertex];
        for (int i = 0; i < vertex; i++) {
            list[i] = new LinkedList<>();
        }
    }

    // add edge to add linked list to each element of array list
    // it is added to the last each time
    // added both to origin and destination since undirected
    public void addEdge(int source, int destination) {
        //add from source to destination
        list[source].addLast(destination);

        //add edge from destination to source
        list[destination].addLast(source);
    }

    // debugging code, method accessed to check adjacency list created correctly
    public void printGraph(ArrayList<String> adjacencyLists) {
        for (int i = 0; i < vertex; i++) {
            if (list[i].size() > 0) {
                System.out.print("Vertex " + adjacencyLists.get(i) + " is connected to: ");
                for (int j = 0; j < list[i].size(); j++) {
                    System.out.print(adjacencyLists.get(list[i].get(j)) + " ");
                }
                System.out.println();
            }
        }
    }

    // prints all paths from origin to destination as requested
    public void printAllPaths(ArrayList<String> adjacencyLists, ArrayList<Path> edges,
                              int tempSortInt, int origin, int destination) {

        // array to check if a city has already been visited
        boolean[] isVisited = new boolean[vertex];

        // Stack implemented for putting in each city and then accessing it
        Stack<Integer> pathList = new Stack<>();

        // starts with pushing the first city to the top of the stack
        pathList.push(origin);

        // Call recursive function to find paths
        printAllPathsUtil(adjacencyLists, edges, tempSortInt, origin, destination, isVisited, pathList);

        // if less than or equal to 3 paths, this print all paths, not dependent on cost or time
        if (paths.size() <= 3) {
            for (int j = 0; j < paths.size(); j++) {
                System.out.println(paths.get(j).path + ". Time : " + paths.get(j).time + ", Cost : " + paths.get(j).cost);
            }
        }

        // if there more than 3 path, this prints paths depending on cost or time as the user requests
        else {
            for (int j = 0; j < 3; j++) {
                System.out.println(paths.get(j).path + ". Time : " + paths.get(j).time + ", Cost : " + paths.get(j).cost);
            }
        }

        System.out.println("");
        // this clears the array list
        paths.clear();
    }


    // recursively prints all possible paths from origin to destination
    private void printAllPathsUtil(ArrayList<String> adjacencyLists, ArrayList<Path> edges,
                                                       int tempSortInt,
                                                       Integer origin, Integer destination,
                                                       boolean[] isVisited,
                                                       Stack<Integer> localPathList) {
        // recursion end
        if (origin.equals(destination)) {
            // initialize variables to calculate weights and the paths
            int cost = 0;
            int time = 0;
            String tempPath = "";

            // localPathList contains whole path so we access each path and concatenate it to make whole path
            for (int i = 0; i < localPathList.size(); i++) {
                if (i != 0) {
                    tempPath += " -> ";
                }
                tempPath += adjacencyLists.get(localPathList.get(i));

                // time and cost incremented as weight increases as we take more cities
                for (int j = 0; j < edges.size(); j++) {
                    if ((edges.get(j).origin == localPathList.get(i) && i + 1 < localPathList.size() && edges.get(j).destination == localPathList.get(i + 1))
                            | ((edges.get(j).destination == localPathList.get(i) && i + 1 < localPathList.size() && edges.get(j).origin == localPathList.get(i + 1)))) {
                        time += edges.get(j).time;
                        cost += edges.get(j).cost;
                    }
                }
            }

            // we now need to sort each path according to time or cost as requested
            // StringFlights wrapper class for the path and the weights
            stringFlights tempFlightPath = new stringFlights(tempPath, time, cost);
            paths.add(tempFlightPath);

            // no need to sort if user not requested
            if (tempSortInt == -1) {
                return;
            }

            // if sorting by time requested, override method used
            if (tempSortInt == 0) {
                Collections.sort(paths, new stringFlights.stringFlightsTimeComparator());
                return;
            }

            // if sorting by cost requested, override method used
            if (tempSortInt == 1) {
                Collections.sort(paths, new stringFlights.stringFlightsCostComparator());
                return;
            }

            // return if end found;
            return;
        }

        // visited node marked to avoid visiting again
        isVisited[origin] = true;

        // Stack implementation, first checked if city is visited with isVisited
        for (Integer i : list[origin]) {
            if (!isVisited[i]) {
                // current city pushed to the top of the stack
                localPathList.push(i);
                // recursively called again to find paths
                printAllPathsUtil(adjacencyLists, edges, tempSortInt, i, destination, isVisited, localPathList);
                // finally removed from stack
                localPathList.remove(i);
            }
        }
        // visit completed so marked as false to start again
        isVisited[origin] = false;
        return;
    }
}

