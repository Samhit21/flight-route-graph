# Data Format

The program expects two pipe-delimited text files.

## Flight Data

The first line contains the number of flight records.

Each following line uses:

```text
Origin|Destination|Cost|Time
```

Example:

```text
4
Dallas|Austin|98|47
Austin|Houston|95|39
Dallas|Houston|101|51
Austin|Chicago|144|192
```

The graph treats flights as bidirectional.

## Requested Flight Plans

The first line contains the number of route requests.

Each following line uses:

```text
Origin|Destination|Sort
```

The sort field can be:

- `T` for travel time
- `C` for cost

Example:

```text
2
Dallas|Houston|T
Chicago|Dallas|C
```

## Running With the Included Files

After compiling:

```bash
java Main
```

Enter:

```text
FlightData.txt RequestedFlightPlans.txt
```
