package br.com.boltframework.util;

import br.com.boltframework.error.BoltException;

public class ClassUtils {

  public static final boolean classExists(String className) {
  	try {
      Class.forName(className);
      return true;
    }
    catch (ClassNotFoundException e) {
      return false;
    }
  }

  public static final Class<?> getClassOfClassName(String className) throws BoltException {
    try {
      return Class.forName(className);
    }
    catch (ClassNotFoundException e) {
      throw new BoltException(e);
    }
  }

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
