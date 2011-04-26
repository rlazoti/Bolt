package br.com.boltframework.scan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.com.boltframework.annotation.Controller;
import br.com.boltframework.core.ControllerMapping;
import br.com.boltframework.scan.ClassScanner.Builder;
import br.com.boltframework.scan.classes.PersonController;
import br.com.boltframework.scan.classes.User;
import br.com.boltframework.scan.classes.UserController;
import br.com.boltframework.util.Constants;

public class ClassScannerTest {

	private ClassScanner classScanner = ClassScanner.createInstance().withDefaultDirectoryClasses();

	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testIsControllerWithValidClass() {
		Class clazz = UserController.class;
		boolean annotated = classScanner.isAnnotatedWithController(clazz);
		assertTrue(annotated);
	}

	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testIsControllerWithoutValidClass() {
		Class clazz = User.class;
		boolean annotated = classScanner.isAnnotatedWithController(clazz);
		assertFalse(annotated);
	}

	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testObtainControllerAnnotationByClassWithValidClass() {
		Class clazz = UserController.class;
		Controller controller = classScanner.obtainControllerAnnotationByClass(clazz);
		assertNotNull(controller);
		assertEquals("user", controller.mappedBy());
	}

	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testObtainControllerAnnotationByClassWithoutValidClass() {
		Class clazz = User.class;
		Controller controller = classScanner.obtainControllerAnnotationByClass(clazz);
		assertNull(controller);
	}

	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testGetActionsByClassIfIsNotNull() {
		Class clazz = UserController.class;
		List<ControllerMapping> mappings = classScanner.getActionsByClass(clazz, "user");
		assertNotNull(mappings);
	}

	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testGetActionsByClassIfHasValidSize() {
		Class clazz = UserController.class;
		List<ControllerMapping> mappings = classScanner.getActionsByClass(clazz, "user");
		assertEquals(7, mappings.size());
	}

	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testGetActionsByClassIfHasValidMethodForRunBeforeAllActions() {
		Class clazz = UserController.class;
		List<ControllerMapping> mappings = classScanner.getActionsByClass(clazz, "user");
		for (ControllerMapping mapping : mappings) {
			assertNotNull(mapping.getRunBeforeAction());
		}
	}

	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testGetActionsByClassIfHasValidMethodForRunBeforeSomeActions() {
		Class clazz = PersonController.class;
		List<ControllerMapping> mappings = classScanner.getActionsByClass(clazz, "user");
		for (ControllerMapping mapping : mappings) {
			String action = mapping.getActionName();
			if ("save".equals(action) || "update".equals(action) || "delete".equals(action)) {
				assertNotNull(mapping.getRunBeforeAction());
			}
			else {
				assertNull(mapping.getRunBeforeAction());
			}
		}
	}

	@Test
	public void testCreateInstance() {
		Builder builder = ClassScanner.createInstance();
		assertNotNull(builder);
	}

	@Test
	public void testCreateInstanceWithDefaultDirectoryClasses() {
		ClassScanner instance = ClassScanner.createInstance().withDefaultDirectoryClasses();
		assertNotNull(instance);
	}

	@Test
	public void testCreateInstanceWithCustomDirectoryClasses() {
		ClassScanner instance = ClassScanner.createInstance().withCustomDirectoryClasses("xpto");
		assertNotNull(instance);
	}

	@Test
	public void testGetDirectoryClassesByDefault() {
		String directory = classScanner.getDirectoryClasses();
		String defaultDirectory = Constants.DEFAULT_DIRECTORY_CLASSES;
		assertEquals(defaultDirectory, directory);
	}
}
