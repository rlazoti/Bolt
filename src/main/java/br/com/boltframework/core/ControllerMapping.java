package br.com.boltframework.core;

import java.lang.reflect.Method;

import br.com.boltframework.http.HttpMethod;
import br.com.boltframework.util.Constants;
import br.com.boltframework.util.StringUtils;

public class ControllerMapping {
  private Class<Object> controller;
  private String controllerName;
  private Method action;
  private String actionName;
  private HttpMethod httpMethod;

  public String getMapping() {
    return createMapping(controllerName, actionName);
  }

  public Class<Object> getController() {
    return controller;
  }

  public String getControllerName() {
    return controllerName;
  }

  public Method getAction() {
    return action;
  }

  public String getActionName() {
    return actionName;
  }

  public HttpMethod getHttpMethod() {
    return httpMethod;
  }

  public void setController(Class<Object> controller) {
    this.controller = controller;
  }

  public void setControllerName(String controllerName) {
    this.controllerName = controllerName;
  }

  public void setAction(Method action) {
    this.action = action;
  }

  public void setActionName(String actionName) {
    this.actionName = actionName;
  }

  public void setHttpMethod(HttpMethod httpMethod) {
    this.httpMethod = httpMethod;
  }

  public static String createMapping(String controllerName, String actionName) {
    if (StringUtils.isBlank(controllerName) || StringUtils.isBlank(actionName)) {
      return null;
    }
    else {
      return Constants.FORWARD_SLASH + controllerName + Constants.FORWARD_SLASH + actionName;
    }
  }

}
