package hrs.training.springmvcex1.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import hrs.training.springmvcex1.model.Customer;

@Component
public class CustomerValidator implements Validator {
	// Các class được hỗ trợ Validate
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == Customer.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Customer customer = (Customer) target;

		// Kiểm tra các field của Customer.
		// (Xem thêm file property: messages/validator.property)
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.customer.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", "NotEmpty.customer.birthday");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.customer.address");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.customer.gender");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "school", "NotEmpty.customer.school");

		if (customer.getSchoolYear() <= 0 || customer.getSchoolYear() > 6) {
			errors.rejectValue("schoolYear", "Select.customer.schoolYear");
		}

		if (customer.getName().length() > 100) {
			errors.rejectValue("name", "TooLong.customer.name");
		}

		if (customer.getAddress().length() > 100) {
			errors.rejectValue("address", "TooLong.customer.address");
		}

		if (customer.getSchool().length() > 100) {
			errors.rejectValue("school", "TooLong.customer.school");
		}

	}
}
