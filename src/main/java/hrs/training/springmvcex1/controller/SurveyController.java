package hrs.training.springmvcex1.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hrs.training.springmvcex1.model.Customer;
import hrs.training.springmvcex1.service.CustomerService;
import hrs.training.springmvcex1.validator.CustomerValidator;

@Controller
public class SurveyController {
	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerValidator customerValidator;

	// Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {

		// Form mục tiêu
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		if (target.getClass() == Customer.class) {
			dataBinder.setValidator(customerValidator);
		}
	}
	
	@RequestMapping(value = "/newSurvey", method = RequestMethod.GET)
	public ModelAndView newSurvey(ModelAndView model) {
		Customer newCustomer = new Customer();
		model.addObject("customerForm", newCustomer);
		surveyDefaultModel(model);
		model.setViewName("surveyForm");
		return model;
	}

	// 1. @ModelAttribute bind form value
	// 2. @Validated form validator
	// 3. RedirectAttributes for flash value
	@RequestMapping(value = "/saveSurvey", method = RequestMethod.POST)
	public ModelAndView saveSurvey(ModelAndView model, @ModelAttribute("customerForm") @Validated Customer customer,
			BindingResult result, final RedirectAttributes redirectAttributes) {
		// Nếu validate có lỗi.
		if (result.hasErrors()) {
			surveyDefaultModel(model);
			model.setViewName("surveyForm");
			return model;
		} else {
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Survey added successfully!");
			customerService.insert(customer);
			model.setViewName("redirect:/thankPage");
			return model;
		}
	}

	@RequestMapping(value = "/thankPage", method = RequestMethod.GET)
	public ModelAndView thankPage(ModelAndView model) {
		model.setViewName("thankPage");
		return model;
	}

	public void surveyDefaultModel(ModelAndView model) {

		Map<Integer, String> years = new LinkedHashMap<Integer, String>();
		years.put(0, "Lựa chọn");
		years.put(1, "Năm thứ nhất");
		years.put(2, "Năm thứ hai");
		years.put(3, "Năm thứ ba");
		years.put(4, "Năm thứ tư");
		years.put(5, "Năm thứ năm");
		years.put(6, "Năm thứ sáu");
		model.addObject("yearList", years);

		Map<String, String> subjects = new LinkedHashMap<String, String>();
		subjects.put("Tư tưởng", "Tư tưởng Hồ Chí Minh");
		subjects.put("Đường lối", "Đường lối cách mạng của Đảng");
		subjects.put("Triết", "Những nguyên lý của chủ nghĩa Mác Lê-nin");
		subjects.put("Pháp luật", "Pháp luật đại cương");
		model.addObject("subjectList", subjects);
	}
}
