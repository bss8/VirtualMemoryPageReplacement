# Virtual Memory Page Replacement
Simulates three virtual memory page replacement algorithms: FIFO, LRU, and Optimal

## 1. How To: Set up & Run
This simulator is implemented using the Java language. 
A Java installation is required to run the JAR artifact. 

A batch (Windows) or shell (Linux) script may be used to obtain simulation results. 

#### On Windows: genData.bat 
`del "test.csv" /q`         
`FOR /L %%A IN (1,1,30) DO (`    
&nbsp;&nbsp;&nbsp;`Java -jar "VirtualMemoryPageReplacement.jar" 2 %%A`    
`)`    
`move "test.csv" %temp%`

#### On Linux: genData.bat
`#!/bin/bash`    
`rm sim.data`     
`for ((i = 1; i < 31; i++)); do`     
&nbsp;&nbsp;&nbsp;`./sim 1 $i`    
&nbsp;&nbsp;&nbsp;`cp sim.data /data/sim-1-$i.data`    
`done`
 
 As can be observed from the example scripts, to manually run the program, at    
 a command line terminal, invoke the following:
 `java -jar "VirtualMemoryPageReplacement.jar`
 
 Execute without parameters to see instructions and information on what parameters   
 the program accepts. Alternatively, you may also type 'Help' (capitalization does not matter)"
 `java -jar "DiscreteEventSimulation.jar `   
 or `java -jar "VirtualMemoryPageReplacement.jar help`
 
 The following will be displayed: 
         
 Virtual Memory Page Replacement Simulator.  
 Author: Borislav Sabotinov   
 `java -jar VirtualMemoryPageReplacement.jar <algorithm_type> <numFrames>`    
 #####[algorithm_type] : value can be in the range (1,3) inclusive.   
         1 - First Come First Served (FCFS)
         2 - Least Recently Used (LRU)
         3 - Optimal  
 [numFrames] : defines the number of available frames, range (1,30)   
 
 Please refer to attached documentation for further details.    
 
## 2. Results 

The Optimal algorithm / strategy for page replacements emerges as preferable, consistently outperforming 
the other two in terms of the number of page faults generated. 

For a page reference string of size 100: 
![alt text](https://i.imgur.com/ccb4e8F.png)

For a page reference string of size 10,000: 
![alt text](https://i.imgur.com/IUcyI6O.png)

Most notable for me is the similarity between LRU and FIFO as they appear to behave in a similar manner.  

## 3. Conclusion
 
