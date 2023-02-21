package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import command.CommentCommand;

public class CommentCommandValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CommentCommand.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comment_content", "required");
	}
	
}
