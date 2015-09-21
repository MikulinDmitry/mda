package com.mikulin.ma;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mikulin.ma.AuthUtils;

/**
 * Tests for AuthUtils class.
 *
 */
public class AuthUtilsTest {

	@Test
	public void testGetUsername() throws Exception {
		assertEquals("tf", AuthUtils.getUsername("Basic dGY6MQ=="));
	}

}
