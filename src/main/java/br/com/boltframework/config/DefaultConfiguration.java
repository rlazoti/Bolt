package br.com.boltframework.config;

import br.com.boltframework.core.Result;

public class DefaultConfiguration implements BoltConfiguration {

  private final String viewsPath = "/WEB-INF/views/";
  private final String errorPage = "boltErrorPage.html";

  public String getViewsPath() {
    return this.viewsPath;
  }

  public Result getResultErrorPage() {
    return new Result(errorPage, Result.ResultType.FORWARD);
  }

}
