My submission for one of the questions in my final assesment for COMP2009 (v.1) Intelligent Agents at Curtin. The problem statement was to write an algorithm which can solve the n queens problem with a Hill-climbing algorithm.

## Compiling

Run ./make to compile the program.
This will create a /dist directory with the compiled .class files

##Running

Run with ./san <heat> <heatchange> 
OR ./san <heat> <heatchange> <size>

Running ./san will automatically compile the program if not already compiled.

Heat, heatchange, and size must be in integer format.
Heat change cannot be negative. Will get absolute value if a negative value in inputted.

"./san 10 2" will run
	Heat of 10%
	Will reduce by 2% each time
