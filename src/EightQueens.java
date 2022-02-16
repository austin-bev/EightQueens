/* Class: EightQueens
Purpose: An object which simulates the state of the Eight Queens puzzle
Author: Austin Bevacqua
 */

import java.util.*;

public class EightQueens{

    /* Inner Class: Queen
    Purpose: Holds all information for a "Queen" on the chessboard
    Author: Austin Bevacqua
     */
    private static class Queen{
        private int cost;
        private int value;

        public Queen(int inValue){
            cost = 0;
            value = inValue;
        }
    }

    /* Inner Class: Queen State
    Purpose: Holds all information for the chess board itself.
    Author: Austin Bevacqua
     */
    public static class QueenState{
        private Queen[] state;
        private int totalCost;

        // Constructor randomly generated a queen state
        public QueenState(int inSize){
            Random rand = new Random();
            state = new Queen[inSize];
            // The state list represents the locations of all the queens on the board (columns)
            for (int i = 0; i < inSize; i++) {
                state[i] = (new Queen(rand.nextInt(inSize)));
                checkCollisions(i, i);
                // Every queen is given a random placement, which is an abstraction of a chess board
                // 0 = top row, 1 = second row, ...
            }
            totalCost = overallCost();
        }

        public String toString(){
            StringBuilder out = new StringBuilder();
            for (Queen q : state){
                out.append(q.value);
            }
            return out.toString();
        }

        //Alternate constructor creates a new random state based off a predetermined "State"
        public QueenState(State inState, int inSize){
            state = new Queen[inSize];
            // The state list represents the locations of all the queens on the board (columns)
            for (int i = 0; i < inSize; i++) {
                state[i] = (new Queen(inState.getPlacement()[i]));
                checkCollisions(i, i);
                // Every queen is given a random placement, which is an abstraction of a chess board
                // 0 = top row, 1 = second row, ...
            }
            totalCost = inState.getCost();
        }

        //Calculates the cost for any given state of the chess board
        //1 collision = +2 on the cost
        public int overallCost(){
            int cost = 0;
            for (Queen q : state){
                cost += q.cost;
            }
            return cost;
        }

        //Prints the entire board to the screen
        public void printBoard(){
            for (int i = 0; i < state.length; i++){
                for (Queen queen : state) {
                    if (queen.value == i) {
                        System.out.print("Q");
                    } else {
                        System.out.print(".");
                    }
                }
                System.out.print("\n");
            }
            System.out.println("Total cost = " + totalCost);
        }

        //Moves a every queen to each available space and adds all the
        //possible states to the linked list Q
        public void moveQueens(LinkedList<State> q){
            for (int i = 0; i < state.length; i++){
                moveQueenColumn(i, q);
            }
        }

        //Used for constructing the state space. Sets the number of collisions of every queen.
        private void checkCollisions(int maxVal, int current) {
            for (int i = 0; i < maxVal; i++) {
                if (i != current) {
                    // Checks horizontal collisions
                    if (state[i].value == state[current].value) {
                        addCollision(i, current);
                    // Checks diagonal collisions
                    // Current column of number -+ row of checked number
                    } else if (state[i].value == (state[current].value - (current - i))) {
                        addCollision(i, current);
                    } else if (state[i].value == (state[current].value + (current - i))) {
                        addCollision(i, current);
                    }
                }
            }
        }

        //Returns how many collisions an individual queen will have.
        private int checkNumCollisions(int current){
            int numNewCollisions = 0;
            for (int i = 0; i < state.length; i++) {
                if (i != current) {
                    // Checks horizontal collisions
                    if (state[i].value == state[current].value) {
                        numNewCollisions++;
                    // Checks diagonal collisions
                    // Current column of number -+ row of checked number
                    } else if (state[i].value == (state[current].value - (current - i))) {
                        numNewCollisions++;
                    } else if (state[i].value == (state[current].value + (current - i))) {
                        numNewCollisions++;
                    }
                }
            }
            return numNewCollisions;
        }

        //Makes 2 queen nodes have a "collision", increasing both their costs bt 1
        private void addCollision(int i, int current){
            state[i].cost = state[i].cost + 1;
            state[current].cost = state[current].cost + 1;
        }

        //Moves a singular queen to each available space and adds all the
        //possible states to the linked list Q
        private void moveQueenColumn(int current, LinkedList<State> q){
            int[] queenPositions;
            int newCollisions;
            int baseCollisions = totalCost - (state[current].cost *2);
            int originalPos = state[current].value;

            for (int i = 0; i < state.length; i++) {
                if (i != originalPos) { //We ignore the current state space
                    state[current].value = i; //We set the new location of the queen
                    //Calculate the new cost of the queen and add it
                    newCollisions = baseCollisions + (checkNumCollisions(current)*2);
                    //We add the locations of the queens and the cost to a priority queue
                    queenPositions = getQueenPositions();
                    q.add(new State(newCollisions, queenPositions));
                }
            }
            state[current].value = originalPos;
        }

        //Creates a deep copy of the current queen positions.
        private int[] getQueenPositions(){
            int[] queensPositions = new int[state.length];
            for (int i = 0; i < state.length; i++){
                queensPositions[i] = state[i].value;
            }
            return queensPositions;
        }
    }
}
