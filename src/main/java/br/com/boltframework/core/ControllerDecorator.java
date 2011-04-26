package br.com.boltframework.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.boltframework.annotation.Controller;

public class ControllerDecorator {

  private Object controller;

  protected List<String> returnMessages = new ArrayList<String>();

  public ControllerDecorator(Object controller) {
    this.controller = controller;
  }

  public Result executeAction(HttpServletRequest request, HttpServletResponse response, Method runBeforeAction, Method action) throws Exception {
    Object[] args = new Object[2];
    args[0] = request;
    args[1] = response;

    Result result = ViewHelper.processAction();

    if (runBeforeAction != null) {
      result = (Result) runBeforeAction.invoke(controller, args);
    }

    if (result.isProcessAction()) {
      result = (Result) action.invoke(controller, args);
    }

    if (result.isDefaultView()) {
      Controller annotation = controller.getClass().getAnnotation(Controller.class);
      result = ViewHelper.forwardToView(annotation.mappedBy(), action.getName());
    }

    args = null;
    return result;
  }

}