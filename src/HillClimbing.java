/* Class: EightQueens
Purpose: Runs simulated annealing to try to solve the n queens puzzle.
Author: Austin Bevacqua
 */

import java.util.LinkedList;
import java.util.Random;

public class HillClimbing {

    /* Method: simAnealing
    Purpose: The wrapper method for the solveQueens algorithm.
    Author: Austin Bevacqua
     */
    public static void simAnnealing(int size, int heat, int heatchange){
        EightQueens.QueenState queens;
        //Heat cannot be negative
        if (heat < 0){
            heat = 0;
        }
        queens = solveQueens(size, heat, heatchange);
        System.out.println("Final Solution: " + queens);
        queens.printBoard();
    }

    /* Method: solveQueens
    Purpose: Solves the n queens problem through hill climbing / simulated annealing
             Will generate a state, generate all possible states, and then "step" to a better
             state. The heat will randomly decide to move to a "worse" or equal state.
    Author: Austin Bevacqua
     */
    private static EightQueens.QueenState solveQueens(int size, int heat, int heatchange) {
        Random rand = new Random();
        LinkedList<State> q = new LinkedList<>();
        State state;
        int index, randAccept, depth;

        //Generates the first random state
        EightQueens.QueenState queens = new EightQueens.QueenState(size);
        depth = 0;
        do {
            //Clears the list of possible states
            q.clear();
            //Generates the list of all possible states
            queens.moveQueens(q);
            System.out.println("\nCURRENT STATE = <<" + queens + ">>");
            System.out.println("CURRENT STATE COST = <<" + queens.overallCost() + ">>");
            System.out.println("CURRENT DEPTH = <<" + depth + ">>\n");
            depth++;
            /*Continues iterating until all possible states are exhausted
            or a better state has been found (or a state has been randomly selected)*/
            while (!q.isEmpty()) {
                //Randomly selects a state from all states
                index = rand.nextInt(q.size());
                state = q.get(index);
                System.out.println("Considering state " + state + " goodness (" + state.getCost() +")");
                //If the state has a lower cost, we switch to it. It is an improvement
                if (queens.overallCost() > state.getCost()){
                    System.out.println("\tThis is an improvement!. Accepted");
                    queens = new EightQueens.QueenState(state, size);
                    heat = changeTemp(heat, heatchange);
                    break;
                //If not, then we see if it can be randomly jumped to
                } else {
                    System.out.print("\tThis is not an improvement. Chance of acceptance is " + heat + "%");
                    randAccept = rand.nextInt(100) + 1; //Generates a number 1-100
                    //If it generates a number within the heat range, it is randomly jumped to
                    if (randAccept <= heat) {
                        System.out.println(". Accepted");
                        queens = new EightQueens.QueenState(state, size);
                        heat = changeTemp(heat, heatchange);
                        break;
                    //Otherwise, it is rejected and the state is removed from the list.
                    } else {
                        System.out.println(". Rejected");
                        q.remove(index);
                    }
                }
            }
        //Loop continues until list is echaused or we have found a state with a cost of 0; the goal state
        }while((q.size() != 0) && (queens.overallCost() != 0));
        return queens;
    }

    //Changes the temperature (randomness) of the hill climbing.
    //Gets less random the higher the depth
    private static int changeTemp(int heat, int heatchange){
        int newheat = heat - heatchange;
        if (newheat < 0){
            newheat = 0;
        }
        return newheat;
    }
}
