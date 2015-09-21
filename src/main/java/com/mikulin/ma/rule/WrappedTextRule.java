package com.mikulin.ma.rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Rule for the next case: 
 * - Text wrapped in ** … ** becomes strong (**lorem** – > <strong>lorem</strong>) 
 * - Text wrapped in * … * becomes emphasized (*lorem* – > <em>lorem</em>
 * 
 */
public class WrappedTextRule implements Rule {
	private Pattern patternStrongCase = Pattern.compile("(.*)(\\*\\*)(.*)(\\*\\*)");
	private Pattern patternEmpCase = Pattern.compile("(.*)(\\*)(.*)(\\*)(.*)");

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mikulin.ma.rule.Rule#apply(java.lang.String[])
	 */
	@Override
	public void apply(String[] linesArray) {
		for (int i = 0; i < linesArray.length; i++) {
			String newLine = applyStrong(linesArray[i]);
			newLine = applyEmphasized(newLine);
			linesArray[i] = newLine;
		}

	}

	private String applyStrong(String input) {
		Matcher matcher = patternStrongCase.matcher(input);
		if (matcher.matches()) {
			StringBuilder sb = new StringBuilder();
			sb.append(matcher.group(1)).append("<strong>").append(matcher.group(3)).append("</strong>");
			return applyStrong(sb.toString());
		} else {
			return input;
		}
	}

	private String applyEmphasized(String input) {
		Matcher matcher = patternEmpCase.matcher(input);
		if (matcher.matches()) {
			StringBuilder sb = new StringBuilder();
			sb.append(matcher.group(1)).append("<em>").append(matcher.group(3)).append("</em>")
					.append(matcher.group(5));
			return applyEmphasized(sb.toString());
		} else {
			return input;
		}
	}

}
