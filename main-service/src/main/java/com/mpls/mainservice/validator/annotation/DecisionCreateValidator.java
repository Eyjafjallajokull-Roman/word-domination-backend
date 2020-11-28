package com.mpls.mainservice.validator.annotation;

import com.mpls.mainservice.validator.DecisionCreateValidatorConstrain;
import com.mpls.mainservice.validator.DonateMoneyValidatorConstrain;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = DecisionCreateValidatorConstrain.class)
@Documented
public @interface DecisionCreateValidator {

    String message() default "money not found";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
