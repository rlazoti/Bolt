package br.com.boltframework.util;

import br.com.boltframework.error.BoltException;

public class ClassUtils {

	/**
	 * Verify if a class exists by its name, if the class doesn't exist then BoltException exception will be throw.
	 * @param className
	 * @return
	 */
  public static final boolean classExists(String className) {
  	try {
      Class.forName(className);
      return true;
    }
    catch (ClassNotFoundException e) {
      return false;
    }
  }

  /**
   * Obtain a class by its name, if the class doesn't exist then BoltException exception will be throw.
   * @param className
   * @return
   * @throws BoltException
   */
  public static final Class<?> getClassOfClassName(String className) throws BoltException {
    try {
      return Class.forName(className);
    }
    catch (ClassNotFoundException e) {
      throw new BoltException(e);
    }
  }

  /**
   * Create a new instance of a class by its name, if the class doesn't exist then BoltException exception will be throw.
   * @param className
   * @return
   * @throws BoltException
   */
  public static final Object createClassInstance(String className) throws BoltException {
    Class<?> clazz = getClassOfClassName(className);

    try {
	    return clazz.newInstance();
    }
    catch (Exception e) {
	    throw new BoltException(e);
    }
  }

}
