import java.util.ArrayList;


/**
 * Frames - user sets value 1 to 30 inclusive.
 * Each frame holds a Page.
 */
public class Frames {

    ArrayList<Page> frameList;
    private int numFrames;

    Frames(int numFrames) {
        this.numFrames = numFrames;
        frameList = new ArrayList<>(numFrames);
    }

    boolean isThereEmptyFrame() {
        return (frameList.isEmpty()) || (frameList.size() < numFrames);
    }

    int getSize() {
        return frameList.size();
    }

    /**
     * Used by FIFO, determines which page in the available frames entered first
     * @return
     */
    int getIndexFirstArrival() {
        int size = getSize();
        int earliest = 0;
        for (int i = 1; i < size; i++) {
            if (frameList.get(i).getEnteredAtTime() < frameList.get(earliest).getEnteredAtTime() ) {
                earliest = i;
            }
        }
        return earliest;
    }

} // end class Frames
