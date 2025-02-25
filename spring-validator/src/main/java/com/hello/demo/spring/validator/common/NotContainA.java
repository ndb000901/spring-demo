package com.hello.demo.spring.validator.common;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = NotContainAConstraintValidator.class) // 指定校验器
public @interface NotContainA {

	String message() default "不能包含字符A"; // 默认错误消息

	Class<?>[] groups() default { }; // 分组

	Class<? extends Payload>[] payload() default { }; // 负载

	// 指定多个时使用
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	@interface List {

		jakarta.validation.constraints.NotNull[] value();
	}
}

