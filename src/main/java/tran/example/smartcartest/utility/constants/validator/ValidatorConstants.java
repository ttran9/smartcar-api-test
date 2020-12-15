package tran.example.smartcartest.utility.constants.validator;

public class ValidatorConstants {

    public static final int MIN_USERNAME_LENGTH = 4;
    public static final int MAX_USERNAME_LENGTH = 12;

    public static final String USERNAME_INPUT = "username";
    public static final String CONFIRM_PASSWORD_INPUT = "confirmPassword";

    public static final String USERNAME_ERROR_KEY = "UsernameError";
    public static final String USERNAME_LENGTH_KEY = "UsernameLengthError";
    public static final String NULL_PASSWORD_KEY = "ErrorWithPasswords";
    public static final String PASSWORDS_MISMATCH_KEY = "PasswordsDontMatch";

    public static final String USERNAME_EMPTY_OR_NULL_MESSAGE = "Username cannot be empty";
    public static final String USERNAME_INCORRECT_LENGTH_MESSAGE = "Username must be between 4 and 12 characters";
    public static final String EMPTY_OR_NULL_PASSWORDS_MESSAGE = "Both passwords fields must be filled in!";
    public static final String PASSWORDS_MISMATCH_MESSAGE = "Passwords must match!!";
}
