package hrs.training.springmvcex1.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import hrs.training.springmvcex1.dao.CustomerDAO;
import hrs.training.springmvcex1.model.Customer;

@Controller
public class HomeController {
    @Autowired
    private CustomerDAO customerDAO;
    
    @RequestMapping(value= { "/", "/homepage" })
    public ModelAndView listCustomer(ModelAndView model) throws IOException{
        List<Customer> listCustomer = customerDAO.list();
        model.addObject("listCustomer", listCustomer);
        model.setViewName("homePage");
     
        return model;
    }
    
    @RequestMapping(value = "/newSurvey", method = RequestMethod.GET)
    public ModelAndView newSurvey(ModelAndView model) {
    	Customer newCustomer = new Customer();
        model.addObject("customer", newCustomer);
        model.setViewName("surveyForm");
        return model;
    }

    
    @RequestMapping(value = "/saveSurvey", method = RequestMethod.POST)
    public ModelAndView saveSurvey(@ModelAttribute Customer customer) {
        customerDAO.insert(customer);
        return new ModelAndView("redirect:/thankPage");
    }
    
    @RequestMapping(value = "/thankPage", method = RequestMethod.GET)
    public ModelAndView thankPage(ModelAndView model) {
        model.setViewName("thankPage");
        return model;
    }

}
