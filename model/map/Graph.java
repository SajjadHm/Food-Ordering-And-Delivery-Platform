public class Graph {
    int n;
    int edgeNum;
    int[][] matrix;

    public Graph(int n, int m){
        this.n = n;
        this.edgeNum = m;
        this.matrix = new int[n][n];
    }
    public Graph(){
        this.n = 0;
        this.edgeNum = 0;
        this.matrix = new int[n][n];
    }
    public void setEdge (int i, int j, int weight){
        this.matrix[i][j]=weight;
    }
    public int getNodesNum (){
        return this.n;
    }
}
