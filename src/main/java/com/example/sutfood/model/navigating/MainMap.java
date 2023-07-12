package com.example.sutfood.model.navigating;

import java.util.ArrayList;
import java.util.Arrays;

public class MainMap {
    public static void main(String[] args) {

        // Input
        // Source & Destination is the index of the nodes (from 1 to 1000)
        // مبدا و مقصد شماره راس مربوطه در گراف است از 1 تا 1000
        int source=258;
        int destination=349;
        int[] shortestRouteFound;

        //The output is an int array with a length of (number of nodes in path + 1)
        //The last index of the output array is the distance of path

        shortestRouteFound = ShortestRoute.runPathFinding(source,destination);

        System.out.println(Arrays.toString(shortestRouteFound));

        //if you want distance/time use this
        //shortestRouteFound[shortestRouteFound.length]

        //if you want path use this
        //shortestRouteFound[0] to shortestRouteFound[shortestRouteFound.length-1]

        



        //Printing output just in case

//        if (shortestRouteFound.length == 0) {
//            System.out.println("No path found from node " + sourcePlusOne + " to node " + destinationPlusOne);
//        } else {
//            System.out.print("Shortest path from node " + source + " to node " + destination + ": ");
//            for (int c=0; c<shortestRouteFound.length-1; c++) {
//                System.out.print(shortestRouteFound[c]+1);
//                if(c!=shortestRouteFound.length-2) System.out.print(" ---> ");
//            }
//            System.out.println();
//            System.out.println("And the distance is: " + shortestRouteFound[shortestRouteFound.length-1]);
//        }
    }
}
