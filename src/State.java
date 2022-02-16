/* Class: State
Purpose: Used by the EightQueens object to store possible states of the chess board
Author: Austin Bevacqua
 */

public class State implements Comparable<State>{
    private int totalCost;
    private int[] placement;

    public State(int inCost, int[] inState){
        totalCost = inCost;
        placement = inState;
    }

    public int getCost() {return totalCost;}
    public int[] getPlacement() {return placement;}

    public void printPlacement(){
        System.out.print("COST: (" + totalCost + ") ");
        for (int i : placement){
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // Determines the placements of the items if they need to be sorted
    @Override
    public int compareTo(State o) {
        return this.totalCost - o.totalCost;
    }

    public String toString(){
        StringBuilder out = new StringBuilder();
        for (int i : placement){
            out.append(i);
        }
        return out.toString();
    }
}