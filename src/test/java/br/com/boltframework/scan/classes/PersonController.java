package br.com.boltframework.scan.classes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.boltframework.annotation.Action;
import br.com.boltframework.annotation.Controller;
import br.com.boltframework.annotation.RunBeforeActions;
import br.com.boltframework.core.Result;
import br.com.boltframework.core.ViewHelper;
import br.com.boltframework.http.HttpMethod;

@Controller(mappedBy = "person")
public class PersonController {

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

	@RunBeforeActions(applyToActions = { "update", "delete", "save" })
	public Result runBeforeAction(HttpServletRequest request, HttpServletResponse response) {
		return ViewHelper.processAction();
	}

}
