package br.com.boltframework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.com.boltframework.config.BoltConfiguration;
import br.com.boltframework.config.DefaultConfiguration;
import br.com.boltframework.core.ClassFinder;
import br.com.boltframework.core.ControllerDecorator;
import br.com.boltframework.core.ControllerMapping;
import br.com.boltframework.core.Result;
import br.com.boltframework.error.BoltException;
import br.com.boltframework.http.HttpMethod;
import br.com.boltframework.util.ClassUtils;
import br.com.boltframework.util.Constants;
import br.com.boltframework.util.ControllerUtils;
import br.com.boltframework.util.StringUtils;

public class Bolt extends HttpServlet {

	private static final long serialVersionUID = -6569963555600736301L;
	private static Logger logger = Logger.getLogger(Bolt.class);

	private List<ControllerMapping> controllerList;
	private BoltConfiguration configuration;

	@Override
	public void init() throws ServletException {
		configuration = getConfigurationInstance();
		controllerList = ClassFinder.createInstance().loadAllControllerMappings(getServletConfig(), getServletContext());
		logger.debug(String.format("Bolt found %1$d actions", controllerList.size()));
	}

	private String getCustomConfiguration() {
		return getServletConfig().getInitParameter(Constants.CONFIGURATION_INIT_PARAMETER);
	}

	private BoltConfiguration getConfigurationInstance() {
		if (StringUtils.isNotBlank(getCustomConfiguration())) {
			try {
				return (BoltConfiguration) ClassUtils.createClassInstance(getCustomConfiguration());
			}
			catch (BoltException e) {
				logger.error("Error to initialize the Custom Bolt Configuration class, please see your class. Using the Default Bolt Configuration class.", e);
			}
		}
		return new DefaultConfiguration();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		action(req, resp, HttpMethod.GET);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		action(req, resp, HttpMethod.POST);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		action(req, resp, HttpMethod.DELETE);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		action(req, resp, HttpMethod.PUT);
	}

	protected void action(HttpServletRequest request, HttpServletResponse response, HttpMethod httpMethod) throws ServletException, IOException {
		Result dispatch = null;
		String pathInfo = request.getPathInfo();
		String applicationContext = ControllerUtils.getApplicationContext(request, getServletConfig());
		String boltContext = ControllerUtils.getBoltContext(request, getServletConfig());
		request.setAttribute(Constants.BOLT_CONTEXT, boltContext);
		request.setAttribute(Constants.APPLICATION_CONTEXT, applicationContext);

		try {
			if (StringUtils.isBlank(pathInfo)) {
				response.sendRedirect(request.getRequestURI() + Constants.FORWARD_SLASH);
			}

			logger.debug(String.format("Action %1$s called via %2$s", pathInfo, httpMethod.toString()));
			ControllerMapping controllerMapping = ControllerUtils.findMapping(controllerList, pathInfo, httpMethod);

			if (controllerMapping == null) {
				String message = String.format("Controller mapping not found: %1$s", pathInfo);
				logger.warn(message);
				throw new ClassNotFoundException(message);
			}

			Class<Object> controllerClass = (Class<Object>) controllerMapping.getController();
			if (controllerClass == null) {
				String message = String.format("Controller class not found: %1$s", pathInfo);
				logger.warn(message);
				throw new ClassNotFoundException("Controller class not found: " + pathInfo);
			}

			Object controller = controllerClass.newInstance();
			Method action = controllerMapping.getAction();
			Method runBeforeActions = controllerMapping.getRunBeforeActions();
			ControllerDecorator controllerDecorator = new ControllerDecorator(controller);

			dispatch = (Result) controllerDecorator.executeAction(request, response, runBeforeActions, action);
		}
		catch (Exception e) {
			request.setAttribute(Constants.ERROR_ATTRIBUTE_NAME, e);
			dispatch = configuration.getResultErrorPage();

			if (dispatch.isErrorPage(configuration)) {
				PrintWriter out = response.getWriter();
				String message = (e.getMessage() == null) ? e.getCause().getClass().getName() : e.getMessage();
				String content = ControllerUtils.obtainDefaultErrorPageWithMessage(message);
				out.print(content);
				return;
			}
		}

		if (dispatch.isForward()) {
			String goTo = dispatch.goTo().endsWith(Constants.JSP_FILE_EXTENSION) ? configuration.getViewsPath() + dispatch.goTo() : dispatch.goTo();
			logger.debug(String.format("Forward request %1$s to %2$s via %3$s", pathInfo, goTo, httpMethod.toString()));

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(goTo);
			dispatcher.forward(request, response);
		}
		else {
			response.sendRedirect(dispatch.goTo());
		}
	}

}