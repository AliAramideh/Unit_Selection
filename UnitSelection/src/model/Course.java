package model;

import java.util.ArrayList;

public class Course {

    private ArrayList<String> codes;
    private String name;
    private int units;
    private int group;
    private ArrayList<Times> times;
    private ArrayList<Dayes> dayes;
    private ArrayList<Teacher> teachersOfCourse;

    {
        times = new ArrayList<>();
        teachersOfCourse = new ArrayList<>();
        dayes = new ArrayList<>();
        codes = new ArrayList<>();
    }

    public Course(String name, String code, int units, Teacher teacher, Dayes dayes, Times times) {
        codes.add(code);
        this.name = name;
        this.units = units;
        teachersOfCourse.add(teacher);
        this.times.add(times);
        this.dayes.add(dayes);
        group = 1;
    }

    public void addTeacher(Teacher teacher) {
        teachersOfCourse.add(teacher);
    }

    public void addTimes(Times times) {
        this.times.add(times);
    }

    public void addDayes(Dayes dayes) {
        this.dayes.add(dayes);
    }

    public boolean teacherIsThisCourseTeachers(Teacher teacher) {
        if (teachersOfCourse.size() == 0)
            return false;
        for (Teacher ourTeache : teachersOfCourse) {
            if (ourTeache.getName().equals(teacher.getName()))
                return true;
        }
        return false;
    }

    public boolean areTheCoursePresentedOnThisDayes(Dayes dayes) {
        if (this.dayes.size() == 0)
            return false;
        for (Dayes ourDayes : this.dayes) {
            if (ourDayes == dayes)
                return true;
        }
        return false;
    }

    public boolean areTheCoursePresentedOnThisTimes(Times times) {
        if (this.times.size() == 0)
            return false;
        for (Times ourTimes : this.times) {
            if (ourTimes == times)
                return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public int getUnits() {
        return units;
    }

    public ArrayList<Times> getTimes() {
        return times;
    }

    public ArrayList<Dayes> getDayes() {
        return dayes;
    }

    public ArrayList<Teacher> getTeachersOfCourse() {
        return teachersOfCourse;
    }

    public int getGroup() {
        return group;
    }

    public ArrayList<String> getCodes() {
        return codes;
    }

    public void setUints(int units) {
        this.units = units;
    }

    public void setGroup(int group) {
        this.group = group;
    }

}
