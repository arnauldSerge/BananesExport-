package com.cag.marketplace.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MultipleDeValidator implements ConstraintValidator<MultipleDeConstraint, Long> {

	private long facteur = 7;
	
	public MultipleDeValidator() {
		super();
	}
	
	

	@Override
	public void initialize(MultipleDeConstraint constraint) {
		this.facteur = constraint.facteur();
	}
	

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		return value%facteur == 0;
	}
}
