package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import command.ChangePasswordCommand;

public class ChangePasswordCommandValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ChangePasswordCommand.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword", "required");
	}
	
}
