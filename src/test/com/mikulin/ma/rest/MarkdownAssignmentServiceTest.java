package com.mikulin.ma.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Test suite for MarkdownAssignmentService.
 *
 */
public class MarkdownAssignmentServiceTest {

	@Test
	public void testProcess_null() throws Exception {
		MarkdownAssignmentService mainService = new MarkdownAssignmentService();
		String actualResult = mainService.transform(null);
		assertNull(actualResult);
	}

	@Test
	public void testProcess_simple() throws Exception {
		MarkdownAssignmentService mainService = new MarkdownAssignmentService();
		String input = "# Lorem ipsum\n";
		String expectedResult = "<html>\n<body>\n<h1> Lorem ipsum</h1>\n</body>\n</html>";
		String actualResult = mainService.transform(input);
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testProcess_simple2() throws Exception {
		MarkdownAssignmentService mainService = new MarkdownAssignmentService();
		String input = "# Lorem ipsum\r\n";
		String expectedResult = "<html>\n<body>\n<h1> Lorem ipsum</h1>\n</body>\n</html>";
		String actualResult = mainService.transform(input);
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testProcess_complex() throws Exception {
		MarkdownAssignmentService mainService = new MarkdownAssignmentService();
		String input = "# Lorem ipsum\r\nDolor sit amet,\r\nconsetetur *sadipscing* elitr,\r\nsed [diam](http://mysite.com) nonumy eirmod tempor";
		String expectedResult = "<html>\n<body>\n<h1> Lorem ipsum</h1>\n<p>Dolor sit amet,</p>\n<p>consetetur <em>sadipscing</em> elitr,</p>\n<p>sed <a href=\"http://mysite.com\">diam</a> nonumy eirmod tempor</p>\n</body>\n</html>";
		String actualResult = mainService.transform(input);
		assertEquals(expectedResult, actualResult);
	}

}
