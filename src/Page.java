public class Page {
    private int pageValue;
    private int enteredAtTime = 0;

    Page(int pageValue) {
        this.pageValue = pageValue;
    }

    public int getPageValue() {
        return pageValue;
    }

    public void setPageValue(int pageValue) {
        this.pageValue = pageValue;
    }

    public int getEnteredAtTime() {
        return enteredAtTime;
    }

    public void setEnteredAtTime(int enteredAtTime) {
        this.enteredAtTime = enteredAtTime;
    }

    @Override
    public String toString() {
        return " " + pageValue;
    }
}
