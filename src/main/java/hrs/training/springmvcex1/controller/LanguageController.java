package hrs.training.springmvcex1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hrs.training.springmvcex1.model.Language;
import hrs.training.springmvcex1.service.LanguageService;

@Controller
public class LanguageController {

	@Autowired
	private LanguageService languageService;

	@RequestMapping(value = "/newlanguage", method = RequestMethod.GET)
	public ModelAndView newLanguage(ModelAndView model) {
		Language newLanguage = new Language();
		model.addObject("language", newLanguage);
		model.setViewName("languageForm");
		return model;
	}

	@RequestMapping(value = "/savelanguage", method = RequestMethod.POST)
	public ModelAndView saveLanguage(ModelAndView model, @ModelAttribute("language") @Validated Language language,
			BindingResult result, final RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Language added successfully!");
		languageService.insert(language);
		model.setViewName("redirect:/admin");
		return model;
	}
}
