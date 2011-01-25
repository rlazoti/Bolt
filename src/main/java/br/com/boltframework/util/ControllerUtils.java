package br.com.boltframework.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import br.com.boltframework.core.ControllerMapping;
import br.com.boltframework.http.HttpMethod;

public final class ControllerUtils {

  enum MappingType {
    Controller, Action;
  };

  private static final String returnMappingByType(String controller, String action, MappingType mappingType) {
    if (MappingType.Controller.equals(mappingType)) {
      return controller;
    }
    else if (MappingType.Action.equals(mappingType)) {
      return action;
    }
    else {
      return null;
    }
  }

  private static final String getMappingByPath(String currentPath, MappingType mappingType) {
    String path = clearPath(currentPath);

    if (StringUtils.isBlank(path)) {
      return null;
    }

    String[] values = path.split(Constants.FORWARD_SLASH);
    String controller = null;
    String action = null;

    for (int i = 0; i < values.length; i++) {
      if (i < values.length - 1 && controller == null) {
        controller = values[i];
      }
      else if (i < values.length - 1 && controller != null) {
        controller += Constants.FORWARD_SLASH + values[i];
      }
      else {
        action = values[i];
      }
    }

    return returnMappingByType(controller, action, mappingType);
  }

  private static final String clearPath(String currentPath) {
    String path = currentPath;

    if (StringUtils.isNotBlank(path)) {
      path = path.trim();

      if (path.startsWith(Constants.FORWARD_SLASH)) {
        path = path.substring(1);
      }

      if (path.endsWith(Constants.FORWARD_SLASH)) {
        path = path.substring(0, path.length() - 1);
      }
    }

    return path;
  }

  public static final String getControllerMappingByPath(String currentPath) {
    return getMappingByPath(currentPath, MappingType.Controller);
  }

  public static final String getActionMappingByPath(String currentPath) {
    return getMappingByPath(currentPath, MappingType.Action);
  }

	public static ControllerMapping findMapping(List<ControllerMapping> mappings, String path, HttpMethod httpMethod) {
		String controller = ControllerUtils.getControllerMappingByPath(path);
		String action = ControllerUtils.getActionMappingByPath(path);

		if (StringUtils.isBlank(controller) || StringUtils.isBlank(action)) {
			return null;
		}

		String currentMapping = ControllerMapping.createMapping(controller, action);
		for (ControllerMapping controllerMapping : mappings) {
			if (controllerMapping.getMapping().equals(currentMapping) && controllerMapping.getHttpMethod().equals(httpMethod)) {
				return controllerMapping;
			}
		}

		return null;
	}

  public static String getApplicationContext(HttpServletRequest request, ServletConfig servletConfig) {
    return request.getContextPath() + Constants.FORWARD_SLASH + servletConfig.getInitParameter(Constants.APPLICATION_CONTEXT);
  }

	public static String obtainDefaultErrorPageWithMessage(String errorMessage) throws IOException {
		String content = FileUtils.obtainContentOfFile(Constants.DEFAULT_ERROR_PAGE);
		if (StringUtils.isNotBlank(content)) {
			content = content.replace(Constants.ERROR_ATTRIBUTE_NAME, errorMessage);
		}
		return content;
	}
}
