public enum DisplayState {
    HOME(0),
    STUDENT(1),
    GRADE(2);

    private final int value;

    private DisplayState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

