public class Node implements Comparable<Node> {
    int id;
    int distance;

    public Node (){
        this.id=0;
        this.distance=0;
    }

    public Node(int id, int distance) {
        this.id = id;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.distance, other.distance);
    }
}
