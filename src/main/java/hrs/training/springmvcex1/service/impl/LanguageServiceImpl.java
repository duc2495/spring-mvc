package hrs.training.springmvcex1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrs.training.springmvcex1.dao.LanguageDAO;
import hrs.training.springmvcex1.model.Language;
import hrs.training.springmvcex1.service.LanguageService;

@Service("languageService")
public class LanguageServiceImpl implements LanguageService {

	LanguageDAO languageDAO;

	@Autowired
	public void setLanguageDao(LanguageDAO languageDAO) {
		this.languageDAO = languageDAO;
	}

	@Override
	public void saveOrUpdate(Language language) {
		if (getLanguageById(language.getId()) == null) {
			languageDAO.insert(language);
		} else {
			languageDAO.update(language);
		}
	}

	@Override
	public Language getLanguageById(int id) {
		return languageDAO.getLanguageById(id);
	}

	@Override
	public List<Language> findAll() {
		return languageDAO.findAll();
	}

}
