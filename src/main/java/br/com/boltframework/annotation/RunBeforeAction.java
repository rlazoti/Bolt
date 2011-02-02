package br.com.boltframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.boltframework.util.Constants;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RunBeforeAction {
	String[] applyToActions() default { Constants.ALL_ACTIONS };
}
