# Virtual Memory Page Replacement
Simulates three virtual memory page replacement algorithms: FIFO, LRU, and Optimal.
I tested with a reference string of 100 and 10,000. For the purposes of submission, 100 pages are used.

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
Where %temp% will become C:\Users\<name>\AppData\Local\Temp

#### On Linux: genData.bat
`#!/bin/bash`    
`rm test.csv`     
`for ((i = 1; i < 31; i++)); do`     
&nbsp;&nbsp;&nbsp;`java -jar "VirtualMemoryPageReplacement.jar" 1 $i`    
&nbsp;&nbsp;&nbsp;`cp test.csv /data/test-1-$i.data`    
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
![alt text](https://i.imgur.com/LPJLDUC.png)

We see some variance and jaggedness in the graph. Overall, FIFO and LRU behave similarly. A closer 
inspection reveals that LRU slightly outperforms FIFO and this is confirmed when we look at the raw data. 
For most runs (number of frames varied from 1 to 30), LRU produces fewer page faults. 

The optimal algorithm outperforms both and drops # page faults quite fast after 3 available frames, though it does
begin to converge back at around 30. 
 
For a page reference string of size 10,000: 
![alt text](https://i.imgur.com/hKgcyeV.png)

Though the assignment did not require this, I tested with 10,000 pages to allow the graph to approximate its 
actual shape. We do see smooth curves here and any differences between LRU and FIFO are marginal, with both 
algorithms presenting a steady linear decrease in the number of page faults. 

Here with 10,000 pages, Optimal clearly outperforms both and provides a convex curve far below both other 
algorithmic approaches. 

## 3. Conclusion
The Optimal algorithm produces significantly fewer page faults than either FIFO or LRU. 
A caveat is that it requires/presumes a prior knowledge, which may not always be feasible in practice. 

For minimizing cost and ease of implementation, the FIFO algorithm is likely a good choice for real-life 
scenarios. 

## Class Diagram
![alt text](https://i.imgur.com/KUTVUdN.png)


 
