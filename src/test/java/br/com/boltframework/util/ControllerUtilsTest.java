package br.com.boltframework.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class ControllerUtilsTest {

  private void testControllerExpected(String path, String expected) {
    String controller = ControllerUtils.getControllerMappingByPath(path);
    assertEquals(expected, controller);
  }

  private void testActionExpected(String path, String expected) {
    String controller = ControllerUtils.getActionMappingByPath(path);
    assertEquals(expected, controller);
  }

  @Test
  public void testGetControllerMappingByPathWithCorrectPath() {
    testControllerExpected("home/index", "home");
    testControllerExpected("/home/index", "home");
    testControllerExpected("/home/index/", "home");    
  }
  
  @Test
  public void testGetControllerMappingByPathWithTwoLevelsOfCorrectPath() {
    testControllerExpected("orders/items/show", "orders/items");
    testControllerExpected("/orders/items/show", "orders/items");
    testControllerExpected("orders/items/show/", "orders/items");    
  }

  @Test
  public void testGetControllerMappingByPathWithoutController() {
    testControllerExpected("/index/", null);
    testControllerExpected("index/", null);
    testControllerExpected("/index", null);
    testControllerExpected("index", null);
  }
  
  @Test
  public void testGetControllerMappingByPathWithoutValue() {
    testControllerExpected("/", null);
    testControllerExpected("", null);
    testControllerExpected(" ", null);
    testControllerExpected("    ", null);
    testControllerExpected(null, null);
  }

  @Test
  public void testGetActionMappingByPathWithCorrectPath() {
    testActionExpected("home/index", "index");
    testActionExpected("/home/index", "index");
    testActionExpected("/home/index/", "index");    
  }

  @Test
  public void testGetActionMappingByPathWithTwoLevelsOfCorrectPath() {
    testActionExpected("orders/items/show", "show");
    testActionExpected("/orders/items/show", "show");
    testActionExpected("orders/items/show/", "show");    
  }

  @Test
  public void testGetActionMappingByPathWithoutController() {
    testActionExpected("/index/", "index");
    testActionExpected("index/", "index");
    testActionExpected("/index", "index");
    testActionExpected("index", "index");
  }

  @Test
  public void testGetActionMappingByPathWithoutValue() {
    testActionExpected("/", null);
    testActionExpected("", null);
    testActionExpected(" ", null);
    testActionExpected("    ", null);
    testActionExpected(null, null);
  }

}
