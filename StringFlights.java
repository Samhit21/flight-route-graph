
import java.util.Comparator;

public class stringFlights {
    String path;
    int time;
    int cost;
    int tempSortInt;

    public stringFlights(String path, int time, int cost) {
        this.path = path;
        this.time = time;
        this.cost = cost;
    }

    // this compares the time of the flights
    public static class stringFlightsTimeComparator implements Comparator<stringFlights> {
        @Override
        public int compare(stringFlights time1, stringFlights time2) {
            return time1.time - time2.time;
        }
    }

    // this compares the cost of the flights
    public static class stringFlightsCostComparator implements Comparator<stringFlights> {
        @Override
        public int compare(stringFlights cost1, stringFlights cost2) {
            return cost1.cost - cost2.cost;
        }
    }
}

