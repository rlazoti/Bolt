package br.com.boltframework.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.boltframework.core.ControllerMapping;
import br.com.boltframework.http.HttpMethod;
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

  private List<ControllerMapping> getMappingList() {
    List<ControllerMapping> mappings = new ArrayList<ControllerMapping>();
    mappings.add(createMapping("home", "index", HttpMethod.GET));
    mappings.add(createMapping("home", "contact", HttpMethod.POST));
    mappings.add(createMapping("users", "list", HttpMethod.GET));
    mappings.add(createMapping("users", "show", HttpMethod.POST));
    return mappings;
  }

  private ControllerMapping createMapping(String controllerName, String actionName, HttpMethod httpMethod) {
    ControllerMapping mapping = new ControllerMapping();
    mapping.setControllerName(controllerName);
    mapping.setActionName(actionName);
    mapping.setHttpMethod(httpMethod);
    return mapping;
  }

  @Test
  public void testObtainDefaultErrorPageWithMessage() {
    try {
      String errorPage = ControllerUtils.obtainDefaultErrorPageWithMessage("");
      assertNotNull(errorPage);
    }
    catch (IOException e) {
      assertTrue(false);
    }
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

  @Test
  public void testFindMappingWithCorrectValue() {
    ControllerMapping mapping = ControllerUtils.findMapping(getMappingList(), "/home/index", HttpMethod.GET);
    assertNotNull(mapping);
  }
  
  @Test
  public void testFindMappingWithCorrectValue2() {
    ControllerMapping mapping = ControllerUtils.findMapping(getMappingList(), "/users/show", HttpMethod.POST);
    assertNotNull(mapping);
  }

  @Test
  public void testFindMappingWithoutCorrectValue() {
    ControllerMapping mapping = ControllerUtils.findMapping(getMappingList(), "/users/login", HttpMethod.GET);
    assertNull(mapping);
  }

  @Test
  public void testFindMappingWithoutValue() {
    ControllerMapping mapping = ControllerUtils.findMapping(getMappingList(), "/", HttpMethod.GET);
    assertNull(mapping);
  }

  @Test
  public void testFindMappingWithoutValue2() {
    ControllerMapping mapping = ControllerUtils.findMapping(getMappingList(), "", HttpMethod.GET);
    assertNull(mapping);
  }

  @Test
  public void testFindMappingWithoutValue3() {
    ControllerMapping mapping = ControllerUtils.findMapping(getMappingList(), null, HttpMethod.GET);
    assertNull(mapping);
  }

  @Test
  public void testFindMappingWithoutValue4() {
    ControllerMapping mapping = ControllerUtils.findMapping(getMappingList(), "/home", HttpMethod.GET);
    assertNull(mapping);
  }

  @Test
  public void testCreateMappingWithCorrectValue() {
    String mapping = ControllerUtils.createMapping("home", "index");
    assertEquals("/home/index", mapping);
  }
  
  @Test
  public void testCreateMappingWithoutController() {
    String mapping = ControllerUtils.createMapping("", "index");
    assertNull(mapping);
  }

  @Test
  public void testCreateMappingWithoutAction() {
    String mapping = ControllerUtils.createMapping("home", "");
    assertNull(mapping);
  }

  @Test
  public void testCreateMappingWithoutCorrectValue() {
    String mapping = ControllerUtils.createMapping(null, null);
    assertNull(mapping);
  }

}
