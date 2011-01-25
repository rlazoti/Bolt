package br.com.boltframework.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.boltframework.annotation.Controller;
import br.com.boltframework.util.Constants;

public class ControllerDecorator {

  private Object controller;

  protected List<String> returnMessages = new ArrayList<String>();

  public ControllerDecorator(Object controller) {
    this.controller = controller;
  }

  public String initializeAction(HttpServletRequest request, HttpServletResponse response, Method action)
      throws Exception {
    Object[] args = new Object[2];
    args[0] = request;
    args[1] = response;

    String result = (String) action.invoke(controller, args);
    
    if (ViewHelper.DEFAULT_PAGE.equalsIgnoreCase(result)) {
      Controller annotation = controller.getClass().getAnnotation(Controller.class);
      result = annotation.mappedBy() +Constants.FORWARD_SLASH + action.getName() + Constants.JSP_FILE_EXTENSION;
    }
    
    args = null;
    return result;
  }
}