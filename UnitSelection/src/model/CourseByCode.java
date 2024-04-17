package model;

public class CourseByCode {
    private String code;
    private String name;
    private int units;
    private Times times;
    private Dayes dayes;
    private Teacher teacher;

    public CourseByCode(String code, String name, int units, Times times, Dayes dayes, Teacher teacher) {
        this.code = code;
        this.name = name;
        this.units = units;
        this.times = times;
        this.dayes = dayes;
        this.teacher = teacher;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getUnits() {
        return units;
    }

    public Times getTimes() {
        return times;
    }

    public Dayes getDayes() {
        return dayes;
    }

    public Teacher getTeacher() {
        return teacher;
    }

}
