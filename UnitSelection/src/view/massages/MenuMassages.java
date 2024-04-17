package view.massages;

public enum MenuMassages {
    INVALID_COMMAND("Invalid command !"),
    INCORRECT_FISRST_NAME("First name first character should be uppercase !"),
    INCORRECT_LAST_NAME("Last name first character should be uppercase !"),
    INCORRECT_CODE("The ID number contains characters other than numbers or is too short !"),
    STUDENT_EXISTS("A student with this name exist !"),
    SIGNIN_SUCCES("Student with name "),
    STUDENT_DOES_NOT_EXIST("There is no student with this name and ID !"),
    LOGIN_SUCCES("Welcome"),
    LOGOUT_SUCCES("Logged out successfully !"),
    YEAR_MUST_BE_A_NUMBER("Year must be a number !"),
    YEAR_MUST_BE_1401_OR_1402("Year must be 1401 or 1402 !"),
    INCORRECT_PHONE_NUMBER("Phone must be a number and it's length should be 10 !"),
    PASSED_UNITS_MUST_BE_A_NUMBER("Units must be a number !"),
    INCORRECT_PASSED_UNITS_NUMBER("Units must be between 10 and 90 !"),
    EDIT_PROFILE_SUCCES("Profile edited successfully !"),
    INVALID_CODE("Invalid Code !"),
    PLEASE_EDIT_PROFILE("Please first edit your profile !"),
    UNITS_LIMIT_ERROR("Unit Limits Error !"),
    SAME_COURSE_ERROR("Same Course Code Error !"),
    SAME_TIME_ERROR("Same Time Error !"),
    COURSE_ADD_SUCCES("Course added successfully !"),
    COURSE_DOES_NOT_EXIST("Course with this code does not exist in your list !"),
    COURSE_DELETED("Course deleted successfully !");

    public String output;

    MenuMassages(String output) {
        this.output = output;
    }
}
