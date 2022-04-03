package com.cag.marketplace.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = MultipleDeValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface MultipleDeConstraint {
	String message() default "validator de multiple";

	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default { };
	/**
	 * Nombre de jour minimum accepte pour la livraison
	 * @return
	 */
	long facteur();
}
