package model;

public enum Times {

    FIRST("7:30 to 9:00"),
    SECOND("9:00 to 10:30"),
    THIRD("10:30 to 12:00");

    public String time;

    Times(String time) {
        this.time = time;
    }

    public static Times toTimes(int time) {
        switch (time) {
            case 1:
                return Times.FIRST;
            case 2:
                return Times.SECOND;
            default:
                return Times.THIRD;
        }
    }
}
