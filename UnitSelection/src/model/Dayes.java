package model;

public enum Dayes {

    ONE("Saturday / Monday"),
    TWO("Sunday / Tuesday");

    public String days;

    Dayes(String days) {
        this.days = days;
    }

    public static Dayes toDayes(int integerDay) {
        switch (integerDay) {
            case 1:
                return Dayes.ONE;

            default:
                return Dayes.TWO;
        }
    }
}
