package com.example.sutfood.model.navigating;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ShortestRoute {
    public static int[] findShortestPath(Graph graph, int source, int destination) {
        int numNodes = graph.getNodesNum();
        int[] distances = new int[numNodes];
        int[] previous = new int[numNodes];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(previous, -1);

        distances[source] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(source, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.id == destination) {
                break;
            }

            for (int neighbor = 0; neighbor < numNodes; neighbor++) {
                int weight = graph.matrix[current.id][neighbor];
                if (weight > 0) {
                    int distance = current.distance + weight;
                    if (distance < distances[neighbor]) {
                        distances[neighbor] = distance;
                        previous[neighbor] = current.id;
                        queue.add(new Node(neighbor, distance));
                    }
                }
            }
        }

        if (distances[destination] == Integer.MAX_VALUE) {
            return new int[0]; // No path found
        }

        // Reconstruct the shortest path
        List<Integer> route = new ArrayList<>();
        int currentNId = destination;
        while (currentNId != -1) {
            route.add(currentNId);
            currentNId = previous[currentNId];
        }

        // Reverse the path
        Collections.reverse(route);

        // Convert the path to an array
        int[] shortestRoute = new int[route.size()+1];
        for (int i = 0; i < route.size(); i++) {
            shortestRoute[i] = route.get(i);
        }

        // Adding Distance
        shortestRoute[route.size()] = distances[destination];

        return shortestRoute;
    }

    public static int[] runPathFinding (int sourcePlusOne, int destinationPlusOne) {

        Graph graph=new Graph();
        int n,m,i,j,w;

        try {
            File graphFile = new File("D:/OOP/Food-Ordering-And-Delivery-Platform/src/main/java/com/example/sutfood/model/navigating/graph.txt");
            Scanner input = new Scanner(graphFile);
            int f=1;
            while (input.hasNextLine()) {
                if(f==1){
                    n=input.nextInt();
                    m=input.nextInt();
                    graph=new Graph(n,m);
                    f=0;
                }
                else {
                    i=input.nextInt();
                    j=input.nextInt();
                    w=input.nextInt();
                    graph.setEdge(i-1,j-1,w);
                    graph.setEdge(j-1,i-1,w);
                }
            }
            input.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred reading graph file.");
            e.printStackTrace();
        }

//        int sourcePlusOne = 258;
//        int destinationPlusOne = 349;
        int source=sourcePlusOne-1;
        int destination=destinationPlusOne-1;

        return findShortestPath(graph, source, destination);
    }
}

