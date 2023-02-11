package validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import command.RegisterCommand;

public class RegisterCommandValidator implements Validator {

	private static final String emailRegExp = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
			"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private static final String birthdateRegExp =
			"^(19[0-9][0-9]|20\\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$";
	
	private Pattern email_pattern;
	private Pattern birthdate_pattern;
	
	public RegisterCommandValidator() {
		email_pattern = Pattern.compile(emailRegExp);
		birthdate_pattern = Pattern.compile(birthdateRegExp);
	}
	
	// clazz 객체가 RegisterCommand로 변환 가능한 지 확인 - Spring MVC가 자동 검증
	@Override
	public boolean supports(Class<?> clazz) {
		return RegisterCommand.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		RegisterCommand regCommand = (RegisterCommand)target;
		
		if(regCommand.getEmail() == null || regCommand.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "required");
		} else {
			Matcher matcher = email_pattern.matcher(regCommand.getEmail());
			if(!matcher.matches()) {
				errors.rejectValue("email", "wrong");
			}
		}
		
		if(regCommand.getBirthdate() == null || regCommand.getBirthdate().trim().isEmpty()) {
			errors.rejectValue("birthdate", "required");
		} else {
			Matcher matcher = birthdate_pattern.matcher(regCommand.getBirthdate());
			if(!matcher.matches()) {
				errors.rejectValue("birthdate", "wrong");
			}
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "required");
		
		if(!regCommand.getPassword().isEmpty()) {
			if(!regCommand.isPasswordEqualToConfirmPassword()) {
				errors.rejectValue("confirmPassword", "nomatch");
			}
		}
		
	}
	
}
