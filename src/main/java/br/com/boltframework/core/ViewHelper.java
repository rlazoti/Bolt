package br.com.boltframework.core;

import javax.servlet.http.HttpServletRequest;

import br.com.boltframework.util.Constants;

public class ViewHelper {
  public static final String DEFAULT_PAGE = "BoltDefaultActionPage";
  public static final String PROCESS_ACTION = "BoltProcessAction";

	public static String redirectToAction(HttpServletRequest request, String controller, String action) {
		String applicationContext = (String) request.getAttribute(Constants.APPLICATION_CONTEXT);
		return applicationContext + Constants.FORWARD_SLASH + controller + Constants.FORWARD_SLASH + action;
	}

}
