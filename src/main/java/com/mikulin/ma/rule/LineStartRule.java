package com.mikulin.ma.rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Line start rule.
 * Lines starting with #, ##, ### etc. become headers <h1/>, <h2/>, <h3/> etc.
 * Simple lines become paragraphs (just a line – > <p>just a line</p>).
 */
public class LineStartRule implements Rule{
	@Override
	public void apply(String[] linesArray) {
		for (int i=0; i<linesArray.length; i++)
		{
			String line = linesArray[i];
			Pattern pattern = Pattern.compile("^(#{1,}).*");
			Matcher matcher = pattern.matcher(line);
			StringBuilder sb = new StringBuilder();
		    if (matcher.matches()) {
		    	int count = matcher.group(1).length();
		    	sb.append("<h").append(count).append(">");
		    	sb.append(line.substring(count));
		    	sb.append("</h").append(count).append(">");
		    }
		    else {
		    	sb.append("<p>").append(line).append("</p>");
		    }
		    linesArray[i] = sb.toString();
		}
		
	}

}
