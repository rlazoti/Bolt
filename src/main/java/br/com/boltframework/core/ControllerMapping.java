package br.com.boltframework.core;

import java.lang.reflect.Method;

import br.com.boltframework.http.HttpMethod;
import br.com.boltframework.util.ControllerUtils;

public class ControllerMapping {
	private Class<Object> controller;
	private String controllerName;
	private Method action;
	private String actionName;
	private HttpMethod httpMethod;
	private Method runBeforeActions;

	public String getMapping() {
		return ControllerUtils.createMapping(controllerName, actionName);
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

	public Method getRunBeforeActions() {
		return runBeforeActions;
	}

	public void setRunBeforeActions(Method runBeforeActions) {
		this.runBeforeActions = runBeforeActions;
	}

	@Override
	public String toString() {
		String runBeforeActionsName = runBeforeActions != null ? runBeforeActions.getName() : "none";
		return String.format("Controller: %1$s Action: %2$s Method: %3$s RunBefore: %4$s", controllerName, actionName, httpMethod.toString(), runBeforeActionsName);
	}

}
