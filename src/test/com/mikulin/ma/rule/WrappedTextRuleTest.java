package com.mikulin.ma.rule;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

/**
 * Tests for WrappedTextRule.
 *
 */
public class WrappedTextRuleTest {
	@Test
	public void testApply() throws Exception {

		WrappedTextRule rule = new WrappedTextRule();
		String[] input = new String[] { "**foo**", "**foo1** additional text", "*foo2*", "*foo1* aa", "just a text",
				"*a* **cdef**" };
		String[] expectedResult = new String[] { "<strong>foo</strong>", "<em></em>foo1<em></em> additional text",
				"<em>foo2</em>", "<em>foo1</em> aa", "just a text", "<em>a</em> <strong>cdef</strong>" };

		rule.apply(input);
		assertArrayEquals(expectedResult, input);
	}

}
