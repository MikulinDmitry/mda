package com.mikulin.ma.rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
//[diam](http://mysite.com)
//<a href=”http://mysite.com”>diam</a>

public class HrefRule implements Rule{
	@Override
	public void apply(String[] linesArray) {
		for (int i=0; i<linesArray.length; i++)
		{
			//check for href
			linesArray[i]= processHref(linesArray[i]);
		}

		
	}

	//TODO rename
	private String processHref(String input) {
		Pattern pattern = Pattern.compile("(.*)\\[(diam)\\]\\(([a-zA-Z.:/-]*)\\)(.*)");
		Matcher matcher = pattern.matcher(input);
		if (matcher.matches()) {
			StringBuilder sb = new StringBuilder();
			sb.append(matcher.group(1)).append("<a href=\"").append(matcher.group(3)).append("\">")
					.append(matcher.group(2)).append("</a>").append(matcher.group(4));
			return processHref(sb.toString());
		} else {
			return input;
		}
	}

}
