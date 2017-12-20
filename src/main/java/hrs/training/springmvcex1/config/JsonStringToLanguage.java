package hrs.training.springmvcex1.config;

import org.springframework.core.convert.converter.Converter;

import com.google.gson.Gson;

import hrs.training.springmvcex1.model.Language;

public class JsonStringToLanguage implements Converter<String, Language> {
	Gson gson = new Gson();

	@Override
	public Language convert(String source) {
		Language language = new Language();
		language = gson.fromJson(source, Language.class);
		return language;
	}
}
