package hrs.training.springmvcex1.validator;

import java.util.regex.Pattern;

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
		String nameRegex = "[^ ]([\\u3000-\\u303F ]*|" + "[\\u3040-\\u309F ]*|" + "[\\u30A0-\\u30FF ]*|"
				+ "[\\uFF00-\\uFFEF ]*|" + "[\\u4E00-\\u9FAF ]*|" + "[\\u00C0-\\u1EF9 &a-zA-Z ]*|" + "[a-zA-Z ]*)";

		if(!language.getName().isEmpty() && !Pattern.matches(nameRegex, language.getName())) {
			errors.rejectValue("name", "NotMatch.language.name");
		}
	}
	
}
