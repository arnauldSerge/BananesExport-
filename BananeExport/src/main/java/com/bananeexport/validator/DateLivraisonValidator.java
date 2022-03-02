package com.bananeexport.validator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateLivraisonValidator implements ConstraintValidator<DateLivraisonConstraint, Date> {
	
	private long minimum = 7;
	
	public DateLivraisonValidator() {
		super();
	}
	

	@Override
	public void initialize(DateLivraisonConstraint minimum) {
		this.minimum = minimum.minimum();
	}
	


	@Override
	public boolean isValid(Date value, ConstraintValidatorContext context) {
		LocalDate today = LocalDate.now();
		LocalDate localDateToBeValidate = value.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();
		return (localDateToBeValidate.isAfter(today) 
				&& ChronoUnit.DAYS.between(today,localDateToBeValidate) >= minimum);
	}
}
