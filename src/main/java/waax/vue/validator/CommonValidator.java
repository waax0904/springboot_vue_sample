package waax.vue.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommonValidator {

	final String EMPTY = "";

	public boolean isNumber(final char c) {
		return (c >= 0x0030 && c <= 0x0039);

	}

	public boolean isEmpty(String value) {
		return (value == null || EMPTY.equals(value));

	}

	public boolean isNumber(String value) {

		if (value == null) {
			return true;

		}

		char[] c = value.toCharArray();

		for (int i = 0; i < c.length; i++) {

			if (!isNumber(c[i])) {
				return false;

			}

		}

		return true;

	}

	public String checkText(String value, int maxLength) {
		String trimValue = value.trim();

		if (StringUtils.isBlank(trimValue)) {
			return "input value is empty.";

		}

		int length = trimValue.length();

		if (length > maxLength) {
			return "Input value is out of max-length(" + maxLength + " :" + value + ")";

		}

		return null;

	}
}