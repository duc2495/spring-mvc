package hrs.training.springmvcex1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import hrs.training.springmvcex1.model.Language;

@Service("languageService")
public interface LanguageService {
	void insert(Language language);

	List<Language> listAll();
}
