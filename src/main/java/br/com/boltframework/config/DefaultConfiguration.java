package br.com.boltframework.config;

public class DefaultConfiguration implements BoltConfiguration {

  private final String viewsPath = "/WEB-INF/jsp/";
  private final String errorPage = "boltErrorPage.html";

  @Override
  public String getViewsPath() {
    return this.viewsPath;
  }

  @Override
  public String getErrorPage() {
    return errorPage;
  }

}
