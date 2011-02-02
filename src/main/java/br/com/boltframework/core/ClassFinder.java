package br.com.boltframework.core;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import br.com.boltframework.annotation.Controller;
import br.com.boltframework.scan.ClassScanner;
import br.com.boltframework.util.Constants;

public final class ClassFinder {

  private ClassFinder() {
    super();
  }

  public static ClassFinder createInstance() {
    return new ClassFinder();
  }

  public List<ControllerMapping> loadAllControllerMappings(ServletConfig servletConfig, ServletContext servletContext) {
    if (servletContext == null) {
      throw new IllegalArgumentException("The argument 'servletContext' must contain a value.");
    }

    Class<Object>[] classes = null;
    List<ControllerMapping> controllerList = new ArrayList<ControllerMapping>();
    ClassScanner classScanner = ClassScanner.createInstance().withDefaultDirectoryClasses();
    String basePackage = Constants.DOT;

    try {
      classes = classScanner.getAllClassesOfApplication(servletContext, basePackage);
    }
    catch (ClassNotFoundException e) {
      throw new IllegalStateException("Failed to list the actions! Please, see your actions mapping in the web.xml file.");
    }

    for (Class<Object> clazz : classes) {
      if (classScanner.isAnnotatedWithController(clazz)) {
        Controller controller = classScanner.obtainControllerAnnotationByClass(clazz);
        String mapping = controller.mappedBy();
        controllerList.addAll(classScanner.getActionsByClass(clazz, mapping));
      }
    }

    classes = null;
    return controllerList;
  }
}
