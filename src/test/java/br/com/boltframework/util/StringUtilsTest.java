package br.com.boltframework.util;

import static org.junit.Assert.*;
import org.junit.Test;

public class StringUtilsTest {

	static final String WHITESPACE;

	static {
		String ws = "";

		for (int i = 0; i < Character.MAX_VALUE; i++) {
			if (Character.isWhitespace((char) i)) {
				ws += String.valueOf((char) i);
			}
		}

		WHITESPACE = ws;
	}

	@Test
	public void testIsBlank() {
		assertEquals(true, StringUtils.isBlank(null));
		assertEquals(true, StringUtils.isBlank(""));
		assertEquals(true, StringUtils.isBlank(StringUtilsTest.WHITESPACE));
		assertEquals(false, StringUtils.isBlank("foo"));
		assertEquals(false, StringUtils.isBlank("  foo  "));
	}

	@Test
	public void testIsNotBlank() {
		assertEquals(false, StringUtils.isNotBlank(null));
		assertEquals(false, StringUtils.isNotBlank(""));
		assertEquals(false, StringUtils.isNotBlank(StringUtilsTest.WHITESPACE));
		assertEquals(true, StringUtils.isNotBlank("foo"));
		assertEquals(true, StringUtils.isNotBlank("  foo  "));
	}

	@Test
	public void testIsNumeric() {
		assertEquals(false, StringUtils.isNumeric(null));
		assertEquals(true, StringUtils.isNumeric(""));
		assertEquals(false, StringUtils.isNumeric(" "));
		assertEquals(false, StringUtils.isNumeric("a"));
		assertEquals(false, StringUtils.isNumeric("A"));
		assertEquals(false, StringUtils.isNumeric("kgKgKgKgkgkGkjkjlJlOKLgHdGdHgl"));
		assertEquals(false, StringUtils.isNumeric("ham kso"));
		assertEquals(true, StringUtils.isNumeric("1"));
		assertEquals(true, StringUtils.isNumeric("1000"));
		assertEquals(false, StringUtils.isNumeric("2.3"));
		assertEquals(false, StringUtils.isNumeric("10 00"));
		assertEquals(false, StringUtils.isNumeric("hkHKHik6iUGHKJgU7tUJgKJGI87GIkug"));
		assertEquals(false, StringUtils.isNumeric("_"));
		assertEquals(false, StringUtils.isNumeric("hkHKHik*khbkuh"));
	}

}