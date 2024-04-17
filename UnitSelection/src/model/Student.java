package model;

import java.util.ArrayList;

public class Student {

    private String firstName;
    private String lastName;
    private int year;
    private int code; // studentID
    private String phoneNumber;
    private int pasdUnitsNumber;
    private ArrayList<CourseByCode> courses;
    private boolean completedProfile;
    private final int maxUnitsNumberInASemester = 20;
    private int unitsNumberSelected;
    private boolean loggedIn;

    public Student(String firstName, String lastName, int code) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.code = code;
        completedProfile = false;
        unitsNumberSelected = 0;
        courses = new ArrayList<>();
    }

    public boolean isCompletedProfile() {
        return completedProfile;
    }

    public boolean isEqual(Student student) {
        if (student.getFirstName().equals(this.firstName)) {
            if (student.getLastName().equals(this.lastName)) {
                if (student.getCode() == this.code)
                    return true;
            }
        }
        return false;
    }

    public boolean ismaxUnitsNumberWillExceed(int unitsNumber) {
        int newUnitsNumber = unitsNumberSelected + unitsNumber;
        if (maxUnitsNumberInASemester >= newUnitsNumber)
            return false;
        if (maxUnitsNumberInASemester == this.unitsNumberSelected)
            return true;
        return true;
    }

    public boolean isSelectedSameCourse(String courseCode) {
        if (courses.size() == 0)
            return false;
        for (CourseByCode course : courses) {
            if (course.getCode().substring(0, 5).equals(courseCode.substring(0, 5)))
                return true;
        }
        return false;
    }

    public void addCourse(CourseByCode course) {
        courses.add(course);
        this.unitsNumberSelected += course.getUnits();
    }

    public void deleteCourse(CourseByCode course) {
        courses.remove(course);
        unitsNumberSelected -= course.getUnits();
    }

    // getters and setters:
    public int getUnitsNumberSelected() {
        return unitsNumberSelected;
    }

    public int getCode() {
        return code;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ArrayList<CourseByCode> getCourses() {
        return courses;
    }

    public int getYear() {
        return year;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getPasdUnitsNumber() {
        return pasdUnitsNumber;
    }

    public int getMaxUnitsNumberInASemester() {
        return maxUnitsNumberInASemester;
    }

    public void setUnitsNumberSelected(int unitsNumberSelected) {
        this.unitsNumberSelected = unitsNumberSelected;
    }

    public void setCompletedProfile(boolean completedProfile) {
        this.completedProfile = completedProfile;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPasdUnitsNumber(int pasdUnitsNumber) {
        this.pasdUnitsNumber = pasdUnitsNumber;
    }

    public void setLoggedIn(boolean setLoggedIn) {
        this.loggedIn = setLoggedIn;
    }

}
