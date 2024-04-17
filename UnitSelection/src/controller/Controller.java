package controller;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Course;
import model.CourseByCode;
import model.DB;
import model.Dayes;
import model.Student;
import model.Teacher;
import model.Times;
import view.massages.MenuMassages;

public class Controller {

    public static void importCourse(String courseName, int courseUnits, String teacherName, int integerDay,
            int integerTime, String courseCode) {
        Times time = Times.toTimes(integerTime);
        Dayes day = Dayes.toDayes(integerDay);
        Teacher teacher;
        Course course;
        if (DB.getTeacher(teacherName) == null) { // new teacher
            teacher = new Teacher(teacherName);
            DB.addTeacher(teacher);
        } else
            teacher = DB.getTeacher(teacherName);
        if (DB.getCourse(courseName) == null) { // Course is new
            course = new Course(courseName, courseCode, courseUnits, teacher, day, time);
            DB.addCourse(course);
        } else {
            course = DB.getCourse(courseName);
            int newCourseGroup = course.getGroup() + 1;
            course.setGroup(newCourseGroup);
            if (!course.teacherIsThisCourseTeachers(teacher))
                course.addTeacher(teacher);
            if (!course.areTheCoursePresentedOnThisDayes(day))
                course.addDayes(day);
            if (!course.areTheCoursePresentedOnThisTimes(time))
                course.addTimes(time);
        }
        CourseByCode courseForAllCourses = new CourseByCode(courseCode, courseName, courseUnits, time, day, teacher);
        DB.addCourseToAllCoursesSequential(courseForAllCourses);
        // DB.addToAllCoursesSequential(course)
    }

    public static boolean checkMaches(String input, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches())
            return true;
        return false;
    }

    public static MenuMassages checkSignIn(String firstName, String lastName, String code) {
        if (!checkMaches(firstName, "[A-Z][a-z]+"))
            return MenuMassages.INCORRECT_FISRST_NAME;
        if (!checkMaches(lastName, "[A-Z][a-z]+"))
            return MenuMassages.INCORRECT_LAST_NAME;
        if (!checkMaches(code, "\\d{9}"))
            return MenuMassages.INCORRECT_CODE;
        int integerCode = Integer.parseInt(code);
        if (DB.getStudent(integerCode) != null)
            return MenuMassages.STUDENT_EXISTS;
        Student student = new Student(firstName, lastName, integerCode);
        DB.addStudent(student);
        return MenuMassages.SIGNIN_SUCCES;
    }

    public static MenuMassages checkLogIn(String firstName, String lastName, String code) {
        if (checkMaches(code, "\\d{9}")) {
            int integerCode = Integer.parseInt(code);
            Student student = DB.getStudent(integerCode);
            if (student != null) {
                if ((student.getFirstName().equals(firstName)) && (student.getLastName().equals(lastName))) {
                    if (!student.equals(DB.getLoggedInStudent())) {
                        student.setLoggedIn(true);
                        DB.setLoggedInStudent(student);
                        return MenuMassages.LOGIN_SUCCES;
                    }
                    return MenuMassages.INVALID_COMMAND;
                }
            }
        }
        return MenuMassages.STUDENT_DOES_NOT_EXIST;
    }

    public static MenuMassages checkLogOut() {
        if (DB.getLoggedInStudent() == null)
            return MenuMassages.INVALID_COMMAND;
        Student student = DB.getLoggedInStudent();
        DB.logOutLoggedInStudent();
        student.setLoggedIn(false);
        return MenuMassages.LOGOUT_SUCCES;
    }

    public static MenuMassages checkEditProfile(String stringYear, String phoneNumber, String stringPassedUnitsNumber) {
        if (DB.getLoggedInStudent() == null)
            return MenuMassages.INVALID_COMMAND;
        if (!checkMaches(stringYear, "\\d+"))
            return MenuMassages.YEAR_MUST_BE_A_NUMBER;
        if (!checkMaches(stringYear, "140(1|2)"))
            return MenuMassages.YEAR_MUST_BE_1401_OR_1402;
        if (!checkMaches(phoneNumber, "[0-9]{10}"))
            return MenuMassages.INCORRECT_PHONE_NUMBER;
        if (!checkMaches(stringPassedUnitsNumber, "\\d+"))
            return MenuMassages.PASSED_UNITS_MUST_BE_A_NUMBER;
        int passedUnitsNumber = Integer.parseInt(stringPassedUnitsNumber);
        if ((passedUnitsNumber <= 10) || (passedUnitsNumber >= 90))
            return MenuMassages.INCORRECT_PASSED_UNITS_NUMBER;
        int year = Integer.parseInt(stringYear);
        Student student = DB.getLoggedInStudent();
        student.setYear(year);
        student.setPhoneNumber(phoneNumber);
        student.setPasdUnitsNumber(passedUnitsNumber);
        student.setCompletedProfile(true);
        return MenuMassages.EDIT_PROFILE_SUCCES;
    }

    public static MenuMassages checkSelectCourse(String courseCode) {
        if (DB.getLoggedInStudent() == null)
            return MenuMassages.INVALID_COMMAND;
        Student student = DB.getLoggedInStudent();
        if (!student.isCompletedProfile())
            return MenuMassages.PLEASE_EDIT_PROFILE;
        if (DB.getCourseFromCorseByCode(courseCode) == null)
            return MenuMassages.INVALID_CODE;
        CourseByCode course = DB.getCourseFromCorseByCode(courseCode);
        if (student.ismaxUnitsNumberWillExceed(course.getUnits()))
            return MenuMassages.UNITS_LIMIT_ERROR;
        if (student.isSelectedSameCourse(courseCode))
            return MenuMassages.SAME_COURSE_ERROR;
        for (CourseByCode sampleCourse : student.getCourses()) {
            if (sampleCourse.getDayes() == course.getDayes()) {
                if (sampleCourse.getTimes() == course.getTimes())
                    return MenuMassages.SAME_TIME_ERROR;
            }
        }
        student.addCourse(course);
        // int newGetUnitsNumberSelected = course.getUnits() +
        // student.getUnitsNumberSelected();
        // student.setUnitsNumberSelected(newGetUnitsNumberSelected);
        return MenuMassages.COURSE_ADD_SUCCES;
    }

    public static ArrayList<CourseByCode> selectedCourses() {
        Student student = DB.getLoggedInStudent();
        return student.getCourses();
    }

    public static MenuMassages checkDeleteCourse(String courseCode) {
        if (DB.getLoggedInStudent() == null)
            return MenuMassages.INVALID_COMMAND;
        Student student = DB.getLoggedInStudent();
        if (!student.isSelectedSameCourse(courseCode))
            return MenuMassages.COURSE_DOES_NOT_EXIST;
        CourseByCode course = DB.getCourseByCode(courseCode);
        student.deleteCourse(course);
        return MenuMassages.COURSE_DELETED;
    }
}
