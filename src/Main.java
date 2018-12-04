import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    private static ArrayList<Page> referenceStringList = new ArrayList<>(100);
    private static int numPageFaults = 0;

    public static void main(String[] args) {

        if (args.length == 0 || args[0].toLowerCase().equals("help") || args.length < 2) {
            printProgramInstructions();
        } else {
            final int algorithm = Integer.parseInt(args[0]);
            final int numFrames = Integer.parseInt(args[1]);
            Frames frames = new Frames(numFrames);

            //Uncomment below function call to populate list with known test values
            //Make sure to comment out the for-loop that follows
            populateTestList();

            //Comment out this for loop if above call to populateTestList() is active
//            for (int i = 0; i < 10000; i++) {
//                referenceStringList.add(new Page(generateRandomNums()));
//            }

            if(algorithm == 1) {
                fifo(frames);
            } else if (algorithm == 2) {
                lru(frames);
            } else if (algorithm == 3) {
                optimal(frames, numFrames);
            } else {
                System.out.println("Invalid algorithm value provided. Please input 1-3 for FIFO, LRU, or Optimal respectively.");
            }

            printStatistics(numFrames);
        }
    } // end main

    /**
     * This method defines the First In First Out page replacement algorithm.
     * @param frames
     */
    private static void fifo(Frames frames) {

        for (int i = 0; i < referenceStringList.size(); ++i) {
            boolean isInFrame = false;
            //set first element
            if (frames.frameList.isEmpty()) {
                numPageFaults++;
                frames.frameList.add(referenceStringList.get(i));
                continue;
            }

            for (int j = 0; j < frames.getSize(); j++) {
                if (frames.frameList.get(j).getPageValue() == referenceStringList.get(i).getPageValue()) {
                    isInFrame = true;
                }
            }

            if (!isInFrame && frames.isThereEmptyFrame()) {
                frames.frameList.add(referenceStringList.get(i));
                referenceStringList.get(i).setEnteredAtTime(i);
                numPageFaults++;
            } else if (isInFrame && frames.isThereEmptyFrame()) {
                // do nada
            } else if (!isInFrame && !frames.isThereEmptyFrame()) {
                numPageFaults++;
                referenceStringList.get(i).setEnteredAtTime(i);
                frames.frameList.set(frames.getIndexFirstArrival(), referenceStringList.get(i));
            } else if (isInFrame && !frames.isThereEmptyFrame()) {
                // do nada
            }
            //System.out.println(frames.frameList.toString());
        } // end main for

        System.out.println(frames.frameList.toString());
    } // end fifo

    /**
     * This method defines the Least Recently Used page replacement algorithm.
     * @param frames
     */
    private static void lru(Frames frames) {
        int index = 0;

        for (int i = 0; i < referenceStringList.size(); ++i) {
            boolean isInFrame = false;
            //set first element
            if (frames.frameList.isEmpty()) {
                numPageFaults++;
                frames.frameList.add(referenceStringList.get(i));
                continue;
            }

            //System.out.println("Curr page" + referenceStringList.get(i));
            for (int j = 0; j < frames.getSize(); j++) {
                if (frames.frameList.get(j).getPageValue() == referenceStringList.get(i).getPageValue()) {
                    isInFrame = true;
                    index = j;
                }
            }

            if (!isInFrame && frames.isThereEmptyFrame()) {
                frames.frameList.add(referenceStringList.get(i));
                referenceStringList.get(i).setEnteredAtTime(i);
                numPageFaults++;
            } else if (isInFrame && frames.isThereEmptyFrame()) {
                // do nada
                frames.frameList.get(index).setEnteredAtTime(i);
            } else if (!isInFrame && !frames.isThereEmptyFrame()) {
                numPageFaults++;
                referenceStringList.get(i).setEnteredAtTime(i);
                frames.frameList.set(frames.getIndexFirstArrival(), referenceStringList.get(i));
            } else if (isInFrame && !frames.isThereEmptyFrame()) {
                //update time entered because it was "recently used," which is needed by algorithm
                frames.frameList.get(index).setEnteredAtTime(i);
            }
            //System.out.println(frames.frameList.toString());
        } // end main for

        System.out.println(frames.frameList.toString());
    } // end lru

    private static void optimal(Frames frames, int numFrames) {
        int index = 0;

        for (int i = 0; i < referenceStringList.size(); ++i) {
            boolean isInFrame = false;
            //set first element
            if (frames.frameList.isEmpty()) {
                numPageFaults++;
                frames.frameList.add(referenceStringList.get(i));
                continue;
            }

            //System.out.println("Curr page" + referenceStringList.get(i));
            for (int j = 0; j < frames.getSize(); j++) {
                if (frames.frameList.get(j).getPageValue() == referenceStringList.get(i).getPageValue()) {
                    isInFrame = true;
                    index = j;
                    //System.out.println(frames.frameList.get(j));
                    //System.out.println(true);
                }
            }

            if (!isInFrame && frames.isThereEmptyFrame()) {
                frames.frameList.add(referenceStringList.get(i));
                referenceStringList.get(i).setEnteredAtTime(i);
                numPageFaults++;
            } else if (isInFrame && frames.isThereEmptyFrame()) {
                // do nada
                frames.frameList.get(index).setEnteredAtTime(i);
            } else if (!isInFrame && !frames.isThereEmptyFrame()) {
                numPageFaults++;
                referenceStringList.get(i).setEnteredAtTime(i);
                frames.frameList.set(getIndexOfFarthestNeeded(numFrames, frames, i), referenceStringList.get(i));

                for (int k = 0; k < numFrames; k++) {
                    frames.frameList.get(k).setFirstFutureOccurrenceIndex(-1);
                }

            } else if (isInFrame && !frames.isThereEmptyFrame()) {
                //update time entered because it was "recently used," which is needed by algorithm
                //referenceStringList.get(i).setEnteredAtTime(i);
                frames.frameList.get(index).setEnteredAtTime(i);
            }
            //System.out.println(frames.frameList.toString());
        } // end main for

        System.out.println(frames.frameList.toString());
    } // end optimal


    private static int getIndexOfFarthestNeeded(int numFrames, Frames frames, int currIndex) {

        for (int i = 0; i < numFrames; i++) {
            //System.out.println("Frame: " + i);
            try {
                for (int j = currIndex + 1; j < 100; j++) {
                    if (frames.frameList.get(i).getPageValue() == referenceStringList.get(j).getPageValue()) {
                        frames.frameList.get(i).setFirstFutureOccurrenceIndex(j);
                        break;
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                //
            }
        }

        int latest = frames.frameList.get(0).getFirstFutureOccurrenceIndex();
        int indexToReturn = 0;

        if (frames.frameList.get(0).getFirstFutureOccurrenceIndex() == -1) {
            return 0;
        }

        for (int i = 1; i < numFrames; i++) {
            //System.out.println("frame first occurrence " + frames.frameList.get(i).getFirstFutureOccurrenceIndex());
            if (frames.frameList.get(i).getFirstFutureOccurrenceIndex() == -1) {
                return i;
            }
            else if(frames.frameList.get(i).getFirstFutureOccurrenceIndex() > latest) {
                latest = frames.frameList.get(i).getFirstFutureOccurrenceIndex();
                indexToReturn = i;
            }
        }

        return indexToReturn;
    }

    /**
     * This method populates the reference string with KNOWN values from Assignment 4, where we can compare the results
     * to determine if the algorithms behave as desired. The finalized project retains but does not use this function.
     * Instead, it dynamically generates 100 random values in range (1,49) for processing.
     */
    private static void populateTestList() {
        referenceStringList.add(new Page(7));
        referenceStringList.add(new Page(2));
        referenceStringList.add(new Page(3));
        referenceStringList.add(new Page(1));
        referenceStringList.add(new Page(2));
        referenceStringList.add(new Page(5));
        referenceStringList.add(new Page(3));
        referenceStringList.add(new Page(4));
        referenceStringList.add(new Page(6));
        referenceStringList.add(new Page(7));
        referenceStringList.add(new Page(7));
        referenceStringList.add(new Page(1));
        referenceStringList.add(new Page(0));
        referenceStringList.add(new Page(5));
        referenceStringList.add(new Page(4));
        referenceStringList.add(new Page(6));
        referenceStringList.add(new Page(2));
        referenceStringList.add(new Page(3));
        referenceStringList.add(new Page(0));
        referenceStringList.add(new Page(1));
    }

    static int generateRandomNums() {
        Random rand = new Random();
        return rand.nextInt((49-1) + 1) + 1;
    }

    /***
     * This method prints usage instructions to the command line if the user does not specify any
     * command line arguments or types the word 'help'
     */
    private static void printProgramInstructions() {
        System.out.println("Virtual Memory Page Replacement Simulator. ");
        System.out.println("Author: Borislav Sabotinov");
        System.out.println("java -jar VirtualMemoryPageReplacement.jar <algorithm_type> <numFrames>");
        System.out.println("[algorithm_type] : value can be in the range (1,3) inclusive.");
        System.out.println("\t1 - First Come First Served (FCFS) ");
        System.out.println("\t2 - Least Recently Used (LRU) ");
        System.out.println("\t3 - Optimal");
        System.out.println("[numFrames] : defines the number of available frames, range (1,30)");
    } // end printProgramInstructions

    private static void printStatistics(int numFrames) {
        System.out.println("Number of frames available: " + numFrames);
        System.out.println("Number of page Faults: " + numPageFaults);

        try {
            FileWriter pw = new FileWriter("test.csv", true);
            BufferedReader br = new BufferedReader(new FileReader("test.csv"));
            StringBuilder sb = new StringBuilder();

            if(br.readLine() == null) {
                sb.append("# Frames");
                sb.append(',');
                sb.append("# Page Faults");
            }

            sb.append('\n');
            sb.append(numFrames);
            sb.append(',');
            sb.append(numPageFaults);

            pw.write(sb.toString());
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

} // end class
