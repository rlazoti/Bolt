package br.com.boltframework.scan.classes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.boltframework.annotation.Action;
import br.com.boltframework.annotation.Controller;
import br.com.boltframework.annotation.RunBeforeAction;
import br.com.boltframework.core.Result;
import br.com.boltframework.http.HttpMethod;

@Controller(mappedBy = "user")
public class UserController {

	@Action(methods = HttpMethod.GET)
	public Result index(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	@Action(methods = HttpMethod.GET)
	public Result show(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	@Action(methods = HttpMethod.GET)
	public Result create(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	@Action(methods = HttpMethod.POST)
	public Result save(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	@Action(methods = HttpMethod.GET)
	public Result edit(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	@Action(methods = HttpMethod.GET)
	public Result update(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	@Action(methods = HttpMethod.GET)
	public Result delete(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	@SuppressWarnings("unused")
  private void doSomething() {
		System.out.println("ops");
	}

	protected Object otherMethod(String s, int i) {
		return new Object();
	}

	public String fakeAction(HttpServletRequest request, HttpServletResponse response) {
		return "list";
	}

	@RunBeforeAction
	public String runBeforeAction(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
}
