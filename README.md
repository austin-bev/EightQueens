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
