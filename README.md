Bolt
====

It's a micro web MVC framework write in Java.

Requirements
============

Java 1.5+

Dependencies
============

log4j 1.2+

Install
=======

To use Bolt in your web application, you just need to define the follow servlet in the web.xml file:

    <servlet>
        <servlet-name>Bolt</servlet-name>
        <servlet-class>br.com.boltframework.Bolt</servlet-class>
        <init-param>
            <param-name>applicationContext</param-name>
            <param-value>app</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>Bolt</servlet-name>
        <url-pattern>/app/*</url-pattern>
    </servlet-mapping>

The init parameter called "applicationContext" should be the same value of the "url-pattern" (without '/' and '*').
Currently the framework doesn't work with extension mapping yet (example: *.do).

Usage
=====

controllers
----------

You should annotate the controller with @Controller and define the mappedBy parameter.

    @Controller(mappedBy = "welcome")
    public class WelcomeController {
    
    }

Actions
-------

You should annotate the action with @Action and define the http method that it can receive.

    @Action(methods = HttpMethod.GET)
    public Result index(HttpServletRequest request, HttpServletResponse response) {
        return ViewHelper.forwardToDefaultView();
    }
 
An action can response to more of one http method.

    @Action(methods = { HttpMethod.GET, HttpMethod.POST })
    public Result index(HttpServletRequest request, HttpServletResponse response) {
        return ViewHelper.forwardToDefaultView();
    }

Views
-----

By default, views are in WEB-INF/views folder and each jsp file follow the following convertion "controller mapping"/"action name".jsp.
The example below uses the following JSP file: /WEB-INF/views/welcome/index.jsp

    @Controller(mappedBy = "welcome"
    public class WelcomeController {

        @Action(methods = HttpMethod.GET)
        public Result index(HttpServletRequest request, HttpServletResponse response) {
            return ViewHelper.forwardToDefaultView();
        }
    
    }

Forward and Redirect
--------------------

To forward or redirect an action or a view you should use the ViewHelper class.

    ViewHelper.forwardToDefaultView(); //forward the current action to its default view.
    ViewHelper.forwardToView(String controller, String view); //forward to another view in some other controller or in the current controller
    ViewHelper.redirectToAction(HttpServletRequest request, String controller, String action); //redirect the current action to another action in some other controller or in the current controller.
    ViewHelper.forwardToAction(HttpServletRequest request, String controller, String action); //forward the current action to another action in some other controller or in the current controller.

Building in your machine
========================

If you want to build it, you'll need to install Gradle.
Then, execute:

    gradle build

Authors
=======

Rodrigo Lazoti - rodrigolazoti@gmail.com
