//this is a wrapper class for the edges
class Path {
    int origin;
    int destination;
    int cost;
    int time;
    
    public Path (int origin, int destination, int cost, int time) {
       this.origin = origin;
       this.destination = destination;
       this.cost = cost;
       this.time = time;
    }
}