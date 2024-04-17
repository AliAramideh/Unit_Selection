package view.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MenuCommands {
    GET_COURSE(
            "(?<courseName>[^,]+)\\,(?<courseUnits>\\d{1,2})\\,(?<teacher>[^,]+)\\,(?<day>[12])\\,(?<time>[123])\\,(?<courseCode>\\d+)\\s*"),
    END("-end"),
    // -signin <Firstname> <Lastname> <Code>
    SIGNIN("-signin\\s+(?<firstName>[A-Za-z]+)\\s+(?<lastName>[A-Za-z]+)\\s+(?<code>\\w+)\\s*"),
    // -login <Firstname> <Lastname> <Code>
    LOGIN("-login\\s+(?<firstName>[A-Za-z]+)\\s+(?<lastName>[A-Za-z]+)\\s+(?<code>\\w+)\\s*"),
    LOGOUT("-logout"),
    // -edit profile <Year> <Phone number> <Passed units number>
    EDIT_PROFILE("-edit\\s+profile\\s+(?<year>\\w+)\\s+(?<phoneNumber>\\w+)\\s+(?<passedUnitsNumber>\\w+)\\s*"),
    SHOW_DEPARTMENT_LIST("-show\\s+department\\s+list\\s*"),
    SHOW_MY_LIST("-show\\s+my\\s+list\\s*"),
    // -select <Course code>
    SELECT_COURSE("-select\\s+(?<courseCode>\\w+)\\s*"),
    DELETE_COURSE("-delete\\s+course\\s+(?<courseCode>\\w+)\\s*"),
    SHOW_PROFILE("-show\\s+profile\\s*"),
    SELECT_SEVERAL_COURSES("-select\\s+all\\s+(?<coursesCodes>[\\d\\s]+)\\s*");

    public String regex;

    MenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, MenuCommands mainRegex) {
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(input);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
