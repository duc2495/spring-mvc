package hrs.training.springmvcex1.dao;

import java.util.List;

import hrs.training.springmvcex1.model.Language;

public interface LanguageDAO {
	void insert(Language language);
	List<Language> listAll();
}
