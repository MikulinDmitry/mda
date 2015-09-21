package com.mikulin.ma.rule;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

/**
 * Test suite for HREF rule.
 *
 */
public class HrefRuleTest {
	@Test
	public void testApply() throws Exception {
		HrefRule rule = new HrefRule();
		String[] input = new String[] { "sed [diam](http://mysite.com) nonumy eirmod tempor", "next line" };
		rule.apply(input);
		String[] expectedResult = new String[] { "sed <a href=\"http://mysite.com\">diam</a> nonumy eirmod tempor",
				"next line" };
		assertArrayEquals(expectedResult, input);

	}

}
