# Flight Route Graph

A Java graph project that finds possible flight routes between cities and ranks them by total cost or total travel time.

The program reads flight data from text files, builds a weighted graph using an adjacency list, searches for valid routes between two cities, calculates the total cost and travel time for each path, and returns up to the three best options based on the requested sort criteria.

## Features

- Represents cities as graph vertices
- Represents flights as weighted edges
- Uses an adjacency-list graph structure
- Finds valid routes with recursive depth-first search
- Prevents cycles with visited-node tracking
- Calculates total route cost and travel time
- Sorts routes by cost or time
- Reads flight data and requested routes from text files

## Algorithms and Data Structures

- **Adjacency list** for graph representation
- **Depth-first search (DFS)** for route enumeration
- **Visited-node tracking** to avoid cycles
- **Weighted path calculations** for total cost and travel time
- **Comparator-based sorting** to rank the best routes

## Project Structure

```text
flight-route-graph/
├── Graph.java
├── Main.java
├── Path.java
├── ReadFile.java
├── StringFlights.java
├── FlightData.txt
├── RequestedFlightPlans.txt
├── DATA_FORMAT.md
├── README.md
└── .gitignore
```

## How It Works

The application first reads the available flights and builds an adjacency list. Each flight stores two weights:

- Cost
- Travel time

For each requested route, the program performs a recursive DFS to find every valid path between the requested cities. A visited-node structure is used to avoid cycles while exploring the graph.

After each route is found, the program calculates its total cost and total travel time. The completed routes are then sorted using Java comparators based on the requested criterion.

If more than three valid routes are available, only the three best routes are displayed.

## Requirements

- Java 8 or newer

## Compile

From the project directory:

```bash
javac *.java
```

## Run

```bash
java Main
```

When prompted, enter the flight-data file and requested-flight-plan file:

```text
FlightData.txt RequestedFlightPlans.txt
```

## Example Input

`FlightData.txt`

```text
4
Dallas|Austin|98|47
Austin|Houston|95|39
Dallas|Houston|101|51
Austin|Chicago|144|192
```

Each flight uses:

```text
Origin|Destination|Cost|Time
```

`RequestedFlightPlans.txt`

```text
2
Dallas|Houston|T
Chicago|Dallas|C
```

Each request uses:

```text
Origin|Destination|Sort
```

where:

- `T` sorts by travel time
- `C` sorts by cost

## Example Output

```text
Flight 1: Dallas, Houston (Time)
Dallas -> Houston. Time : 51, Cost : 101
Dallas -> Austin -> Houston. Time : 86, Cost : 193

Flight 2: Chicago, Dallas (Cost)
Chicago -> Austin -> Dallas. Time : 239, Cost : 242
Chicago -> Austin -> Houston -> Dallas. Time : 282, Cost : 340
```

## Main Components

### `Graph.java`
Stores the adjacency list, performs recursive route search, calculates path weights, and ranks the results.

### `ReadFile.java`
Parses the flight-data and requested-route files and builds the graph.

### `Path.java`
Stores an edge's origin, destination, cost, and travel time.

### `StringFlights.java`
Stores completed routes and provides comparators for sorting by time or cost.

### `Main.java`
Provides the command-line entry point and accepts the input filenames.

## What This Project Demonstrates

- Graph data structures
- Depth-first search
- Recursion
- Weighted graphs
- Java collections
- Comparators and sorting
- File parsing
- Object-oriented programming

## Possible Improvements

- Use Dijkstra's algorithm for shortest-path queries on larger graphs
- Add automated tests
- Add input validation for malformed files
- Refactor class naming to follow standard Java conventions
- Use a map for faster city lookup
