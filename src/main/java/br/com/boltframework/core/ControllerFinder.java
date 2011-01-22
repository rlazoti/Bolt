package br.com.boltframework.core;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import br.com.boltframework.scan.ClassScanner;
import br.com.boltframework.util.Constants;

public final class ControllerFinder {

  private ControllerFinder() {
    super();
  }

  public static ControllerFinder createInstance() {
    return new ControllerFinder();
  }

  public List<ControllerMapping> loadAllControllers(ServletConfig servletConfig, ServletContext servletContext) {
    if (servletContext == null) {
      throw new IllegalArgumentException("The argument 'servletContext' must contain a value.");
    }

    Class<Object>[] controllers = null;
    List<ControllerMapping> controllerList = new ArrayList<ControllerMapping>();
    ClassScanner classScanner = ClassScanner.createInstance().withDefaultDirectoryClasses();
    String basePackage = Constants.DOT;

    try {
      controllers = classScanner.getAllClassesOfServletContext(servletContext, basePackage);
    }
    catch (ClassNotFoundException e) {
      throw new IllegalStateException("Failed to list the actions! Please, see your actions mapping in the web.xml file.");
    }

    for (Class<Object> controller : controllers) {
      String mapping = classScanner.getMappingByController(controller);
      controllerList.addAll(classScanner.getActionsByController(controller, mapping));
    }

    controllers = null;
    return controllerList;
  }
}
