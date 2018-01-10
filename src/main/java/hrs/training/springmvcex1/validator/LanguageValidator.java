package hrs.training.springmvcex1.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import hrs.training.springmvcex1.model.Language;

@Component
public class LanguageValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == Language.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Language language = (Language) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.language.name");
		if (language.getName().length() > 30) {
			errors.rejectValue("name", "TooLong.language.name");
		}
	}
	
}
