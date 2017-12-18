package hrs.training.springmvcex1.model;

import org.springframework.core.convert.converter.Converter;

public class LanguageToString implements Converter<Language, String> {
	@Override
	public String convert(Language source) {
		return source.getId() + "|" + source.getName();
	}
}
