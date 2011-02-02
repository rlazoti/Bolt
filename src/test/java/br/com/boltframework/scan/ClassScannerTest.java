package br.com.boltframework.scan;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.boltframework.annotation.Controller;

public class ClassScannerTest {

  private ClassScanner classScanner = ClassScanner.createInstance().withDefaultDirectoryClasses();

  public class User {
  };

  @Controller(mappedBy = "test")
  public class UserController {
  };

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

}
