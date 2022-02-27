package com.bananeexport.validator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateLivraisonValidator implements ConstraintValidator<DateLivraisonConstraint, Date> {

	//	@Override
	//    public void initialize(DateLivraisonConstraint date) {
	//    }
	
	public DateLivraisonValidator() {
		super();
	}
	
	public DateLivraisonValidator(Long minimum) {
		
	}

	private long minimum = 7;

	@Override
	public void initialize(DateLivraisonConstraint minimum) {
		this.minimum = minimum.minimum();
	}
	
	public void initialize(long minimum) {
		this.minimum = 7;
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
	
	
	public boolean isValid(Date value) {
		LocalDate today = LocalDate.now();
		LocalDate localDateToBeValidate = value.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();
		return (localDateToBeValidate.isAfter(today) 
				&& ChronoUnit.DAYS.between(today,localDateToBeValidate) >= minimum);
	}

}
