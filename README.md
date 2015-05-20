# EulerNumberCalculator 
The default setup is to calculate the euler number with precision 1000 digits after the floating point. 
The formula used to calculate _e_ is: 
sum from 0 to Infinity: (2k + 1)/(2k)! 
 
Command line arguments: 
-t       sets the number of threads to use. e.g. -t 4 will make the program use 4 threads. 
-p       sets the maximal member of the sequence. e.g. -p 300 will calculate the sum from 0 to 300. 
-o       output file for the result, result.txt by default.
-q 	 will output only the time needed to compute the result.
