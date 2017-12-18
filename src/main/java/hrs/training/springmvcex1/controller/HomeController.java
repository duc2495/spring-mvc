package hrs.training.springmvcex1.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hrs.training.springmvcex1.model.Customer;
import hrs.training.springmvcex1.service.CustomerService;

@Controller
public class HomeController {
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = { "/", "/homepage" })
	public ModelAndView listCustomer(ModelAndView model) throws IOException {
		List<Customer> listCustomer = customerService.findAll();
		model.addObject("listCustomer", listCustomer);
		model.setViewName("homePage");
		return model;
	}
}
