package hrs.training.springmvcex1.config;

import org.springframework.core.convert.converter.Converter;

import com.google.gson.Gson;

import hrs.training.springmvcex1.model.Language;

public class LanguageToJsonString implements Converter<Language, String> {
	Gson gson = new Gson();

	@Override
	public String convert(Language source) {
		String jsonInString = gson.toJson(source);
		return jsonInString;
	}
}
