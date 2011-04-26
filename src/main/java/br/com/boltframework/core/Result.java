package br.com.boltframework.core;

import br.com.boltframework.config.BoltConfiguration;

public class Result {

  private String goTo;
  private ResultType resultType;

  public enum ResultType {
    FORWARD, REDIRECT;
  }

  public Result(String goTo, ResultType resultType) {
    this.goTo = goTo;
    this.resultType = resultType;
  }

  public String goTo() {
    return goTo;
  }

  public ResultType getResultType() {
    return resultType;
  }

  public boolean isProcessAction() {
    Result processActionResult = ViewHelper.processAction();
    return processActionResult.goTo().equals(this.goTo) && processActionResult.getResultType() == this.resultType;
  }

  public boolean isDefaultView() {
    Result processActionResult = ViewHelper.forwardToDefaultView();
    return processActionResult.goTo().equals(this.goTo) && processActionResult.getResultType() == this.resultType;
  }

  public boolean isErrorPage(BoltConfiguration configuration) {
    Result resultErrorPage = configuration.getResultErrorPage();
    return resultErrorPage.goTo().equals(this.goTo) && resultErrorPage.getResultType() == this.resultType;
  }

  public boolean isRedirect() {
    return ResultType.REDIRECT == this.resultType;
  }

  public boolean isForward() {
    return ResultType.FORWARD == this.resultType;
  }

  @Override
  public String toString() {
    return String.format("Result to: %1$s by: %2$s", goTo, resultType.toString());
  }

}
