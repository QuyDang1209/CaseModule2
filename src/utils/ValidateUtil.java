package utils;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ValidateUtil {
    public static final String REGEX_USERNAME = "^[A-Za-z][A-Za-z0-9_]{7,19}$";
    public static final String REGEX_EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$";
    public static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
    public static final String AGE_REGEX = "^(1[0-9]|[1-9][0-9])$";


    public static final String FIELD_NAME = "tên";
    public static final String FIELD_NAME_MESSAGE = "Tên phải bắt đầu là kí tự, có từ 8-20 kí tự";

    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_PASSWORD_MESSAGE = "Password phải tôí thiểu 8 kí tự và không chứa kí tự đặc biệt";
    public static final String FIELD_EMAIL_MESSAGE = "Địa chỉ email phải hợp lệ theo mẫu aaa@aaa.aaa";
    public static final String FIELD_EMAIL = "Email";
    public static final String FIELD_AGE = "tuổi";
    public static final String FIELD_AGE_MESSAGE = "Tuổi phải từ 10-99";
    public static boolean isUserNameValid(String username) {
        return  Pattern.matches(REGEX_USERNAME, username);
    }
    public static boolean isEmailValid(String email) {
        return  Pattern.matches(REGEX_EMAIL, email);
    }
    public static boolean isPasswordValid(String password) {
        return Pattern.matches(PASSWORD_REGEX, password);
    }

    public static boolean isAgeValid(String age) {
        return Pattern.matches(AGE_REGEX, age);
    }
    public static boolean isValid(String pattern, String strInput) {
        return Pattern.matches(pattern, strInput);
    }

}
