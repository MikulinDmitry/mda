package com.mikulin.ma;


import java.util.Arrays;
import java.util.List;

import com.mikulin.ma.rule.HrefRule;
import com.mikulin.ma.rule.LineStartRule;
import com.mikulin.ma.rule.Rule;
import com.mikulin.ma.rule.WrappedTextRule;

public class HTMLTransformer {

	private static String WINDOWS_LINE_SEPARATOR = "\r\n";
	private static String COMMON_LINE_SEPARATOR = "\n";
	private static List<Rule> RULES = Arrays.asList(new LineStartRule(), new WrappedTextRule(), new HrefRule());

	/**
	 * Tansform input according to the existed rules.
	 * @param input 
	 * @return
	 */
	public static String transform(String input) {
		if (input == null) {
			return null;
		}
		
		String normalizedInput = input.replaceAll(WINDOWS_LINE_SEPARATOR, COMMON_LINE_SEPARATOR);
		String[] linesArray = normalizedInput.split(COMMON_LINE_SEPARATOR);
		for (Rule someRule : RULES) {
			someRule.apply(linesArray);
		}

		StringBuilder result = new StringBuilder("<html>");
		result.append(COMMON_LINE_SEPARATOR).append("<body>").append(COMMON_LINE_SEPARATOR);
		for (String line : linesArray) {
			result.append(line).append(COMMON_LINE_SEPARATOR);
		}
		result.append("</body>").append(COMMON_LINE_SEPARATOR).append("</html>");
		return result.toString();

	}

}
