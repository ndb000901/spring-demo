package com.hello.demo.spring.validator.common;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({ ElementType.FIELD }) //可用于字段或方法参数
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = ) // 指定校验器
public @interface NotContainA {

	String message() default "{jakarta.validation.constraints.NotNull.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	@interface List {

		jakarta.validation.constraints.NotNull[] value();
	}
}

