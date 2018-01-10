package hrs.training.springmvcex1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hrs.training.springmvcex1.model.Language;
import hrs.training.springmvcex1.service.LanguageService;
import hrs.training.springmvcex1.validator.LanguageValidator;

@Controller
public class LanguageController {

	@Autowired
	private LanguageService languageService;
	@Autowired
	private LanguageValidator languageValidator;

	// Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {

		// Form mục tiêu
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}

		if (target.getClass() == Language.class) {
			dataBinder.setValidator(languageValidator);
		}
	}

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
		// Nếu validate có lỗi.
		if (result.hasErrors()) {
			model.setViewName("languageForm");
			return model;
		} else {
			// Add message to flash scope
			redirectAttributes.addFlashAttribute("css", "success");
			if (language.isNew()) {
				redirectAttributes.addFlashAttribute("msg", "言語を追加しました！");
			} else {
				redirectAttributes.addFlashAttribute("msg", "言語を編集しました！");
			}

			languageService.saveOrUpdate(language);
			model.setViewName("redirect:/languages");
			return model;
		}
	}

	@RequestMapping(value = "/languages/{id}/update", method = RequestMethod.GET)
	public ModelAndView showUpdateLanguageForm(@PathVariable("id") int id, ModelAndView model) {

		Language language = languageService.getLanguageById(id);
		model.addObject("language", language);
		model.setViewName("languageForm");
		return model;

	}

	@RequestMapping(value = "/languages", method = RequestMethod.GET)
	public ModelAndView showAllLanguages(ModelAndView model) {
		model.setViewName("languagePage");
		model.addObject("listLanguage", languageService.findAll());
		return model;
	}
}
