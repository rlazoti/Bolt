package br.com.boltframework.error;

public class BoltException extends Exception {

  private static final long serialVersionUID = 1299058916677378267L;

  public BoltException(String message, Throwable cause) {
    super(message, cause);
  }

  public BoltException(Throwable cause) {
    super(cause);
  }

  public BoltException(String message) {
    super(message);
  }

}
