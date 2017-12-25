package com.my.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

	// Can use @Autowired because of ConstraintValidator, This class will be set to @Component

	@Override
	public void initialize(MyConstraint myConstraint) {
		System.out.println("MyConstraintValidator init");
	}

	@Override
	public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
		System.out.println("MyConstraintValidator: " + o);
		return false;
	}
}
