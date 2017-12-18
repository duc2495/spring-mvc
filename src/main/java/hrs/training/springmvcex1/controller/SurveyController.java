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
import hrs.training.springmvcex1.service.LanguageService;
import hrs.training.springmvcex1.validator.CustomerValidator;

@Controller
public class SurveyController {
	@Autowired
	private CustomerService customerService;

	@Autowired
	private LanguageService languageService;

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

		Map<Integer, String> schoolYearList = new LinkedHashMap<Integer, String>();
		schoolYearList.put(0, "選択");
		schoolYearList.put(1, "一年生");
		schoolYearList.put(2, "二年生");
		schoolYearList.put(3, "三年生");
		schoolYearList.put(4, "四年生");
		schoolYearList.put(5, "五年生");
		schoolYearList.put(6, "六年生");
		model.addObject("schoolYearList", schoolYearList);

		model.addObject("languages", languageService.listAll());
	}
}
