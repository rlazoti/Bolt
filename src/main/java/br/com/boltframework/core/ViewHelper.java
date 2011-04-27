package br.com.boltframework.core;

import javax.servlet.http.HttpServletRequest;

import br.com.boltframework.core.Result.ResultType;
import br.com.boltframework.util.Constants;

public class ViewHelper {

  private static final String DEFAULT_VIEW = "BoltDefaultView";
  private static final String PROCESS_ACTION = "BoltProcessAction";

  public static Result processAction() {
    return new Result(PROCESS_ACTION, null);
  }

  public static Result forwardToDefaultView() {
    return new Result(DEFAULT_VIEW, ResultType.FORWARD);
  }

  public static Result redirectToAction(HttpServletRequest request, String controller, String action) {
    String applicationContext = (String) request.getAttribute(Constants.APPLICATION_CONTEXT);
    String goTo = applicationContext + Constants.FORWARD_SLASH + controller + Constants.FORWARD_SLASH + action;
    return new Result(goTo, Result.ResultType.REDIRECT);
  }

  public static Result forwardToAction(HttpServletRequest request, String controller, String action) {
  	String boltContext = (String) request.getAttribute(Constants.BOLT_CONTEXT);
  	String goTo = boltContext + Constants.FORWARD_SLASH + controller + Constants.FORWARD_SLASH + action;
    return new Result(goTo, Result.ResultType.FORWARD);
  }

  public static Result forwardToView(String controller, String view) {
    String goTo = controller + Constants.FORWARD_SLASH + view + Constants.JSP_FILE_EXTENSION;
    return new Result(goTo, Result.ResultType.FORWARD);
  }

}
