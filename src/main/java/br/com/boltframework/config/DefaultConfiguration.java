package br.com.boltframework.config;

import br.com.boltframework.core.Result;

public class DefaultConfiguration implements BoltConfiguration {

  private final String viewsPath = "/WEB-INF/views/";
  private final String errorPage = "boltErrorPage.html";

  @Override
  public String getViewsPath() {
    return this.viewsPath;
  }

  @Override
  public Result getResultErrorPage() {
    return new Result(errorPage, Result.ResultType.FORWARD);
  }

}
