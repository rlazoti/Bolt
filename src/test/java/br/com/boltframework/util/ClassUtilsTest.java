package br.com.boltframework.util;

import org.junit.Test;
import static org.junit.Assert.*;
import br.com.boltframework.error.BoltException;

public class ClassUtilsTest {

	@Test
	public void testGetClassOfClassNameWithInvalidClass() {
		try {
			ClassUtils.getClassOfClassName("xpto.Lorem");
			assertTrue(false);
		}
		catch (BoltException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testGetClassOfClassNameWithEmptyClassname() {
		try {
			ClassUtils.getClassOfClassName("");
			assertTrue(false);
		}
		catch (BoltException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testGetClassOfClassNameWithValidClass() {
		try {
			Class<?> clazz = ClassUtils.getClassOfClassName("java.lang.String");
			assertTrue(clazz != null);
		}
		catch (BoltException e) {
			assertTrue(false);
		}
	}

	@Test
	public void testCreateClassInstanceWithInvalidClass() {
		try {
			ClassUtils.createClassInstance("xpto.Lorem");
			assertTrue(false);
		}
		catch (BoltException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testCreateClassInstanceWithEmptyClassname() {
		try {
			ClassUtils.createClassInstance("");
			assertTrue(false);
		}
		catch (BoltException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testCreateClassInstanceWithValidClass() {
		try {
			Object instance = ClassUtils.createClassInstance("java.lang.String");
			assertTrue(instance != null && instance instanceof String);
		}
		catch (BoltException e) {
			assertTrue(false);
		}
	}

	@Test
	public void testClassExistsWithInvalidClass() {
		assertFalse(ClassUtils.classExists("xpto.Lorem"));
	}

	@Test
	public void testClassExistsWithEmptyClassname() {
		assertFalse(ClassUtils.classExists(""));
	}

	@Test
	public void testClassExistsWithValidClass() {
		assertTrue(ClassUtils.classExists("java.lang.String"));
	}

}
