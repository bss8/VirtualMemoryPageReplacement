public class Page {
    private int pageValue;
    private int enteredAtTime = 0;
    private int firstFutureOccurrenceIndex;

    int getFirstFutureOccurrenceIndex() {
        return firstFutureOccurrenceIndex;
    }

    void setFirstFutureOccurrenceIndex(int firstFutureOccurrenceIndex) {
        this.firstFutureOccurrenceIndex = firstFutureOccurrenceIndex;
    }

    Page(int pageValue) {
        this.pageValue = pageValue;
        this.firstFutureOccurrenceIndex = -1;
    }

    int getPageValue() {
        return pageValue;
    }

    public void setPageValue(int pageValue) {
        this.pageValue = pageValue;
    }

    int getEnteredAtTime() {
        return enteredAtTime;
    }

    void setEnteredAtTime(int enteredAtTime) {
        this.enteredAtTime = enteredAtTime;
    }

    @Override
    public String toString() {
        return " " + pageValue;
    }
} // end class Page
