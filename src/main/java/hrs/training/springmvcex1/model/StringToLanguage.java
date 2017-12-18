package hrs.training.springmvcex1.model;

import org.springframework.core.convert.converter.Converter;

public class StringToLanguage implements Converter<String, Language> {
	@Override
	public Language convert(String source) {
		String[] split = source.split("[|]");
		Language language = new Language();
		language.setId(Integer.parseInt(split[0]));
		language.setName(split[1]);
		return language;
	}
}
