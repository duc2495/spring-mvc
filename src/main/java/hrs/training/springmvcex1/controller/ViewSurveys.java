package hrs.training.springmvcex1.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hrs.training.springmvcex1.model.Customer;
import hrs.training.springmvcex1.service.CustomerService;
import hrs.training.springmvcex1.service.LanguageService;

@Controller
public class ViewSurveys {
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private LanguageService languageService;

	@RequestMapping(value = { "/viewsurveys" })
	public ModelAndView listCustomer(ModelAndView model, Integer offset) throws IOException {

		Integer maxResult = 15;
		List<Customer> listCustomer = customerService.getCustomersByPage(offset, maxResult);
		model.addObject("listCustomer", listCustomer);
		model.addObject("count", customerService.count());
		model.addObject("offset", offset);
		model.setViewName("viewSurveys");
		return model;
	}
}