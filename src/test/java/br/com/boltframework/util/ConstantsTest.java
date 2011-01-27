package br.com.boltframework.util;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ConstantsTest {

  @Test
  public void testDefaultDirectoryClassesCantBeNull() {
    assertNotNull(Constants.DEFAULT_DIRECTORY_CLASSES);
  }

  @Test
  public void testApplicationContextCantBeNull() {
    assertNotNull(Constants.APPLICATION_CONTEXT);
  }

  @Test
  public void testClassExtensionCantBeNull() {
    assertNotNull(Constants.CLASS_EXTENSION);
  }

  @Test
  public void testConfigurationInitParameterCantBeNull() {
    assertNotNull(Constants.CONFIGURATION_INIT_PARAMETER);
  }

  @Test
  public void testDefaultErrorPageCantBeNull() {
    assertNotNull(Constants.DEFAULT_ERROR_PAGE);
  }

  @Test
  public void testDotCantBeNull() {
    assertNotNull(Constants.DOT);
  }

  @Test
  public void testEmptyStringCantBeNull() {
    assertNotNull(Constants.EMPTY_STRING);
  }

  @Test
  public void testErrorAttributeNameCantBeNull() {
    assertNotNull(Constants.ERROR_ATTRIBUTE_NAME);
  }

  @Test
  public void testForwardSlashCantBeNull() {
    assertNotNull(Constants.FORWARD_SLASH);
  }

  @Test
  public void testJspFileExtensionCantBeNull() {
    assertNotNull(Constants.JSP_FILE_EXTENSION);
  }

  @Test
  public void testLineSeparatorPropertyCantBeNull() {
    assertNotNull(Constants.LINE_SEPARATOR_PROPERTY);
  }

}
