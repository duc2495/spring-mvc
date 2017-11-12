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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.customer.address");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sex", "NotEmpty.customer.sex");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "school", "NotEmpty.customer.school");

		if (customer.getYear() <= 0) {
			errors.rejectValue("year", "Select.customer.year");
		}
		
		if (customer.getSubjects() == null || customer.getSubjects().size() < 1) {
			errors.rejectValue("subjects", "Select.customer.subjects");
		}
	}
}
