package tran.example.smartcartest.model.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import tran.example.smartcartest.model.form.RegistrationForm;

import static tran.example.smartcartest.utility.constants.validator.ValidatorConstants.*;

@Component
public class RegistrationFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegistrationForm registrationForm = (RegistrationForm) o;
        validateUsername(registrationForm, errors);
        validatePasswords(registrationForm, errors);
    }

    private void validateUsername(RegistrationForm registrationForm, Errors errors) {
        String username = registrationForm.getUsername();
        if(username == null) {
            errors.rejectValue(USERNAME_INPUT, USERNAME_ERROR_KEY, USERNAME_EMPTY_OR_NULL_MESSAGE);
        } else if(username.length() < MIN_USERNAME_LENGTH || username.length() > MAX_USERNAME_LENGTH) {
            errors.rejectValue(USERNAME_INPUT, USERNAME_LENGTH_KEY, USERNAME_INCORRECT_LENGTH_MESSAGE);
        }
    }

    private void validatePasswords(RegistrationForm registrationForm, Errors errors) {
        /*
         * for now I am not using regular expressions to make sure a password has 1 lower case letter, 1 uppercase,
         * 1 number, 1 letter etc.
         */
        String password = registrationForm.getPassword();
        String confirmPassword = registrationForm.getConfirmPassword();
        if(password == null || confirmPassword== null || password.length() == 0 || confirmPassword.length() == 0) {
            errors.rejectValue(CONFIRM_PASSWORD_INPUT, NULL_PASSWORD_KEY, EMPTY_OR_NULL_PASSWORDS_MESSAGE);
        }
        else if(!registrationForm.getPassword().equals(registrationForm.getConfirmPassword())) {
            errors.rejectValue(CONFIRM_PASSWORD_INPUT, PASSWORDS_MISMATCH_KEY, PASSWORDS_MISMATCH_MESSAGE);
        }
    }
}
