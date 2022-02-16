/* Class: EightQueens
Purpose: The main file in which the simulated annealing is accessed
Author: Austin Bevacqua
 */

public class Driver {
    public static void main(String[] args){
		try{
			int heat, heatchange, size;
			heat = Integer.parseInt(args[0]);
			heatchange = Math.abs(Integer.parseInt(args[1])); //We cannot have negative heat change
			//If no size is specified, we run with default value 16.
			if ((args.length) == 2) {
					HillClimbing.simAnnealing(16, heat, heatchange);
			}
			//Otherwise, we run with the user specified size.
			else{
				size = Math.abs(Integer.parseInt(args[2]));
				HillClimbing.simAnnealing(size, heat, heatchange);
			}
		}
		//If any of the arguments are in non-integer format, NumberFormatException will be thrown.
		catch (NumberFormatException e){
			System.out.println("Non-integer found. Only enter integer digits\nUsage: ./san <heat> <heatchange> ?<size>");
		}
    }
}
