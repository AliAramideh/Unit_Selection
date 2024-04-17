package view;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

import controller.Controller;
import model.CourseByCode;
import model.DB;
import model.Student;
import view.commands.MenuCommands;
import view.massages.MenuMassages;

public class Menu {

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        String stringNumberOfEECourses = scanner.nextLine();
        int numberOfEECourses = Integer.parseInt(stringNumberOfEECourses);
        String command;
        for (int i = 0; i < numberOfEECourses; i++) {
            Matcher matcher;
            command = scanner.nextLine();
            if ((matcher = MenuCommands.getMatcher(command, MenuCommands.GET_COURSE)) != null)
                importCourse(matcher);
            else
                System.out.println(MenuMassages.INVALID_COMMAND.output);
        }
        while (true) {
            command = scanner.nextLine();
            Matcher matcher;

            if ((matcher = MenuCommands.getMatcher(command, MenuCommands.END)) != null)
                break;
            else if ((matcher = MenuCommands.getMatcher(command, MenuCommands.SIGNIN)) != null)
                signIn(matcher);
            else if ((matcher = MenuCommands.getMatcher(command, MenuCommands.LOGIN)) != null)
                logIn(matcher);
            else if ((matcher = MenuCommands.getMatcher(command, MenuCommands.LOGOUT)) != null)
                logOut(matcher);
            else if ((matcher = MenuCommands.getMatcher(command, MenuCommands.EDIT_PROFILE)) != null)
                editProfile(matcher);
            else if ((matcher = MenuCommands.getMatcher(command, MenuCommands.SHOW_DEPARTMENT_LIST)) != null)
                showDepartmentList();
            else if ((matcher = MenuCommands.getMatcher(command, MenuCommands.SELECT_COURSE)) != null)
                selectCourse(matcher);
            else if ((matcher = MenuCommands.getMatcher(command, MenuCommands.SHOW_MY_LIST)) != null)
                showMyList();
            else if ((matcher = MenuCommands.getMatcher(command, MenuCommands.DELETE_COURSE)) != null)
                deleteCourse(matcher);
            else if ((matcher = MenuCommands.getMatcher(command, MenuCommands.SHOW_PROFILE)) != null)
                showProfile();
            else if ((matcher = MenuCommands.getMatcher(command, MenuCommands.SELECT_SEVERAL_COURSES)) != null)
                selectSeveralCourses(matcher);
            else
                System.out.println(MenuMassages.INVALID_COMMAND.output);
        }

    }

    // Regex:
    // (?<courseName>[^,]+)\,(?<courseUnits>\d{1,2})\,(?<teacher>[^,]+)\,(?<day>[12])\,(?<time>[123])\,(?<courseCode>\d+)\s*
    public static void importCourse(Matcher matcher) {
        String courseName = matcher.group("courseName");
        String stringCorseUnits = matcher.group("courseUnits");
        int courseUnits = Integer.parseInt(stringCorseUnits);
        String teacher = matcher.group("teacher");
        String stringDay = matcher.group("day");
        int day = Integer.parseInt(stringDay);
        String stringTime = matcher.group("time");
        int time = Integer.parseInt(stringTime);
        String courseCode = matcher.group("courseCode");
        Controller.importCourse(courseName, courseUnits, teacher, day, time, courseCode);
    }

    // Regex: -signin <Firstname> <Lastname> <Code>
    public static void signIn(Matcher matcher) {
        String firstName = matcher.group("firstName");
        String lastName = matcher.group("lastName");
        String code = matcher.group("code");
        MenuMassages massage;
        massage = Controller.checkSignIn(firstName, lastName, code);
        if (massage == MenuMassages.SIGNIN_SUCCES)
            System.out.println(massage.output + "\"" + firstName + " " + lastName + "\"" + " and ID number " + "\""
                    + code + "\" created !");
        else
            System.out.println(massage.output);
    }

    // Regex: -login <Firstname> <Lastname> <Code>
    public static void logIn(Matcher matcher) {
        String firstName = matcher.group("firstName");
        String lastName = matcher.group("lastName");
        String code = matcher.group("code");
        MenuMassages massage;
        massage = Controller.checkLogIn(firstName, lastName, code);
        if ((massage == MenuMassages.STUDENT_DOES_NOT_EXIST) || (massage == MenuMassages.INVALID_COMMAND))
            System.out.println(massage.output);
        else
            System.out.println(massage.output + " \"" + firstName + "\"");
    }

    public static void logOut(Matcher matcher) {
        MenuMassages massage = Controller.checkLogOut();
        System.out.println(massage.output);
    }

    // Regex: -edit profile <Year> <Phone number> <Passed units number>
    public static void editProfile(Matcher matcher) {
        String year = matcher.group("year");
        String stringPhoneNumber = matcher.group("phoneNumber");
        String stringPassedUnitsNumber = matcher.group("passedUnitsNumber");
        MenuMassages massage = Controller.checkEditProfile(year, stringPhoneNumber, stringPassedUnitsNumber);
        System.out.println(massage.output);
    }

    // Regex: -show department list
    // Output sample: 30. <Course name> - <Course code> - <Course lecturer> - Time :
    // 7:30 to 9:00 - Day : Sunday / Tuesday
    public static void showDepartmentList() {
        System.out.println("Department List is :");
        int i = 1;
        for (CourseByCode course : DB.getAllCoursesSequential()) {
            System.out.println(
                    i + ". " + course.getName() + " - " + course.getCode() + " - " + course.getTeacher().getName()
                            + " - Time : " + course.getTimes().time + " - Day : " + course.getDayes().days);
            i++;
        }
    }

    // Regex: -select <Course code>
    public static void selectCourse(Matcher matcher) {
        String courseCode = matcher.group("courseCode");
        MenuMassages massage = Controller.checkSelectCourse(courseCode);
        System.out.println(massage.output);
    }

    public static void showMyList() {
        if (DB.getLoggedInStudent() == null) {
            System.out.println(MenuMassages.INVALID_COMMAND.output);
            return;
        }
        if (Controller.selectedCourses().size() == 0)
            System.out.println("List is empty !");
        else {
            System.out.println("My List is :");
            int i = 1;
            for (CourseByCode course : Controller.selectedCourses()) {
                System.out.println(i + ". " + course.getName() + " - " + course.getCode());
                i++;
            }
        }

    }

    public static void deleteCourse(Matcher matcher) {
        String courseCode = matcher.group("courseCode");
        MenuMassages massage = Controller.checkDeleteCourse(courseCode);
        System.out.println(massage.output);
    }

    public static void showProfile() {
        if (DB.getLoggedInStudent() == null) {
            System.out.println(MenuMassages.INVALID_COMMAND.output);
            return;
        }
        Student student = DB.getLoggedInStudent();
        if (!student.isCompletedProfile())
            System.out.println(MenuMassages.PLEASE_EDIT_PROFILE.output);
        else {
            System.out.println("First name : " + student.getFirstName());
            System.out.println("Last name : " + student.getLastName());
            System.out.println("Student ID : " + student.getCode());
            System.out.println("Entrance year : " + student.getYear());
            System.out.println("Phone number : " + student.getPhoneNumber());
            System.out.println("Passed units : " + student.getPasdUnitsNumber());
            int unitsLimit = student.getMaxUnitsNumberInASemester() - student.getUnitsNumberSelected();
            System.out.println("Units limit : " + unitsLimit);
            System.out.println("Courses number : " + student.getCourses().size());
        }
    }

    public static void selectSeveralCourses(Matcher matcher) {
        if (DB.getLoggedInStudent() == null) {
            System.out.println(MenuMassages.INVALID_COMMAND.output);
            return;
        }
        String coursesCodes = matcher.group("coursesCodes");
        Student student = DB.getLoggedInStudent();
        if (!student.isCompletedProfile()) {
            System.out.println(MenuMassages.PLEASE_EDIT_PROFILE.output);
            return;
        }
        if (student.getUnitsNumberSelected() == 20) {
            System.out.println("You have selected 20 units already !");
            return;
        }
        // System.out.println("Course <Code> added successfully !");
        ArrayList<String> invalidCodes = new ArrayList<>();
        ArrayList<String> unitLimitCodes = new ArrayList<>();
        ArrayList<String> sameCourseCodes = new ArrayList<>();
        ArrayList<String> sameTimeCodes = new ArrayList<>();
        ArrayList<String> courseSelected = new ArrayList<>();

        String[] splitedCoursesCodes = coursesCodes.split("\\s+");
        MenuMassages massage;
        for (String courseCode : splitedCoursesCodes) {
            massage = Controller.checkSelectCourse(courseCode);
            if (massage == MenuMassages.INVALID_CODE)
                invalidCodes.add(courseCode);
            else if (massage == MenuMassages.UNITS_LIMIT_ERROR)
                unitLimitCodes.add(courseCode);
            else if (massage == MenuMassages.SAME_COURSE_ERROR)
                sameCourseCodes.add(courseCode);
            else if (massage == MenuMassages.SAME_TIME_ERROR)
                sameTimeCodes.add(courseCode);
            else if (massage == MenuMassages.COURSE_ADD_SUCCES)
                courseSelected.add(courseCode);
        }
        for (String code : courseSelected) {
            System.out.println("Course " + code + " added successfully !");
        }
        if (invalidCodes.size() != 0) {
            System.out.print("Invalid Codes ->");
            for (String code : invalidCodes) {
                System.out.print(" " + code);
            }
            System.out.print("\n");
        }
        if (unitLimitCodes.size() != 0) {
            System.out.print("Unit Limit Codes ->");
            for (String code : unitLimitCodes) {
                System.out.print(" " + code);
            }
            System.out.print("\n");
        }
        if (sameCourseCodes.size() != 0) {
            System.out.print("Same Course Codes ->");
            for (String code : sameCourseCodes) {
                System.out.print(" " + code);
            }
            System.out.print("\n");
        }
        if (sameTimeCodes.size() != 0) {
            System.out.print("Same Time Codes ->");
            for (String code : sameTimeCodes) {
                System.out.print(" " + code);
            }
            System.out.print("\n");
        }

    }
}
