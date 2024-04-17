package model;

import java.util.ArrayList;

public class DB {
    // Data Base

    private static ArrayList<Student> students;
    private static ArrayList<Teacher> teachers;
    private static ArrayList<Course> courses;
    private static Student loggedInStudent;
    private static ArrayList<CourseByCode> allCoursesSequential;

    public static Student getLoggedInStudent() {
        return loggedInStudent;
    }

    public static void setLoggedInStudent(Student loggedInStudent) {
        DB.loggedInStudent = loggedInStudent;
    }

    static {
        students = new ArrayList<>();
        teachers = new ArrayList<>();
        courses = new ArrayList<>();
        allCoursesSequential = new ArrayList<>();
    }

    public static void logOutLoggedInStudent() {
        loggedInStudent = null;
    }

    public static Student getStudent(int code) {
        if (students.size() == 0)
            return null;
        for (Student student : students) {
            if (student.getCode() == code)
                return student;
        }
        return null;
    }

    public static boolean areWeHaveStudents() {
        if (students.size() == 0)
            return false;
        return true;
    }

    public static void addStudent(Student student) {
        students.add(student);
    }

    public static Course getCourse(String name) {
        if (courses.size() == 0)
            return null;
        for (Course course : courses) {
            if (course.getName().equals(name))
                return course;
        }
        return null;
    }

    public static CourseByCode getCourseFromCorseByCode(String courseCode) {
        if (allCoursesSequential.size() == 0)
            return null;
        for (CourseByCode course : allCoursesSequential) {
            if (course.getCode().equals(courseCode))
                return course;
        }
        return null;
    }

    public static Teacher getTeacher(String name) {
        if (teachers.size() == 0)
            return null;
        for (Teacher teacher : teachers) {
            if (teacher.getName().equals(name))
                return teacher;
        }
        return null;
    }

    public static void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public static void addCourse(Course course) {
        courses.add(course);
    }

    public static ArrayList<Course> getCourses() {
        return courses;
    }

    public static void addCourseToAllCoursesSequential(CourseByCode course) {
        allCoursesSequential.add(course);
    }

    public static ArrayList<CourseByCode> getAllCoursesSequential() {
        return allCoursesSequential;
    }

    public static CourseByCode getCourseByCode(String code) {
        if (allCoursesSequential.size() == 0)
            return null;
        for (CourseByCode course : allCoursesSequential) {
            if (course.getCode().equals(code))
                return course;
        }
        return null;
    }

    public static ArrayList<Student> getStudents() {
        return students;
    }

    public static void setStudents(ArrayList<Student> students) {
        DB.students = students;
    }

    public static ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public static void setTeachers(ArrayList<Teacher> teachers) {
        DB.teachers = teachers;
    }

    public static void setCourses(ArrayList<Course> courses) {
        DB.courses = courses;
    }

    public static void setAllCoursesSequential(ArrayList<CourseByCode> allCoursesSequential) {
        DB.allCoursesSequential = allCoursesSequential;
    }

}
