package br.com.boltframework.http;

import br.com.boltframework.util.StringUtils;

public enum HttpMethod {
	GET, POST, PUT, DELETE;

	public static HttpMethod createByValue(String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		}
		else if (GET.toString().equals(value.toUpperCase())) {
			return HttpMethod.GET;
		}
		else if (POST.toString().equals(value.toUpperCase())) {
			return HttpMethod.POST;
		}
		else if (PUT.toString().equals(value.toUpperCase())) {
			return HttpMethod.PUT;
		}
		else if (DELETE.toString().equals(value.toUpperCase())) {
			return HttpMethod.DELETE;
		}
		else {
			return null;
		}
	}
}
