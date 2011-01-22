package br.com.boltframework.config;

public class DefaultConfiguration implements BoltConfiguration {

  private final String viewsPath = "/WEB-INF/views/";
  private final String errorPage = "boltErrorPage.html";
  private final String locale = "pt-BR";

  @Override
  public String getViewsPath() {
    return this.viewsPath;
  }

  @Override
  public String getErrorPage() {
    return errorPage;
  }

  @Override
  public String getLocale() {
    return locale;
  }

}
